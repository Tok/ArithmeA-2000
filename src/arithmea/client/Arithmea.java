package arithmea.client;

import arithmea.client.event.ShowListEvent;
import arithmea.client.service.ArithmeaService;
import arithmea.client.service.ArithmeaServiceAsync;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * EntryPoint Class as defined in gwt.xml
 */
public class Arithmea implements EntryPoint {

    /**
     * Creates AppController and sets it to the content div from the html.
     */
    public final void onModuleLoad() {
        final ArithmeaServiceAsync rpcService = GWT.create(ArithmeaService.class);
        final HandlerManager eventBus = new HandlerManager(null);
        final AppController appViewer = new AppController(rpcService, eventBus);
        final Image titleImage = new Image("/images/ArithmeaTitle.png");
        titleImage.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {
                eventBus.fireEvent(new ShowListEvent());
            }
        });
        RootPanel.get("title").add(titleImage);
        appViewer.go(RootPanel.get("content"));
    }

}
