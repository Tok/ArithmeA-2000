package arithmea.client.presenter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import arithmea.client.event.AddTermEvent;
import arithmea.client.event.ParseTextEvent;
import arithmea.client.event.ShowInfoEvent;
import arithmea.client.event.ShowNumberEvent;
import arithmea.client.service.ArithmeaServiceAsync;
import arithmea.client.sort.TermSortByGematriaMethod;
import arithmea.client.sort.TermSortByHebrewString;
import arithmea.client.sort.TermSortByLatinString;
import arithmea.shared.data.Term;
import arithmea.shared.gematria.GematriaMethod;
import arithmea.shared.gematria.HebrewMethod;
import arithmea.shared.gematria.LatinMethod;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Widget;

/**
 * Presenter for the list view.
 */
public class TermsPresenter implements Presenter {
    private List<Term> terms;

    public interface Display {
        HasClickHandlers getAddButton();
        HasClickHandlers getShowNumbersButton();
        HasClickHandlers getParseTextButton();
        HasClickHandlers getDeleteButton();
        HasClickHandlers getShowInfoButton();
        HasClickHandlers getFixButton();
        HasClickHandlers getList();
        HasClickHandlers getLatinHeader();
        HasClickHandlers getHebrewHeader();
        HasClickHandlers getGematriaHeader(GematriaMethod gm);
        void setData(List<Term> data);
        List<Integer> getSelectedRows();
        ListBox getLetterBox();
        int getOffset();
        void setOffset(int offset);
        Widget asWidget();
        FlexTable getTermsTable();
        void setLetter(String letter);
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
    public TermsPresenter(final ArithmeaServiceAsync rpcService, final HandlerManager eventBus, final Display view) {
        this.rpcService = rpcService;
        this.eventBus = eventBus;
        this.display = view;
    }

    /**
     * Binds the handlers to the elements from the view.
     */
    public final void bind() {
        display.getAddButton().addClickHandler(new ClickHandler() {
            public void onClick(final ClickEvent event) {
                eventBus.fireEvent(new AddTermEvent(""));
            }
        });
        display.getShowNumbersButton().addClickHandler(new ClickHandler() {
            public void onClick(final ClickEvent event) {
                eventBus.fireEvent(new ShowNumberEvent("", ""));
            }
        });
        display.getParseTextButton().addClickHandler(new ClickHandler() {
            public void onClick(final ClickEvent event) {
                eventBus.fireEvent(new ParseTextEvent());
            }
        });
        display.getDeleteButton().addClickHandler(new ClickHandler() {
            public void onClick(final ClickEvent event) {
                deleteSelectedTerms();
            }
        });
        display.getShowInfoButton().addClickHandler(new ClickHandler() {
            public void onClick(final ClickEvent event) {
                eventBus.fireEvent(new ShowInfoEvent());
            }
        });
        display.getFixButton().addClickHandler(new ClickHandler() {
            public void onClick(final ClickEvent event) {
                final ArrayList<String> ids = getSelectedIds();
                rpcService.fixTerms(ids, new AsyncCallback<String>() {
                    public void onSuccess(final String result) {
                        Window.alert(result);
                        fetchTermDetails();
                    }
                    public void onFailure(final Throwable caught) {
                        Window.alert("Fail fixing selected terms.");
                    }
                });
            }
        });
        display.getLetterBox().addChangeHandler(new ChangeHandler() {
            public void onChange(final ChangeEvent event) {
                display.setOffset(0);
                fetchTermDetails();
            }
        });
        display.getLatinHeader().addClickHandler(new ClickHandler() {
            public void onClick(final ClickEvent event) {
                sortTermsByLatinString();
            }
        });
        display.getHebrewHeader().addClickHandler(new ClickHandler() {
            public void onClick(final ClickEvent event) {
                sortTermsByHebrewString();
            }
        });
        for (final LatinMethod gm : LatinMethod.values()) {
            display.getGematriaHeader(gm).addClickHandler(new ClickHandler() {
                public void onClick(final ClickEvent event) {
                    sortTermsBy(gm);
                }
            });
        }
        for (final HebrewMethod gm : HebrewMethod.values()) {
            display.getGematriaHeader(gm).addClickHandler(new ClickHandler() {
                public void onClick(final ClickEvent event) {
                    sortTermsBy(gm);
                }
            });
        }
    }

