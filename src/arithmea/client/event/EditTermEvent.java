package arithmea.client.event;

import com.google.gwt.event.shared.GwtEvent;

/**
 * Event to show the page to edit terms.
 */
public class EditTermEvent extends GwtEvent<EditTermEventHandler> {
    public static final Type<EditTermEventHandler> TYPE = new Type<EditTermEventHandler>();
    private final String id;

    public EditTermEvent(final String id) {
        this.id = id;
    }

    public final String getId() {
        return id;
    }

    @Override
    public final Type<EditTermEventHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected final void dispatch(final EditTermEventHandler handler) {
        handler.onEditTerm(this);
    }
}
