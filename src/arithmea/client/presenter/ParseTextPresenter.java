package arithmea.client.presenter;

import arithmea.client.event.ShowListEvent;
import arithmea.client.service.ArithmeaServiceAsync;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.Widget;

/**
 * Presenter for the view to parse texts.
 */
public class ParseTextPresenter implements Presenter {

    public interface Display {
        HasClickHandlers getCancelButton();
        HasClickHandlers getParseButton();
        TextArea getTextArea();
        Label getStatus();
        Widget asWidget();
    }

    private final ArithmeaServiceAsync rpcService;
    private final HandlerManager eventBus;
    private final Display display;

    /**
     * Default constructor.
     * @param rpcService
     * @param eventBus
     * @param view
     */
    public ParseTextPresenter(final ArithmeaServiceAsync rpcService,
            final HandlerManager eventBus, final Display view) {
        this.rpcService = rpcService;
        this.eventBus = eventBus;
        this.display = view;
    }
    
    /**
     * Binds the handlers to the elements from the view.
     */
    public final void bind() {
        this.display.getCancelButton().addClickHandler(new ClickHandler() {
            public void onClick(final ClickEvent event) {
                eventBus.fireEvent(new ShowListEvent());
            }
        });
        this.display.getParseButton().addClickHandler(new ClickHandler() {
            public void onClick(final ClickEvent event) {
                doParse();
            }
        });
    }

    /**
     * Sends the entered text to the service for parsing.
     */
    protected final void doParse() {
        String input = this.display.getTextArea().getText();
        rpcService.parseTerms(input, new AsyncCallback<String>() {
                public void onSuccess(final String result) {
                    display.getStatus().setText(result);
                }
                public void onFailure(final Throwable caught) {
                    display.getStatus().setText("Fail parsing text.");
                }
            }
        );
    }
    
    /**
     * Initializes the container.
     */
    public final void go(final HasWidgets container) {
        bind();
        container.clear();
        container.add(display.asWidget());
        display.getTextArea().setFocus(true);
    }
}
