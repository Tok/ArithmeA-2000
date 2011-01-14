package arithmea.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.RootPanel;

public class Arithmea implements EntryPoint {

  public void onModuleLoad() {
    ArithmeaServiceAsync rpcService = GWT.create(ArithmeaService.class);
    HandlerManager eventBus = new HandlerManager(null);
    AppController appViewer = new AppController(rpcService, eventBus);
    appViewer.go(RootPanel.get());
  }
}
