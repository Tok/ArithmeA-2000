package arithmea.client.event;

import com.google.gwt.event.shared.GwtEvent;

/**
 * Event to delete words.
 */
public class TermDeletedEvent extends GwtEvent<TermDeletedEventHandler> {
    public static final Type<TermDeletedEventHandler> TYPE = new Type<TermDeletedEventHandler>();

    @Override
    public final com.google.gwt.event.shared.GwtEvent.Type<TermDeletedEventHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected final void dispatch(final TermDeletedEventHandler handler) {
        handler.onTermDeleted(this);
    }
}
