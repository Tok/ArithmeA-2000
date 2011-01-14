package arithmea.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.RootPanel;

public class Arithmea implements EntryPoint {

	public void onModuleLoad() {
		ArithmeaServiceAsync rpcService = GWT.create(ArithmeaService.class);
		HandlerManager eventBus = new HandlerManager(null);
		AppController appViewer = new AppController(rpcService, eventBus);

		Image titleImage = new Image("/images/Title.png");
		titleImage.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				redirect("/");
			}
		});

		RootPanel.get("title").add(titleImage);
		appViewer.go(RootPanel.get("content"));
	}

	native void redirect(final String url)
	/*-{
		$wnd.location.replace(url);
	}-*/;
}
