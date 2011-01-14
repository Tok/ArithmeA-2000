package arithmea.client.presenter;

import java.util.ArrayList;
import java.util.List;

import arithmea.client.ArithmeaServiceAsync;
import arithmea.client.event.AddTermEvent;
import arithmea.client.event.EditTermEvent;
import arithmea.shared.Term;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

public class TermsPresenter implements Presenter {
	private List<Term> terms;

	public interface Display {
		HasClickHandlers getAddButton();
		HasClickHandlers getDeleteButton();
		HasClickHandlers getList();
		void setData(List<Term> data);
		int getClickedRow(ClickEvent event);
		List<Integer> getSelectedRows();
		Widget asWidget();
	}

	private final ArithmeaServiceAsync rpcService;
	private final HandlerManager eventBus;
	private final Display display;

	public TermsPresenter(final ArithmeaServiceAsync rpcService,
			final HandlerManager eventBus, final Display view) {
		this.rpcService = rpcService;
		this.eventBus = eventBus;
		this.display = view;
	}

	public void bind() {
		display.getAddButton().addClickHandler(new ClickHandler() {
			public void onClick(final ClickEvent event) {
				eventBus.fireEvent(new AddTermEvent());
			}
		});
		display.getDeleteButton().addClickHandler(new ClickHandler() {
			public void onClick(final ClickEvent event) {
				deleteSelectedTerms();
			}
		});
		display.getList().addClickHandler(new ClickHandler() {
			public void onClick(final ClickEvent event) {
				int selectedRow = display.getClickedRow(event);
				if (selectedRow >= 0) {
					final String id = terms.get(selectedRow).getLatinString();
					eventBus.fireEvent(new EditTermEvent(id));
				}
			}
		});
	}

	public void go(final HasWidgets container) {
		bind();
		container.clear();
		container.add(display.asWidget());
		fetchTermDetails();
	}

	public void sortTermDetails() {
		// TODO implement better sort
		for (int i = 0; i < terms.size(); ++i) {
			for (int j = 0; j < terms.size() - 1; ++j) {
				Term tmp = terms.get(j);
				if (tmp.getLatinString() != null
						&& tmp.getLatinString().compareToIgnoreCase(
								terms.get(j + 1).getLatinString()) >= 0) {
					terms.set(j, terms.get(j + 1));
					terms.set(j + 1, tmp);
				}
			}
		}
	}

	public void setTermDetails(final List<Term> termDetails) {
		this.terms = termDetails;
	}

	public Term getTermDetail(final int index) {
		return terms.get(index);
	}

	private void fetchTermDetails() {
		rpcService.getTerms(new AsyncCallback<ArrayList<Term>>() {
			public void onSuccess(ArrayList<Term> result) {
				terms = result;
				sortTermDetails();
				display.setData(terms);
			}
			public void onFailure(Throwable caught) {
				Window.alert("Fail fetching term details");
			}
		});
	}

	private void deleteSelectedTerms() {
		final List<Integer> selectedRows = display.getSelectedRows();
		final ArrayList<String> ids = new ArrayList<String>();
		for (int i = 0; i < selectedRows.size(); ++i) {
			ids.add(terms.get(selectedRows.get(i)).getId());
		}
		rpcService.deleteTerms(ids, new AsyncCallback<ArrayList<Term>>() {
			public void onSuccess(ArrayList<Term> result) {
				terms = result;
				sortTermDetails();
				display.setData(terms);
			}
			public void onFailure(Throwable caught) {
				Window.alert("Fail deleting selected terms");
			}
		});
	}
}
