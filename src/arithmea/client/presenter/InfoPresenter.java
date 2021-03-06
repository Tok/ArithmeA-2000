package arithmea.client.presenter;

import arithmea.client.event.ShowListEvent;
import arithmea.client.service.ArithmeaServiceAsync;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

/**
 * Presenter for the Info view.
 */
public class InfoPresenter implements Presenter {

    public interface Display {
        HasClickHandlers getCancelButton();
        Widget asWidget();
    }

    @SuppressWarnings("unused")
    private final ArithmeaServiceAsync rpcService;
    private final HandlerManager eventBus;
    private final Display display;

    /**
     * Default constructor.
     * @param rpcService
     * @param eventBus
     * @param view
     */
    public InfoPresenter(final ArithmeaServiceAsync rpcService, final HandlerManager eventBus, final Display view) {
        this.rpcService = rpcService;
        this.eventBus = eventBus;
        this.display = view;
    }

    /**
     * Binds the handler to the button from the view.
     */
    public final void bind() {
        this.display.getCancelButton().addClickHandler(new ClickHandler() {
            public void onClick(final ClickEvent event) {
                eventBus.fireEvent(new ShowListEvent());
            }
        });
    }

    public final void go(final HasWidgets container) {
        bind();
        container.clear();
        container.add(display.asWidget());
    }
}
