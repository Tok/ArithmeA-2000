package arithmea.client.event;

import com.google.gwt.event.shared.GwtEvent;

/**
 * Event to show the page to add new words.
 */
public class AddTermEvent extends GwtEvent<AddTermEventHandler> {
    public static final Type<AddTermEventHandler> TYPE = new Type<AddTermEventHandler>();
    private String word;

    public AddTermEvent(final String word) {
        this.word = word;
    }

    @Override
    public final com.google.gwt.event.shared.GwtEvent.Type<AddTermEventHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected final void dispatch(final AddTermEventHandler handler) {
        handler.onAddTerm(this);
    }

    /**
     * Returns the word that should be added.
     * @return word
     */
    public final String getWord() {
        return word;
    }

}