    /**
     * Initializes the container and fetches the details for the term if there is any.
     */
    public final void go(final HasWidgets container) {
        bind();
        container.clear();
        container.add(display.asWidget());
        fetchTermDetails();
    }

    /**
     * Sets the details for the Term.
     * @param termDetails
     */
    public final void setTermDetails(final List<Term> termDetails) {
        this.terms = termDetails;
    }

    /**
     * Returns the term for the provided index.
     * @param index
     * @return the term
     */
    public final Term getTermDetail(final int index) {
        return terms.get(index);
    }

    /**
     * Fetches the terms for the selected letter at the selected offset.
     */
    private void fetchTermDetails() {
        String letter = display.getLetterBox().getValue(display.getLetterBox().getSelectedIndex());
        display.setLetter(letter);
        if (display.getTermsTable().getRowCount() == 0) {
            display.getTermsTable().setWidget(0, 0, new Label("Loading.."));
            display.getTermsTable().getCellFormatter().setAlignment(0, 0, HasHorizontalAlignment.ALIGN_LEFT, HasVerticalAlignment.ALIGN_TOP);
            display.getTermsTable().getCellFormatter().setStyleName(0, 0, "padded");
        }
        if (letter.equalsIgnoreCase("All")) {
            rpcService.getAllTermsFromOffset(display.getOffset(), new AsyncCallback<ArrayList<Term>>() {
                public void onSuccess(final ArrayList<Term> result) {
                    terms = result;
                    sortTermsByLatinString();
                    display.setData(terms);
                }
                public void onFailure(final Throwable caught) {
                    Window.alert("Fail fetching term details");
                }
            });
        } else {
            rpcService.getTermsFromOffset(letter, display.getOffset(), new AsyncCallback<ArrayList<Term>>() {
                public void onSuccess(final ArrayList<Term> result) {
                    terms = result;
                    sortTermsByLatinString();
                    display.setData(terms);
                }

                public void onFailure(final Throwable caught) {
                    Window.alert("Fail fetching term details");
                }
            });
        }
    }

    /**
     * Deletes the selected terms.
     */
    private void deleteSelectedTerms() {
        final ArrayList<String> ids = getSelectedIds();
        rpcService.deleteTerms(ids, new AsyncCallback<String>() {
            public void onSuccess(final String result) {
                Window.alert(result);
                fetchTermDetails();
            }

            public void onFailure(final Throwable caught) {
                Window.alert("Fail deleting selected terms.");
            }
        });
    }

    /**
     * Sorts the terms alphabetically.
     */
    public final void sortTermsByLatinString() {
        Collections.sort(terms, new TermSortByLatinString());
        display.setData(terms);
    }

    /**
     * Sorts the terms aleph-bethically by the hebrew String.
     */
    public final void sortTermsByHebrewString() {
        Collections.sort(terms, new TermSortByHebrewString());
        display.setData(terms);
    }

    /**
     * Sorts the terms by the value of the provided GematriaMethod.
     * @param gm gematria method
     */
    public final void sortTermsBy(final GematriaMethod gm) {
        Collections.sort(terms, new TermSortByGematriaMethod(gm));
        display.setData(terms);
    }

    /**
     * Returns the a List of the selected ids
     * @return ids the selected ids
     */
    private ArrayList<String> getSelectedIds() {
        final List<Integer> selectedRows = display.getSelectedRows();
        final ArrayList<String> ids = new ArrayList<String>();
        for (int i = 0; i < selectedRows.size(); ++i) {
            ids.add(terms.get(selectedRows.get(i)).getLatinString());
        }
        return ids;
    }
}
