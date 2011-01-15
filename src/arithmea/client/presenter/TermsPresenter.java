package arithmea.client.presenter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import arithmea.client.ArithmeaServiceAsync;
import arithmea.client.event.AddTermEvent;
import arithmea.client.sort.TermSortByGematriaMethod;
import arithmea.client.sort.TermSortByLatinString;
import arithmea.shared.GematriaMethod;
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
		HasClickHandlers getTermHeader();
		HasClickHandlers getGematriaHeader(GematriaMethod gm);
		void setData(List<Term> data);
//		int getClickedRow(ClickEvent event);
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
		
		display.getTermHeader().addClickHandler(new ClickHandler() {
			public void onClick(final ClickEvent event) {
				sortTermsByLatinString();
			}
		});	
		for (final GematriaMethod gm : GematriaMethod.values()) {
			display.getGematriaHeader(gm).addClickHandler(new ClickHandler() {
				public void onClick(final ClickEvent event) {
					sortTermsBy(gm);
				}
			});			
		}
		
//		display.getList().addClickHandler(new ClickHandler() {
//			public void onClick(final ClickEvent event) {
//				int selectedRow = display.getClickedRow(event);
//				if (selectedRow >= 0) {
//					final String id = terms.get(selectedRow).getLatinString();
//					eventBus.fireEvent(new EditTermEvent(id));
//				}
//			}
//		});
	}

	public void go(final HasWidgets container) {
		bind();
		container.clear();
		container.add(display.asWidget());
		fetchTermDetails();
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
				sortTermsByLatinString();
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
			ids.add(terms.get(selectedRows.get(i)).getLatinString());
		}
		rpcService.deleteTerms(ids, new AsyncCallback<ArrayList<Term>>() {
			public void onSuccess(ArrayList<Term> result) {
				terms = result;
				sortTermsByLatinString();
				display.setData(terms);
			}
			public void onFailure(Throwable caught) {
				Window.alert("Fail deleting selected terms");
			}
		});
	}

	public void sortTermsByLatinString() {
        Collections.sort(terms, new TermSortByLatinString());
        display.setData(terms);
	}
	public void sortTermsBy(GematriaMethod gm) {
        Collections.sort(terms, new TermSortByGematriaMethod(gm));
        display.setData(terms);
	}
}
