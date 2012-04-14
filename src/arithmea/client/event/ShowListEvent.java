package arithmea.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class ShowListEvent extends GwtEvent<ShowListEventHandler> {
    public static final Type<ShowListEventHandler> TYPE = new Type<ShowListEventHandler>();
    private String letter;
    private int offset;

    public ShowListEvent() {
        this.letter = "All";
        this.offset = 0;
    }

    public ShowListEvent(final String letter, final int offset) {
        this.letter = letter;
        this.offset = offset;
    }

    @Override
    public final Type<ShowListEventHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected final void dispatch(final ShowListEventHandler handler) {
        handler.onShowList(this);
    }

    public final int getOffset() {
        return offset;
    }

    public final String getLetter() {
        return letter;
    }

}
