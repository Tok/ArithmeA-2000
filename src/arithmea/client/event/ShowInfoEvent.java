package arithmea.client.event;

import com.google.gwt.event.shared.GwtEvent;

/**
 * Event to show the info page.
 */
public class ShowInfoEvent extends GwtEvent<ShowInfoEventHandler> {
    public static final Type<ShowInfoEventHandler> TYPE = new Type<ShowInfoEventHandler>();

    @Override
    public final Type<ShowInfoEventHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected final void dispatch(final ShowInfoEventHandler handler) {
        handler.onShowInfo(this);
    }
}
