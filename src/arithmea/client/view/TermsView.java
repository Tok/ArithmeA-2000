package arithmea.client.view;

import java.util.ArrayList;
import java.util.List;

import arithmea.client.presenter.TermsPresenter;
import arithmea.shared.Term;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTMLTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class TermsView extends Composite implements TermsPresenter.Display {
	private final Button addButton;
	private final Button deleteButton;
	private final FlexTable termsTable;
	private final FlexTable contentTable;

	public TermsView() {
		final DecoratorPanel contentTableDecorator = new DecoratorPanel();
		initWidget(contentTableDecorator);
		contentTableDecorator.setWidth("100%");
		contentTableDecorator.setWidth("18em");

		contentTable = new FlexTable();
		contentTable.setWidth("100%");
		contentTable.getCellFormatter().addStyleName(0, 0,
				"terms-ListContainer");
		contentTable.getCellFormatter().setWidth(0, 0, "100%");
		contentTable.getFlexCellFormatter().setVerticalAlignment(0, 0,
				HasVerticalAlignment.ALIGN_TOP);

		// create the menu
		final HorizontalPanel hPanel = new HorizontalPanel();
		hPanel.setBorderWidth(0);
		hPanel.setSpacing(0);
		hPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
		addButton = new Button("Add");
		hPanel.add(addButton);
		deleteButton = new Button("Delete");
		hPanel.add(deleteButton);
		contentTable.getCellFormatter().addStyleName(0, 0, "terms-ListMenu");
		contentTable.setWidget(0, 0, hPanel);

		// create the terms table
		termsTable = new FlexTable();
		termsTable.setCellSpacing(0);
		termsTable.setCellPadding(0);
		termsTable.setWidth("100%");
		termsTable.addStyleName("terms-ListContents");
		termsTable.getColumnFormatter().setWidth(0, "15px");
		contentTable.setWidget(1, 0, termsTable);

		contentTableDecorator.add(contentTable);
	}

	public HasClickHandlers getAddButton() {
		return addButton;
	}

	public HasClickHandlers getDeleteButton() {
		return deleteButton;
	}

	public HasClickHandlers getList() {
		return termsTable;
	}

	public void setData(final List<Term> terms) {
		termsTable.removeAllRows();
		termsTable.setWidget(0, 1, new Label("Term"));
		termsTable.setWidget(0, 2, new Label("Chal"));
		termsTable.setWidget(0, 3, new Label("Pyth"));
		termsTable.setWidget(0, 4, new Label("A=1"));
		termsTable.setWidget(0, 5, new Label("NAEQ"));
		termsTable.setWidget(0, 6, new Label("TQ"));
		for (int i = 0; i < terms.size(); ++i) {
			final Term term = terms.get(i);
			termsTable.setWidget(i+1, 0, new CheckBox());
			termsTable.setText(i+1, 1, term.getLatinString());
			termsTable.setText(i+1, 2, term.getChaldean().toString());
			termsTable.setText(i+1, 3, term.getPythagorean().toString());
			termsTable.setText(i+1, 4, term.getIa().toString());
			termsTable.setText(i+1, 5, term.getNaeq().toString());
			termsTable.setText(i+1, 6, term.getTq().toString());
		}
	}

	public int getClickedRow(final ClickEvent event) {
		int selectedRow = -1;
		final HTMLTable.Cell cell = termsTable.getCellForEvent(event);
		if (cell != null) {
			// suppress clicks when check box is selected
			if (cell.getCellIndex() > 0) {
				selectedRow = cell.getRowIndex() - 1;
			}
		}
		return selectedRow;
	}

	public List<Integer> getSelectedRows() {
		final List<Integer> selectedRows = new ArrayList<Integer>();
		for (int i = 0; i < termsTable.getRowCount(); i++) {
			final CheckBox checkBox = (CheckBox) termsTable.getWidget(i, 0);
			if (checkBox != null && checkBox.getValue()) {
				selectedRows.add(i-1);
			}
		}
		return selectedRows;
	}

	public Widget asWidget() {
		return this;
	}
}
