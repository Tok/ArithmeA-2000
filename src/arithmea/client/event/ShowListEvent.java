package arithmea.client.event;

import com.google.gwt.event.shared.GwtEvent;

/**
 * Event to show the list view.
 */
public class ShowListEvent extends GwtEvent<ShowListEventHandler> {
    public static final Type<ShowListEventHandler> TYPE = new Type<ShowListEventHandler>();
    private String letter;
    private int offset;

    public ShowListEvent() {
        this.letter = "All";
        this.offset = 0;
    }

    /**
     * Constructor accepting a letter for the filter and an offset.
     * @param letter
     * @param offset
     */
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

    /**
     * Returns the offset if provided or null.
     * @return offset
     */
    public final int getOffset() {
        return offset;
    }

    /**
     * Returns the letter for the filter if provided or null.
     * @return letter
     */
    public final String getLetter() {
        return letter;
    }

}
