package arithmea.client.view;

import arithmea.client.presenter.ParseTextPresenter;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.Widget;

public class ParseTextView extends Composite implements ParseTextPresenter.Display {
    private final Button cancelButton;
    private final Button parseButton;
    private final Label statusLabel;
    private final TextArea textArea;
    private final FlexTable contentTable;

    public ParseTextView() {
        final DecoratorPanel contentTableDecorator = new DecoratorPanel();
        initWidget(contentTableDecorator);
        contentTableDecorator.setWidth("100%");
        contentTableDecorator.setWidth("800px");

        contentTable = new FlexTable();
        contentTable.setWidth("100%");

        final HorizontalPanel menuPanel = new HorizontalPanel();
        cancelButton = new Button("Cancel");
        menuPanel.add(cancelButton);
        parseButton = new Button("Parse Text");
        menuPanel.add(parseButton);

        contentTable.getCellFormatter().addStyleName(0, 0, "menu-table");
        contentTable.setWidget(0, 0, menuPanel);

        statusLabel = new Label("Waiting for input.");
        contentTable.setWidget(1, 0, statusLabel);
        contentTable.getCellFormatter().addStyleName(1, 0, "flow-panel");

        textArea = new TextArea();
        textArea.setWidth("100%");
        textArea.setHeight("400px");

        contentTable.setWidget(2, 0, textArea);
        contentTable.getCellFormatter().addStyleName(2, 0, "text-area");

        contentTable.getCellFormatter().setAlignment(2, 0,
                HasHorizontalAlignment.ALIGN_LEFT,
                HasVerticalAlignment.ALIGN_TOP);
        contentTable.getCellFormatter().setHeight(2, 0, "607px");

        contentTableDecorator.add(contentTable);
    }

    @Override
    public final HasClickHandlers getCancelButton() {
        return cancelButton;
    }

    @Override
    public final HasClickHandlers getParseButton() {
        return parseButton;
    }

    @Override
    public final Widget asWidget() {
        return this;
    }

    @Override
    public final TextArea getTextArea() {
        return textArea;
    }

    @Override
    public final Label getStatus() {
        return statusLabel;
    }
}
