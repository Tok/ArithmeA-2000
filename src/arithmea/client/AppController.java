package arithmea.client;

import arithmea.client.event.AddTermEvent;
import arithmea.client.event.AddTermEventHandler;
import arithmea.client.event.EditTermCancelledEvent;
import arithmea.client.event.EditTermCancelledEventHandler;
import arithmea.client.event.EditTermEvent;
import arithmea.client.event.EditTermEventHandler;
import arithmea.client.event.TermUpdatedEvent;
import arithmea.client.event.TermUpdatedEventHandler;
import arithmea.client.presenter.EditTermPresenter;
import arithmea.client.presenter.Presenter;
import arithmea.client.presenter.TermsPresenter;
import arithmea.client.view.EditTermView;
import arithmea.client.view.TermsView;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.HasWidgets;

public class AppController implements Presenter, ValueChangeHandler<String> {
	private final HandlerManager eventBus;
	private final ArithmeaServiceAsync rpcService;
	private HasWidgets container;

	public AppController(final ArithmeaServiceAsync rpcService,
			final HandlerManager eventBus) {
		this.eventBus = eventBus;
		this.rpcService = rpcService;
		bind();
	}

	private void bind() {
		History.addValueChangeHandler(this);

		eventBus.addHandler(AddTermEvent.TYPE, new AddTermEventHandler() {
			public void onAddTerm(AddTermEvent event) {
				doAddNewTerm();
			}
		});

		eventBus.addHandler(EditTermEvent.TYPE, new EditTermEventHandler() {
			public void onEditTerm(EditTermEvent event) {
				doEditTerm(event.getId());
			}
		});

		eventBus.addHandler(EditTermCancelledEvent.TYPE,
				new EditTermCancelledEventHandler() {
					public void onEditTermCancelled(EditTermCancelledEvent event) {
						doEditTermCancelled();
					}
				});

		eventBus.addHandler(TermUpdatedEvent.TYPE,
				new TermUpdatedEventHandler() {
					public void onTermUpdated(TermUpdatedEvent event) {
						doTermUpdated();
					}
				});
	}

	private void doAddNewTerm() {
		History.newItem("add");
	}

	private void doEditTerm(final String id) {
		History.newItem("edit", false);
		Presenter presenter = new EditTermPresenter(rpcService, eventBus,
				new EditTermView(), id);
		presenter.go(container);
	}

	private void doEditTermCancelled() {
		History.newItem("list");
	}

	private void doTermUpdated() {
		History.newItem("list");
	}

	public void go(final HasWidgets container) {
		this.container = container;
		if ("".equals(History.getToken())) {
			History.newItem("list");
		} else {
			History.fireCurrentHistoryState();
		}
	}

	public void onValueChange(final ValueChangeEvent<String> event) {
		final String token = event.getValue();
		if (token != null) {
			Presenter presenter = null;
			if (token.equals("list")) {
				presenter = new TermsPresenter(rpcService, eventBus,
						new TermsView());
			} else if (token.equals("add")) {
				presenter = new EditTermPresenter(rpcService, eventBus,
						new EditTermView());
			} else if (token.equals("edit")) {
				presenter = new EditTermPresenter(rpcService, eventBus,
						new EditTermView());
			}
			presenter.go(container);
		}
	}
}
