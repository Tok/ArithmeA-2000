package arithmea.client.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import arithmea.client.presenter.TermsPresenter;
import arithmea.shared.data.Highlight;
import arithmea.shared.data.Term;
import arithmea.shared.gematria.GematriaMethod;
import arithmea.shared.gematria.HebrewMethod;
import arithmea.shared.gematria.LatinLetter;
import arithmea.shared.gematria.LatinMethod;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Widget;

public class TermsView extends Composite implements TermsPresenter.Display {
	private final Button addButton;
	private final Button showNumbersButton;
	private final Button parseTextButton;
	private final Button deleteButton;	
	private final ListBox letterBox;
	private final CheckBox highlightCheckBox = new CheckBox("Highlight");
	private final Anchor latinHeader = new Anchor("Latin");
	private final Anchor hebrewHeader = new Anchor("Hebrew");
	
	private final Map<GematriaMethod, Anchor> headers = new HashMap<GematriaMethod, Anchor>();
	
	private final FlexTable termsTable;
	private final FlexTable contentTable;
	
	private String letter;
	private int offset;
	private boolean highlight;

	public TermsView() {
		try {
			letter = com.google.gwt.user.client.Window.Location.getParameter("letter");
			offset = Integer.valueOf(com.google.gwt.user.client.Window.Location.getParameter("offset"));
			highlight = Boolean.valueOf(com.google.gwt.user.client.Window.Location.getParameter("highlight"));
		} catch (NumberFormatException nfe) {
			offset = 0;
		}
		
		final DecoratorPanel contentTableDecorator = new DecoratorPanel();
		initWidget(contentTableDecorator);
		contentTableDecorator.setWidth("100%");
		contentTableDecorator.setWidth("800px");

		contentTable = new FlexTable();
		contentTable.setWidth("100%");

		// create the menu
		final HorizontalPanel hPanel = new HorizontalPanel();
		deleteButton = new Button("Delete Word");
		hPanel.add(deleteButton);
		showNumbersButton = new Button("Show by Numbers");
		hPanel.add(showNumbersButton);
		parseTextButton = new Button("Parse Text");
		hPanel.add(parseTextButton);
		addButton = new Button("Add New Word");
		hPanel.add(addButton);
		letterBox = new ListBox();
		letterBox.addItem("All");
		int index = 0;
		for (LatinLetter ll : LatinLetter.values()) {
			letterBox.addItem(ll.name());
			if (ll.name().equalsIgnoreCase(letter)) {
				letterBox.setSelectedIndex(index);
			}
			index++;
		}
		hPanel.add(letterBox);
		
		highlightCheckBox.setValue(highlight);
		highlightCheckBox.setStyleName("black");
		hPanel.add(highlightCheckBox);

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
	
	public HasClickHandlers getShowNumbersButton() {
		return showNumbersButton;
	}
	
	public HasClickHandlers getParseTextButton() {
		return parseTextButton;
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
		int row;
		for (row = 0; row < terms.size(); ++row) {
			final Term term = terms.get(row);
			addWidgetToTable(row+1, 0, new CheckBox(), false);
			Anchor latinAnchor = new Anchor(term.getLatinString());
			latinAnchor.setHref("?word=" + term.getLatinString() + "#add");
			addWidgetToTable(row+1, 1, latinAnchor, false);
			int column = 2;
			for (LatinMethod gm : LatinMethod.values()) {
				int value = term.get(gm);
				Anchor anchor = prepareContentAnchor(gm.name(), value);
				
				if (highlightCheckBox.getValue()) {
				for (Highlight hl : Highlight.values()) {
					if (hl.getNumber() == value) {
						anchor.setStyleName(hl.getColor());
						anchor.setTitle(hl.getNumberQuality());
						break;
					}
					if (hl.getNumber() > value) {
						anchor.setStyleName("");
						break;
					}
				}
				}
				
//				if (gm.equals(LatinMethod.Chaldean) || gm.equals(LatinMethod.Chaldean)) {
//					int value = term.get(gm);
//					if (value == 11 || value == 22 || value == 33) {
//						anchor.setStyleName("red");
//					}
//				} else {
//				}

				addWidgetToTable(row+1, column, anchor, true);
				column++;
			}
			addWidgetToTable(row+1, column, new Label(term.getHebrewString()), true);
			column++;
			for (HebrewMethod gm : HebrewMethod.values()) {
				int value = term.get(gm);
				Anchor anchor = prepareContentAnchor(gm.name(), value);
				
				if (highlightCheckBox.getValue()) {
				for (Highlight hl : Highlight.values()) {
					if (hl.getNumber() == value) {
						anchor.setStyleName(hl.getColor());
						anchor.setTitle(hl.getNumberQuality());
						break;
					}
					if (hl.getNumber() > value) {
						anchor.setStyleName("");
						break;
					}
				}
				}
				
				addWidgetToTable(row+1, column, anchor, true);
				column++;
			}
		}
		
		//make pager
		FlowPanel pagerPanel = new FlowPanel();
		
		Anchor back = new Anchor("<<back");
		back.setStyleName("padding-right");
		Integer backOffset = offset - 15;
		if (backOffset < 0) { backOffset = 0; }
		back.setHref("?letter=" + letter + "&offset=" + backOffset + "&highlight=" + highlightCheckBox.getValue() + "#list");
		pagerPanel.add(back);
		
		Integer nextOffset = offset;
		if (row >= 15) {
			nextOffset += 15;
		} 
		Anchor next = new Anchor("next>>");
		next.setStyleName("padding-right");
		next.setHref("?letter=" + letter + "&offset=" + nextOffset + "&highlight=" + highlightCheckBox.getValue() + "#list");
		pagerPanel.add(next);
		
		addWidgetToTable(row+1, 1, pagerPanel, false);
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
	
	@Override
	public ListBox getLetterBox() {
		return letterBox;
	}

	@Override
	public int getOffset() {
		return offset;
	}

	@Override
	public void setOffset(int offset) {
		this.offset = offset;
	}

	@Override
	public CheckBox getHighlightCheckBox() {
		return highlightCheckBox;
	}
}
