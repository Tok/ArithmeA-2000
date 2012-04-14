package arithmea.client.presenter;

import arithmea.client.event.ShowListEvent;
import arithmea.client.service.ArithmeaServiceAsync;
import arithmea.client.widgets.tree.HebrewTreeWidget;
import arithmea.client.widgets.tree.LatinTreeWidget;
import arithmea.shared.data.Term;
import arithmea.shared.gematria.GematriaMethod;
import arithmea.shared.gematria.HebrewMethod;
import arithmea.shared.gematria.LatinMethod;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

/**
 * Presenter to control the view for editing words.
 */
public class EditTermPresenter implements Presenter {

    public interface Display {
        HasClickHandlers getSaveButton();
        HasClickHandlers getCancelButton();
        HasValue<String> getInputText();
        HebrewTreeWidget getHebrewTree();
        LatinTreeWidget getLatinTree();
        TextBox getInputTextBox();
        HasValue<String> getLatinString();
        HasValue<String> getHebrewString();
        HasValue<String> get(GematriaMethod gm);
        Widget asWidget();
    }

    private Term term;
    private final ArithmeaServiceAsync rpcService;
    private final HandlerManager eventBus;
    private final Display display;

    /**
     * Default constructor that doesn't accept and id.
     * @param rpcService
     * @param eventBus
     * @param display
     */
    public EditTermPresenter(final ArithmeaServiceAsync rpcService, final HandlerManager eventBus, final Display display) {
        this.rpcService = rpcService;
        this.eventBus = eventBus;
        this.term = new Term();
        this.display = display;
        bind();
    }

    /**
     * Constructor that accepts an id and retrieves the corresponding term.
     * @param rpcService
     * @param eventBus
     * @param display
     * @param id
     */
    public EditTermPresenter(final ArithmeaServiceAsync rpcService, final HandlerManager eventBus, final Display display, final String id) {
        this.rpcService = rpcService;
        this.eventBus = eventBus;
        this.display = display;
        bind();
        rpcService.getTerm(id, new AsyncCallback<Term>() {
            public void onSuccess(final Term result) {
                term = result;
                EditTermPresenter.this.display.getLatinString().setValue(term.getLatinString());
                EditTermPresenter.this.display.getHebrewString().setValue(term.getHebrewString());
                for (LatinMethod gm : LatinMethod.values()) {
                    EditTermPresenter.this.display.get(gm).setValue(term.get(gm).toString());
                }
                for (HebrewMethod gm : HebrewMethod.values()) {
                    EditTermPresenter.this.display.get(gm).setValue(term.get(gm).toString());
                }
            }
            public void onFailure(final Throwable caught) {
                Window.alert("Fail retrieving term");
            }
        });
    }

    /**
     * Defines the bindings for the buttons from the view.
     */
    public final void bind() {
        this.display.getSaveButton().addClickHandler(new ClickHandler() {
            public void onClick(final ClickEvent event) {
                doSave();
            }
        });
        this.display.getCancelButton().addClickHandler(new ClickHandler() {
            public void onClick(final ClickEvent event) {
                eventBus.fireEvent(new ShowListEvent());
            }
        });
    }

    /**
     * Initializes the container and sets the initial focus.
     */
    public final void go(final HasWidgets container) {
        container.clear();
        container.add(display.asWidget());
        display.getInputTextBox().setFocus(true);
    }

    /**
     * Creates and saves a term from the TextBox with the latin String.
     */
    private void doSave() {
        try {
            String latinString = display.getLatinString().getValue();
            final Term term = new Term(latinString);
            rpcService.updateTerm(term, new AsyncCallback<Term>() {
                public void onSuccess(final Term result) {
                    eventBus.fireEvent(new ShowListEvent());
                }
                public void onFailure(final Throwable caught) {
                    Window.alert("Fail updating Term");
                }
            });
        } catch (IllegalArgumentException iae) {
            Window.alert(iae.getMessage());
        }
    }

}
