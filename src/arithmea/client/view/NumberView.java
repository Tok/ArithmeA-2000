package arithmea.client.view;

import arithmea.client.presenter.NumberPresenter;
import arithmea.client.widgets.ExtendedTextBox;
import arithmea.shared.gematria.HebrewMethod;
import arithmea.shared.gematria.LatinMethod;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

/**
 * View to compare terms by gematria values.
 */
public class NumberView extends Composite implements NumberPresenter.Display {
    private final Button cancelButton;
    private final ExtendedTextBox numberBox;
    private final ListBox methodBox;
    private final Button showButton;
    private final FlowPanel similarWords = new FlowPanel();
    private final FlexTable contentTable;

    /**
     * Default construtor.
     * @param method
     * @param number
     */
    public NumberView(final String method, final String number) {
        final DecoratorPanel contentTableDecorator = new DecoratorPanel();
        initWidget(contentTableDecorator);
        contentTableDecorator.setWidth("100%");
        contentTableDecorator.setWidth("800px");

        contentTable = new FlexTable();
        contentTable.setWidth("100%");

        final HorizontalPanel hPanel = new HorizontalPanel();
        hPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
        cancelButton = new Button("Cancel");
        hPanel.add(cancelButton);
        numberBox = new ExtendedTextBox();
        numberBox.setText(number);

        hPanel.add(numberBox);
        methodBox = new ListBox();
        int index = 0;
        for (LatinMethod lm : LatinMethod.values()) {
            methodBox.addItem(lm.name());
            if (lm.name().equalsIgnoreCase(method)) {
                methodBox.setSelectedIndex(index);
            }
            index++;
        }
        for (HebrewMethod hm : HebrewMethod.values()) {
            methodBox.addItem(hm.name());
            if (hm.name().equalsIgnoreCase(method)) {
                methodBox.setSelectedIndex(index);
            }
            index++;
        }
        hPanel.add(methodBox);
        showButton = new Button("Show");
        hPanel.add(showButton);

        contentTable.getCellFormatter().addStyleName(0, 0, "menu-table");
        contentTable.setWidget(0, 0, hPanel);

        similarWords.setWidth("776");
        similarWords.setStyleName("flow-panel");
        contentTable.setWidget(1, 0, similarWords);
        contentTable.getCellFormatter().setAlignment(1, 0,
                HasHorizontalAlignment.ALIGN_LEFT,
                HasVerticalAlignment.ALIGN_TOP);
        contentTable.getCellFormatter().setHeight(1, 0, "631px");

        contentTableDecorator.add(contentTable);
    }

    @Override
    public final ListBox getGematriaMethod() {
        return methodBox;
    }

    @Override
    public final TextBox getNumber() {
        return numberBox;
    }

    @Override
    public final HasClickHandlers getCancelButton() {
        return cancelButton;
    }

    @Override
    public final HasClickHandlers getShowButton() {
        return showButton;
    }

    @Override
    public final FlowPanel getSimilarWords() {
        return similarWords;
    }

    @Override
    public final Widget asWidget() {
        return this;
    }
}
