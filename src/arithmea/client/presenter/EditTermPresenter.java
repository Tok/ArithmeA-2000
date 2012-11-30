package arithmea.client.presenter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import arithmea.client.event.AddTermEvent;
import arithmea.client.event.ShowListEvent;
import arithmea.client.event.ShowNumberEvent;
import arithmea.client.service.ArithmeaServiceAsync;
import arithmea.client.widgets.LetterStarWidget;
import arithmea.client.widgets.tree.HebrewTreeWidget;
import arithmea.client.widgets.tree.LatinTreeWidget;
import arithmea.shared.data.Highlight;
import arithmea.shared.data.Term;
import arithmea.shared.gematria.GematriaMethod;
import arithmea.shared.gematria.HebrewMethod;
import arithmea.shared.gematria.LatinMethod;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;
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
        Panel getBusyPanel();
        Image getBusyImage();
        Image getTranspImage();
        Label getLatinLabel();
        Label getHebrewLabel();
        LetterStarWidget getLetterStar();
        HasValue<String> get(GematriaMethod gm);
        FlowPanel getMatchPanel(GematriaMethod gm);
        Label getMethodLabel(GematriaMethod gm);
        Anchor getAnchor(GematriaMethod gm);
        Map<GematriaMethod, TextBox> getValueBoxes();
        Widget asWidget();
    }

    private static final int TYPING_DELAY_MS = 1500;
    private static final int MATCHES_LIMIT = 15;
    private boolean isDirty = false; //denotes if input has changed since the last update of matches
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
        this(rpcService, eventBus, display);
        display.getInputTextBox().setText(id);
    }

    /**
     * Defines the bindings for the buttons from the view.
     */
    private void bind() {
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
        this.display.getInputTextBox().addValueChangeHandler(new ValueChangeHandler<String>() {
            @Override
            public void onValueChange(final ValueChangeEvent<String> event) {
                doChange();
            }
        });
        this.display.getInputTextBox().addKeyUpHandler(new KeyUpHandler() {
            @Override
            public void onKeyUp(final KeyUpEvent event) {
                doDirtyChange();
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
        doChange();
        updateAll();
    }

    /**
     * Processes changes in the input TextBox and delays the update of the matches.
     */
    private void doDirtyChange() {
        if (!isDirty) {
            display.getBusyPanel().clear();
            display.getBusyPanel().add(display.getBusyImage());
        }
        isDirty = true;
        doChange();
    }

    /**
     * Processes changes in the input TextBox.
     */
    private void doChange() {
        try {
            term = new Term(display.getInputTextBox().getText());
            display.getLatinLabel().setText(term.getLatinString());
            display.getHebrewLabel().setText(term.getHebrewString());
            // create new term and update view
            for (LatinMethod method : LatinMethod.values()) {
                prepareMethodAnchor(term, method);
                display.getMatchPanel(method).clear();
            }
            for (HebrewMethod method : HebrewMethod.values()) {
                prepareMethodAnchor(term, method);
                display.getMatchPanel(method).clear();
            }
            // update tree widgets
            display.getLatinTree().setWord(term.getLatinString());
            display.getHebrewTree().setWord(term.getHebrewString());
            // and the star widget
            display.getLetterStar().setWord(term.getLatinString());

            //wait before getting matches
            if (isDirty) { //delay possible update
                final Timer timer = new Timer() {
                    @Override
                    public void run() {
                        updateAllIfStillDirty();
                    }
                };
                timer.schedule(TYPING_DELAY_MS);
            } else { //update immediately
                updateAllIfStillDirty();
            }
        } catch (IllegalArgumentException iae) {
            display.getBusyPanel().clear();
            display.getBusyPanel().add(display.getTranspImage());
            display.getLatinLabel().setText("");
            display.getHebrewLabel().setText("");
            display.getLatinTree().setWord("");
            display.getHebrewTree().setWord("");
            display.getLetterStar().setWord("");
            for (LatinMethod method : LatinMethod.values()) {
                display.getAnchor(method).setText("");
                display.getMatchPanel(method).clear();
            }
            for (HebrewMethod method : HebrewMethod.values()) {
                display.getAnchor(method).setText("");
                display.getMatchPanel(method).clear();
            }
        }
    }

    /**
     * Prepares the anchors for the provided term and gematria method.
     * @param term
     * @param method
     */
    private void prepareMethodAnchor(final Term term, final GematriaMethod method) {
        final int value = term.get(method);
        final Map<GematriaMethod, TextBox> valueBoxes = display.getValueBoxes();
        valueBoxes.get(method).setText(String.valueOf(value));
        for (Highlight hl : Highlight.values()) {
            if (hl.getNumber() == value) {
                colorContents(method, hl.getColor(), hl.getNumberQuality());
                break;
            }
            if (hl.getNumber() > value) {
                colorContents(method, "", "");
                break;
            }
        }
        prepareNumberAnchor(display.getAnchor(method), String.valueOf(value), method, value);
    }

    /**
     * Prepares anchor to show matches for a number and method.
     */
    private Anchor prepareNumberAnchor(final Anchor anchor, final String text, final GematriaMethod method, final int value) {
        final String methodName = method.name();
        anchor.setText(text);
        anchor.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {
                eventBus.fireEvent(new ShowNumberEvent(methodName, String.valueOf(value)));
            }
        });
        anchor.setHref("#show/" + methodName.toLowerCase() + "/" + String.valueOf(value));
        return anchor;
    }

    /**
     * Sets the style and title for all cells in the row of the provided method.
     * @param method
     * @param style
     * @param title
     */
    private void colorContents(final GematriaMethod method, final String style, final String title) {
        display.getAnchor(method).setStyleName(style);
        display.getAnchor(method).setTitle(title);
        display.getMethodLabel(method).setStyleName(style);
        display.getMethodLabel(method).setTitle(title);
    }

    /**
     * Creates and saves a term from the TextBox with the latin String.
     */
    private void doSave() {
        try {
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

    /**
     * Updates all matches if still dirty.
     */
    private void updateAllIfStillDirty() {
        if (isDirty) {
            updateAll();
        }
    }

    /**
     * Updates all matches.
     */
    private void updateAll() {
        for (LatinMethod method : LatinMethod.values()) {
            updateMatches(method);
        }
        for (HebrewMethod method : HebrewMethod.values()) {
            updateMatches(method);
        }
        display.getBusyPanel().clear();
        display.getBusyPanel().add(display.getTranspImage());
        isDirty = false;
    }

    /**
     * Updates the matches for the provided method.
     */
    private void updateMatches(final GematriaMethod method) {
        if (term == null || term.get(method) == null) {
            try {
                term = new Term(display.getInputText().getValue());
            } catch (IllegalArgumentException iae) {
                return; //ignore
            }
        }
        final int number = term.get(method);
        final FlowPanel matchFlow = display.getMatchPanel(method);
        matchFlow.clear();
        rpcService.getTermsWithLimit(method.name(), number, MATCHES_LIMIT, new AsyncCallback<ArrayList<Term>>() {
            @Override
            public synchronized void onSuccess(final ArrayList<Term> result) {
                matchFlow.clear(); //clear again for concurrent updates
                Iterator<Term> it = result.iterator();
                if (!it.hasNext()) {
                    matchFlow.add(new Label("No matching words found."));
                } else {
                    int count = 0;
                    while (it.hasNext()) {
                        final Term term = it.next();
                        Anchor anchor = new Anchor(term.getLatinString() + " ");
                        anchor.addClickHandler(new ClickHandler() {
                            @Override
                            public void onClick(final ClickEvent event) {
                                eventBus.fireEvent(new AddTermEvent(term.getLatinString()));
                            }
                        });
                        anchor.setHref("#add/" + term.getLatinString());
                        anchor.setStyleName("padding-right");
                        matchFlow.add(anchor);
                        count++;
                    }
                    if (count == MATCHES_LIMIT) {
                        final Anchor moreAnchor = prepareNumberAnchor(new Anchor(), "...", method, term.get(method));
                        matchFlow.add(moreAnchor);
                    }
                }
            }
            public void onFailure(final Throwable caught) {
                Window.alert("Fail loading terms.");
            }
        });
    }

}
