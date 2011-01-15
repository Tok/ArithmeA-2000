package arithmea.client.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import arithmea.client.presenter.TermsPresenter;
import arithmea.shared.GematriaMethod;
import arithmea.shared.Term;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Widget;

public class TermsView extends Composite implements TermsPresenter.Display {
	private final Button addButton;
	private final Button deleteButton;	
	private final Anchor termHeader = new Anchor("Term");
	private final Map<GematriaMethod, Anchor> headers = new HashMap<GematriaMethod, Anchor>();
	private final FlexTable termsTable;
	private final FlexTable contentTable;

	public TermsView() {
		final DecoratorPanel contentTableDecorator = new DecoratorPanel();
		initWidget(contentTableDecorator);
		contentTableDecorator.setWidth("100%");
		contentTableDecorator.setWidth("600px");

		contentTable = new FlexTable();
		contentTable.setWidth("100%");

		// create the menu
		final HorizontalPanel hPanel = new HorizontalPanel();
		addButton = new Button("Add");
		hPanel.add(addButton);
		deleteButton = new Button("Delete");
		hPanel.add(deleteButton);
		contentTable.getCellFormatter().addStyleName(0, 0, "menu-table");
		contentTable.setWidget(0, 0, hPanel);

		// prepare table headers
		for (final GematriaMethod gm : GematriaMethod.values()) {
			Anchor anchor = new Anchor(gm.name());
			headers.put(gm, anchor);
		}
		
		// create the terms table
		termsTable = new FlexTable();
		termsTable.setWidth("100%");
		
		contentTable.setWidget(1, 0, termsTable);
		contentTableDecorator.add(contentTable);
	}

	public HasClickHandlers getAddButton() {
		return addButton;
	}

	public HasClickHandlers getDeleteButton() {
		return deleteButton;
	}
	
	public HasClickHandlers getHeader(GematriaMethod gm) {
		return headers.get(gm);
	}
	
	public HasClickHandlers getList() {
		return termsTable;
	}

	public void setData(final List<Term> terms) {
		termsTable.removeAllRows();

		//set headers
		termsTable.setWidget(0, 1, termHeader);
		termsTable.getCellFormatter().addStyleName(0, 1, "border-cell");
		int col = 2;
		for (final GematriaMethod gm : GematriaMethod.values()) {
			termsTable.setWidget(0, col, headers.get(gm));
			termsTable.getCellFormatter().addStyleName(0, col, "border-cell");
			col++;
		}

		//set data
		for (int row = 0; row < terms.size(); ++row) {
			final Term term = terms.get(row);
			termsTable.setWidget(row+1, 0, new CheckBox());
			termsTable.getCellFormatter().addStyleName(row+1, 0, "border-cell");
			termsTable.setText(row+1, 1, term.getLatinString());
			termsTable.getCellFormatter().addStyleName(row+1, 1, "border-cell");
			int column = 2;
			for (GematriaMethod gm : GematriaMethod.values()) {
				termsTable.setText(row+1, column, term.get(gm).toString());
				termsTable.getCellFormatter().addStyleName(row+1, column, "border-cell");
				termsTable.getCellFormatter().setAlignment(row+1, column,
						HasHorizontalAlignment.ALIGN_RIGHT,
						HasVerticalAlignment.ALIGN_MIDDLE);
				column++;
			}
		}
	}

//	public int getClickedRow(final ClickEvent event) {
//		int selectedRow = -1;
//		final HTMLTable.Cell cell = termsTable.getCellForEvent(event);
//		if (cell != null) {
//			// suppress clicks when check box is selected
//			if (cell.getCellIndex() > 0) {
//				selectedRow = cell.getRowIndex() - 1;
//			}
//		}
//		return selectedRow;
//	}

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

	@Override
	public HasClickHandlers getTermHeader() {
		return termHeader;
	}

	@Override
	public HasClickHandlers getGematriaHeader(GematriaMethod gm) {
		return headers.get(gm);
	}
}
