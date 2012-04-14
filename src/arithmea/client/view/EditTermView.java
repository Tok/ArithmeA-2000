package arithmea.client.view;

import java.util.HashMap;
import java.util.Map;

import arithmea.client.event.ShowNumberEvent;
import arithmea.client.presenter.EditTermPresenter;
import arithmea.client.widgets.ExtendedTextBox;
import arithmea.client.widgets.LetterStarWidget;
import arithmea.client.widgets.tree.HebrewTreeWidget;
import arithmea.client.widgets.tree.LatinTreeWidget;
import arithmea.shared.data.Highlight;
import arithmea.shared.data.Term;
import arithmea.shared.gematria.GematriaMethod;
import arithmea.shared.gematria.HebrewMethod;
import arithmea.shared.gematria.LatinMethod;
import arithmea.shared.qabalah.SephirothData;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * View to edit and add new terms.
 */
public class EditTermView extends Composite implements
        EditTermPresenter.Display {
    private final HorizontalPanel treePanel = new HorizontalPanel();
    private final HebrewTreeWidget hebrewTree = new HebrewTreeWidget(SephirothData.WIDTH, SephirothData.HEIGHT);
    private final LatinTreeWidget latinTree = new LatinTreeWidget(SephirothData.WIDTH, SephirothData.HEIGHT);
    private final LetterStarWidget letterStar = new LetterStarWidget(300, 300);

    private final ExtendedTextBox inputTextBox;
    private final Label latinString;
    private final Label hebrewString;
    private final Map<GematriaMethod, Anchor> anchors = new HashMap<GematriaMethod, Anchor>();
    private final Map<GematriaMethod, TextBox> textBoxes = new HashMap<GematriaMethod, TextBox>();
    private final FlexTable detailsTable;
    private final Button saveButton;
    private final Button cancelButton;

    private final HandlerManager eventBus;

    /**
     * Default constructor
     * @param eventBus
     * @param word
     */
    public EditTermView(final HandlerManager eventBus, final String word) {
        this.eventBus = eventBus;
        final DecoratorPanel contentDetailsDecorator = new DecoratorPanel();
        contentDetailsDecorator.setWidth("800px");
        initWidget(contentDetailsDecorator);

        final VerticalPanel contentDetailsPanel = new VerticalPanel();

        final FlexTable menuTable = new FlexTable();
        menuTable.setWidth("778px");
        final HorizontalPanel hPanel = new HorizontalPanel();
        hPanel.setWidth("778px");

        final FlowPanel buttonFlow = new FlowPanel();
        cancelButton = new Button("Cancel Input");
        buttonFlow.add(cancelButton);
        saveButton = new Button("Save Word");
        buttonFlow.add(saveButton);

        hPanel.add(buttonFlow);
        inputTextBox = new ExtendedTextBox();
        inputTextBox.setWidth("580px");
        inputTextBox.addValueChangeHandler(new ValueChangeHandler<String>() {
            @Override
            public void onValueChange(final ValueChangeEvent<String> event) {
                doChange();
            }
        });
        inputTextBox.addKeyUpHandler(new KeyUpHandler() {
            @Override
            public void onKeyUp(final KeyUpEvent event) {
                doChange();
            }
        });
        hPanel.add(inputTextBox);

        menuTable.getCellFormatter().addStyleName(0, 0, "menu-table");
        menuTable.setWidget(0, 0, hPanel);
        contentDetailsPanel.add(menuTable);

        detailsTable = new FlexTable();
        detailsTable.setWidth("790px");
        latinString = new Label();
        latinString.setWidth("280px");
        latinString.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
        latinString.setStyleName("latin-label");
        hebrewString = new Label();
        hebrewString.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
        hebrewString.setStyleName("hebrew-label");
        for (final LatinMethod method : LatinMethod.values()) {
            final TextBox textBox = new TextBox();
            final Anchor anchor = new Anchor();
            textBox.addValueChangeHandler(new ValueChangeHandler<String>() {
                @Override
                public void onValueChange(final ValueChangeEvent<String> event) {
                    doChange();
                }
            });
            textBoxes.put(method, textBox);
            anchors.put(method, anchor);
        }
        for (final HebrewMethod method : HebrewMethod.values()) {
            final TextBox textBox = new TextBox();
            final Anchor anchor = new Anchor();
            textBox.addValueChangeHandler(new ValueChangeHandler<String>() {
                @Override
                public void onValueChange(final ValueChangeEvent<String> event) {
                    doChange();
                }
            });
            textBoxes.put(method, textBox);
            anchors.put(method, anchor);
        }
        initDetailsTable();
        contentDetailsPanel.add(detailsTable);
        treePanel.add(hebrewTree);
        treePanel.add(latinTree);
        treePanel.add(letterStar);
        contentDetailsPanel.add(treePanel);
        if (word != null && !word.equals("")) {
            inputTextBox.setText(word);
            doChange();
        }
        contentDetailsPanel.setHeight("671px");
        contentDetailsDecorator.add(contentDetailsPanel);
    }

    /**
     * Processes changes in the input TextBox.
     */
    private void doChange() {
        try {
            final Term term = new Term(inputTextBox.getText());
            latinString.setText(term.getLatinString());
            hebrewString.setText(term.getHebrewString());
            // create new term and update view
            for (LatinMethod method : LatinMethod.values()) {
                prepareMethodAnchor(term, method);
            }
            for (HebrewMethod method : HebrewMethod.values()) {
                prepareMethodAnchor(term, method);
            }
            // update tree widgets
            hebrewTree.setWord(term.getHebrewString());
            latinTree.setWord(term.getLatinString());
            // and the star widget
            letterStar.setWord(term.getLatinString());
        } catch (IllegalArgumentException iae) {
            latinString.setText("");
            hebrewString.setText("");
            hebrewTree.setWord("");
            latinTree.setWord("");
            letterStar.setWord("");
        }
    }

    /**
     * Prepares the anchors for the provided tern and gematria method.
     * @param term
     * @param method
     */
    private void prepareMethodAnchor(final Term term, final GematriaMethod method) {
        final String methodName = method.name();
        final int value = term.get(method);
        textBoxes.get(method).setText(String.valueOf(value));
        for (Highlight hl : Highlight.values()) {
            if (hl.getNumber() == value) {
                anchors.get(method).setStyleName(hl.getColor());
                anchors.get(method).setTitle(hl.getNumberQuality());
                break;
            }
            if (hl.getNumber() > value) {
                anchors.get(method).setStyleName("");
                break;
            }
        }
        anchors.get(method).setText(String.valueOf(value));
        anchors.get(method).addClickHandler(new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {
                eventBus.fireEvent(new ShowNumberEvent(methodName, String.valueOf(value)));
            }
        });
        anchors.get(method).setHref("#show/" + methodName.toLowerCase() + "/" + String.valueOf(value));
    }

    /**
     * Initializes the details table.
     */
    private void initDetailsTable() {
        detailsTable.setWidget(0, 1, latinString);
        detailsTable.getCellFormatter().addStyleName(0, 1, "text-table");
        int row = 1;
        for (LatinMethod gm : LatinMethod.values()) {
            addRow(detailsTable, row, gm.name(), anchors.get(gm));
            row++;
        }
        detailsTable.setWidget(row, 1, hebrewString);
        detailsTable.getCellFormatter().addStyleName(row, 1, "text-table");
        row++;
        for (HebrewMethod method : HebrewMethod.values()) {
            addRow(detailsTable, row, method.name(), anchors.get(method));
            row++;
        }
    }

    /**
     * Adds a row to the table.
     * @param table
     * @param row
     * @param description
     * @param anchor
     */
    private void addRow(final FlexTable table, final int row,
            final String description, final Anchor anchor) {
        table.setWidget(row, 0, new Label(description));
        table.getCellFormatter().addStyleName(row, 0, "border-cell");
        table.setWidget(row, 1, anchor);
        table.getCellFormatter().addStyleName(row, 1, "border-cell");
        table.getCellFormatter().setAlignment(row, 1,
                HasHorizontalAlignment.ALIGN_RIGHT,
                HasVerticalAlignment.ALIGN_MIDDLE);
    }

    public final HasValue<String> getInputText() {
        return inputTextBox;
    }

    public final TextBox getInputTextBox() {
        return inputTextBox;
    }

    public final HasValue<String> getHebrewString() {
        //TODO remove textbox
        TextBox textBox = new TextBox();
        textBox.setValue(hebrewString.getText());
        return textBox;
    }

    public final HasValue<String> getLatinString() {
        //TODO remove textbox
        TextBox textBox = new TextBox();
        textBox.setValue(latinString.getText());
        return textBox;
    }

    public final HasClickHandlers getSaveButton() {
        return saveButton;
    }

    public final HasClickHandlers getCancelButton() {
        return cancelButton;
    }

    public final Widget asWidget() {
        return this;
    }

    @Override
    public final HasValue<String> get(final GematriaMethod gm) {
        return textBoxes.get(gm);
    }

    @Override
    public final HebrewTreeWidget getHebrewTree() {
        return hebrewTree;
    }

    @Override
    public final LatinTreeWidget getLatinTree() {
        return latinTree;
    }

}
