package arithmea.client.presenter;

import arithmea.client.event.CancelledEvent;
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

	public ParseTextPresenter(final ArithmeaServiceAsync rpcService,
			final HandlerManager eventBus, final Display view) {
		this.rpcService = rpcService;
		this.eventBus = eventBus;
		this.display = view;
	}

	public void bind() {
		this.display.getCancelButton().addClickHandler(new ClickHandler() {
			public void onClick(final ClickEvent event) {
				eventBus.fireEvent(new CancelledEvent());
			}
		});
		this.display.getParseButton().addClickHandler(new ClickHandler() {
			public void onClick(final ClickEvent event) {
				doParse();
			}
		});
	}
	
	protected void doParse() {
		String input = this.display.getTextArea().getText();
		rpcService.parseTerms(input, new AsyncCallback<String>() {
				public void onSuccess(String result) {
					display.getStatus().setText(result);
				}
				public void onFailure(Throwable caught) {
					display.getStatus().setText("Fail parsing text.");
				}
			}
		);
	}

	public void go(final HasWidgets container) {
		bind();
		container.clear();
		container.add(display.asWidget());
		display.getTextArea().setFocus(true);
	}
}
