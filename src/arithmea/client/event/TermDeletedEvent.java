package arithmea.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class TermDeletedEvent extends GwtEvent<TermDeletedEventHandler> {
    public static final Type<TermDeletedEventHandler> TYPE = new Type<TermDeletedEventHandler>();

    @Override
    public final Type<TermDeletedEventHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected final void dispatch(final TermDeletedEventHandler handler) {
        handler.onTermDeleted(this);
    }
}
