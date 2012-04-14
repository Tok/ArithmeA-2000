package arithmea.client.event;

import com.google.gwt.event.shared.GwtEvent;

/**
 * Event to show the page for parsing texts.
 */
public class ParseTextEvent extends GwtEvent<ParseTextEventHandler> {
    public static final Type<ParseTextEventHandler> TYPE = new Type<ParseTextEventHandler>();

    @Override
    public final Type<ParseTextEventHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected final void dispatch(final ParseTextEventHandler handler) {
        handler.onParseText(this);
    }
}
