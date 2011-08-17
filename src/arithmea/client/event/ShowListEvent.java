package arithmea.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class ShowListEvent extends GwtEvent<ShowListEventHandler> {
	public static Type<ShowListEventHandler> TYPE = new Type<ShowListEventHandler>();
	private String letter;
	private int offset;

	public ShowListEvent() {
		this.letter = "All";
		this.offset = 0;
	}
	
	public ShowListEvent(String letter, int offset) {
		this.letter = letter;
		this.offset = offset;
	}

	@Override
	public Type<ShowListEventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(final ShowListEventHandler handler) {
		handler.onShowList(this);
	}

	public int getOffset() {
		return offset;
	}

	public String getLetter() {
		return letter;
	}

}
