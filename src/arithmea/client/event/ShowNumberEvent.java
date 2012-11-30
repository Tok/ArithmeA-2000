package arithmea.client.event;

import com.google.gwt.event.shared.GwtEvent;

/**
 * Event to show the page that compares numbers by methods.
 */
public class ShowNumberEvent extends GwtEvent<ShowNumberEventHandler> {
    public static final Type<ShowNumberEventHandler> TYPE = new Type<ShowNumberEventHandler>();
    private String method;
    private String number;

    /**
     * Accepts a the name of a gematria method and a number to compare.
     * @param method name of the gematria method
     * @param number as String.
     */
    public ShowNumberEvent(final String method, final String number) {
        this.method = method;
        this.number = number;
    }

    @Override
    public final com.google.gwt.event.shared.GwtEvent.Type<ShowNumberEventHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected final void dispatch(final ShowNumberEventHandler handler) {
        handler.onShowNumber(this);
    }

    /**
     * Returns a String representing the number to compare.
     * @return number String
     */
    public final String getNumber() {
        return number;
    }

    /**
     * Returns the name of the gematria method by wich the comparision is made.
     * @return method name
     */
    public final String getMethod() {
        return method;
    }

}
