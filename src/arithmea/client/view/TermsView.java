package arithmea.client.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import arithmea.client.presenter.TermsPresenter;
import arithmea.shared.data.Term;
import arithmea.shared.gematria.GematriaMethod;
import arithmea.shared.gematria.HebrewMethod;
import arithmea.shared.gematria.LatinMethod;

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
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class TermsView extends Composite implements TermsPresenter.Display {
	private final Button addButton;
	private final Button deleteButton;	
	private final Anchor latinHeader = new Anchor("Latin");
	private final Anchor hebrewHeader = new Anchor("Hebrew");
	
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
		deleteButton = new Button("Delete Word");
		hPanel.add(deleteButton);
		addButton = new Button("Add New Word");
		hPanel.add(addButton);
		contentTable.getCellFormatter().addStyleName(0, 0, "menu-table");
		contentTable.setWidget(0, 0, hPanel);

		// prepare table headers
		latinHeader.setStyleName("table-header");
		hebrewHeader.setStyleName("table-header");
		for (final LatinMethod gm : LatinMethod.values()) {
			Anchor anchor = new Anchor(gm.name());
			anchor.setStyleName("table-header");
			headers.put(gm, anchor);
		}
		for (final HebrewMethod gm : HebrewMethod.values()) {
			Anchor anchor = new Anchor(gm.name());
			anchor.setStyleName("table-header");
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
	
	public HasClickHandlers getHeader(LatinMethod gm) {
		return headers.get(gm);
	}
	
	public HasClickHandlers getList() {
		return termsTable;
	}

	public void setData(final List<Term> terms) {
		termsTable.removeAllRows();

		//set headers
		addWidgetToTable(0, 1, latinHeader, false);
		int col = 2;
		for (final LatinMethod gm : LatinMethod.values()) {
			addWidgetToTable(0, col, headers.get(gm), false);
			col++;
		}
		addWidgetToTable(0, col, hebrewHeader, false);
		col++;
		for (final HebrewMethod gm : HebrewMethod.values()) {
			addWidgetToTable(0, col, headers.get(gm), false);
			col++;
		}
		
		//set data
		for (int row = 0; row < terms.size(); ++row) {
			final Term term = terms.get(row);
			addWidgetToTable(row+1, 0, new CheckBox(), false);
			Anchor latinAnchor = new Anchor(term.getLatinString());
			latinAnchor.setHref("?word=" + term.getLatinString() + "#add");
			addWidgetToTable(row+1, 1, latinAnchor, false);
			int column = 2;
			for (LatinMethod gm : LatinMethod.values()) {
				Anchor anchor = prepareContentAnchor(gm.name(), term.get(gm));
				addWidgetToTable(row+1, column, anchor, true);
				column++;
			}
			addWidgetToTable(row+1, column, new Label(term.getHebrewString()), true);
			column++;
			for (HebrewMethod gm : HebrewMethod.values()) {
				Anchor anchor = prepareContentAnchor(gm.name(), term.get(gm));
				addWidgetToTable(row+1, column, anchor, true);
				column++;
			}
		}
	}
	
	private void addWidgetToTable(int row, int column, Widget widget, boolean alignRight) {
		termsTable.setWidget(row, column, widget);
		termsTable.getCellFormatter().addStyleName(row, column, "border-cell");
		if (alignRight) {
			termsTable.getCellFormatter().setAlignment(row, column,
					HasHorizontalAlignment.ALIGN_RIGHT,
					HasVerticalAlignment.ALIGN_MIDDLE);
		}
	}
	
	private Anchor prepareContentAnchor(String methodName, int number) {
		Anchor anchor = new Anchor(String.valueOf(number));
		anchor.setHref("?method="+methodName+"&number="+number+"#show");
		return anchor;
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

	@Override
	public HasClickHandlers getLatinHeader() {
		return latinHeader;
	}

	@Override
	public HasClickHandlers getHebrewHeader() {
		return hebrewHeader;
	}

	@Override
	public HasClickHandlers getGematriaHeader(GematriaMethod gm) {
		return headers.get(gm);
	}
}
