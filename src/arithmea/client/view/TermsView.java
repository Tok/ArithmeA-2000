package arithmea.client.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import arithmea.client.event.AddTermEvent;
import arithmea.client.event.ShowListEvent;
import arithmea.client.event.ShowNumberEvent;
import arithmea.client.presenter.TermsPresenter;
import arithmea.shared.data.Highlight;
import arithmea.shared.data.Term;
import arithmea.shared.gematria.GematriaMethod;
import arithmea.shared.gematria.HebrewMethod;
import arithmea.shared.gematria.LatinLetter;
import arithmea.shared.gematria.LatinMethod;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
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

/**
 * View for the terms list.
 */
public class TermsView extends Composite implements TermsPresenter.Display {
    private static final int NUMBER_OF_ROWS = 30;
    private final Button addButton;
    private final Button showNumbersButton;
    private final Button parseTextButton;
    private final Button deleteButton;
    private final Button showInfoButton;
    private final ListBox letterBox;
    private final Anchor latinHeader = new Anchor("Latin");
    private final Anchor hebrewHeader = new Anchor("Hebrew");
    private final Map<GematriaMethod, Anchor> headers = new HashMap<GematriaMethod, Anchor>();
    private final FlexTable termsTable;
    private final FlexTable contentTable;
    private final HandlerManager eventBus;
    private String letter;
    private int offset;

    /**
     * Default constructor.
     * @param eventBus
     * @param letter
     * @param offset
     */
    public TermsView(final HandlerManager eventBus, final String letter, final int offset) {
        this.eventBus = eventBus;
        this.letter = letter;
        this.offset = offset;

        final DecoratorPanel contentTableDecorator = new DecoratorPanel();
        initWidget(contentTableDecorator);
        contentTableDecorator.setWidth("100%");
        contentTableDecorator.setWidth("800px");

        contentTable = new FlexTable();
        contentTable.setWidth("100%");

        // create the menu
        final HorizontalPanel hPanel = new HorizontalPanel();
        deleteButton = new Button("Delete");
        hPanel.add(deleteButton);
        showNumbersButton = new Button("Show by Numbers");
        hPanel.add(showNumbersButton);
        parseTextButton = new Button("Parse Text");
        hPanel.add(parseTextButton);
        addButton = new Button("Add New Word");
        hPanel.add(addButton);
        showInfoButton = new Button("Show Info");
        hPanel.add(showInfoButton);
        letterBox = new ListBox();
        letterBox.addItem("All");
        int index = 1;
        for (LatinLetter ll : LatinLetter.values()) {
            letterBox.addItem(ll.name());
            if (ll.name().equalsIgnoreCase(letter)) {
                letterBox.setSelectedIndex(index);
            }
            index++;
        }
        hPanel.add(letterBox);

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

        contentTable.getCellFormatter().setAlignment(1, 0,
                HasHorizontalAlignment.ALIGN_LEFT,
                HasVerticalAlignment.ALIGN_TOP);
        contentTable.getCellFormatter().setHeight(1, 0, "635px");
        contentTable.setWidget(1, 0, termsTable);

        contentTableDecorator.add(contentTable);
    }

    public final HasClickHandlers getAddButton() {
        return addButton;
    }

    public final HasClickHandlers getDeleteButton() {
        return deleteButton;
    }

    public final HasClickHandlers getShowInfoButton() {
        return showInfoButton;
    }

    public final HasClickHandlers getShowNumbersButton() {
        return showNumbersButton;
    }

    public final HasClickHandlers getParseTextButton() {
        return parseTextButton;
    }

    public final HasClickHandlers getHeader(final LatinMethod gm) {
        return headers.get(gm);
    }

    public final HasClickHandlers getList() {
        return termsTable;
    }

