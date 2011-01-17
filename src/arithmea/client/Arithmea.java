package arithmea.client;

import arithmea.client.event.CancelledEvent;
import arithmea.client.service.ArithmeaService;
import arithmea.client.service.ArithmeaServiceAsync;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.RootPanel;

public class Arithmea implements EntryPoint {

	public void onModuleLoad() {
		final ArithmeaServiceAsync rpcService = GWT.create(ArithmeaService.class);
		final HandlerManager eventBus = new HandlerManager(null);
		final AppController appViewer = new AppController(rpcService, eventBus);

		Image titleImage = new Image("/images/Title.png");
		titleImage.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				eventBus.fireEvent(new CancelledEvent());
			}
		});

		RootPanel.get("title").add(titleImage);
		appViewer.go(RootPanel.get("content"));
	}

}
