package arithmea.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class ShowNumberEvent extends GwtEvent<ShowNumberEventHandler> {
    public static final Type<ShowNumberEventHandler> TYPE = new Type<ShowNumberEventHandler>();
    private String method;
    private String number;

    public ShowNumberEvent(final String method, final String number) {
        this.method = method;
        this.number = number;
    }

    @Override
    public final Type<ShowNumberEventHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected final void dispatch(final ShowNumberEventHandler handler) {
        handler.onShowNumber(this);
    }

    public final String getNumber() {
        return number;
    }

    public final String getMethod() {
        return method;
    }

}