    /**
     * Sets the data from the provided terms.
     */
    public final void setData(final List<Term> terms) {
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
            addWidgetToTable(row + 1, 0, new CheckBox(), false);
            Anchor latinAnchor = new Anchor(term.getLatinString());

            latinAnchor.addClickHandler(new ClickHandler() {
                @Override
                public void onClick(final ClickEvent event) {
                    eventBus.fireEvent(new AddTermEvent(term.getLatinString()));
                }
            });
            latinAnchor.setHref("#add/" + term.getLatinString());

            addWidgetToTable(row + 1, 1, latinAnchor, false);
            int column = 2;
            for (LatinMethod gm : LatinMethod.values()) {
                int value = term.get(gm);
                Anchor anchor = prepareContentAnchor(gm.name(), value);
                doHighlight(anchor, value);
                addWidgetToTable(row + 1, column, anchor, true);
                column++;
            }
            addWidgetToTable(row + 1, column, new Label(term.getHebrewString()), true);
            column++;
            for (HebrewMethod gm : HebrewMethod.values()) {
                int value = term.get(gm);
                Anchor anchor = prepareContentAnchor(gm.name(), value);
                doHighlight(anchor, value);
                addWidgetToTable(row + 1, column, anchor, true);
                column++;
            }
        }

        //make pager
        FlowPanel pagerPanel = new FlowPanel();

        Anchor back = new Anchor("<<back");
        back.setStyleName("padding-right");
        Integer backOffset = offset - NUMBER_OF_ROWS;
        if (backOffset < 0) { backOffset = 0; }
        final Integer finalBackOffset = backOffset;
        back.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {
                eventBus.fireEvent(new ShowListEvent(letter, finalBackOffset));
            }
        });
        back.setHref("#list/" + letter + "/" + finalBackOffset);
        pagerPanel.add(back);

        Integer nextOffset = offset;
        if (row >= NUMBER_OF_ROWS) {
            nextOffset += NUMBER_OF_ROWS;
        }
        final Integer finalNextOffset = nextOffset;
        Anchor next = new Anchor("next>>");
        next.setStyleName("padding-right");
        next.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {
                eventBus.fireEvent(new ShowListEvent(letter, finalNextOffset));
            }
        });
        next.setHref("#list/" + letter + "/" + finalNextOffset);
        pagerPanel.add(next);

        addWidgetToTable(row + 1, 1, pagerPanel, false);
    }

    /**
     * Highlights the special gematria values with the corresponding css colors.
     * @param anchor
     * @param value
     */
    private void doHighlight(final Anchor anchor, final int value) {
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

    /**
     * Adds a Widget to the table.
     * @param row
     * @param column
     * @param widget
     * @param alignRight
     */
    private void addWidgetToTable(final int row, final int column,
            final Widget widget, final boolean alignRight) {
        termsTable.setWidget(row, column, widget);
        termsTable.getCellFormatter().addStyleName(row, column, "border-cell");
        if (alignRight) {
            termsTable.getCellFormatter().setAlignment(row, column,
                    HasHorizontalAlignment.ALIGN_RIGHT,
                    HasVerticalAlignment.ALIGN_MIDDLE);
        }
    }

    /**
     * Prepares an Achor for the provided method and number.
     * @param methodName
     * @param number
     * @return the prepared Anchor
     */
    private Anchor prepareContentAnchor(final String methodName, final int number) {
        Anchor anchor = new Anchor(String.valueOf(number));
        anchor.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {
                eventBus.fireEvent(new ShowNumberEvent(methodName, String.valueOf(number)));
            }
        });
        anchor.setHref("#show/" + methodName.toLowerCase() + "/" + number);
        return anchor;
    }

    /**
     * Returns a list with the selected rows.
     */
    public final List<Integer> getSelectedRows() {
        final List<Integer> selectedRows = new ArrayList<Integer>();
        for (int i = 0; i < termsTable.getRowCount(); i++) {
            final CheckBox checkBox = (CheckBox) termsTable.getWidget(i, 0);
            if (checkBox != null && checkBox.getValue()) {
                selectedRows.add(i - 1);
            }
        }
        return selectedRows;
    }

    public final Widget asWidget() {
        return this;
    }

    @Override
    public final HasClickHandlers getLatinHeader() {
        return latinHeader;
    }

    @Override
    public final HasClickHandlers getHebrewHeader() {
        return hebrewHeader;
    }

    @Override
    public final HasClickHandlers getGematriaHeader(final GematriaMethod gm) {
        return headers.get(gm);
    }

    @Override
    public final ListBox getLetterBox() {
        return letterBox;
    }

    @Override
    public final int getOffset() {
        return offset;
    }

    @Override
    public final void setOffset(final int offset) {
        this.offset = offset;
    }

    @Override
    public final FlexTable getTermsTable() {
        return termsTable;
    }

    @Override
    public final void setLetter(final String letter) {
        this.letter = letter;
    }
}
