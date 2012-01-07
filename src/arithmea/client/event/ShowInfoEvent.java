package arithmea.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class ShowInfoEvent extends GwtEvent<ShowInfoEventHandler> {
	public static Type<ShowInfoEventHandler> TYPE = new Type<ShowInfoEventHandler>();

	@Override
	public Type<ShowInfoEventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(final ShowInfoEventHandler handler) {
		handler.onShowInfo(this);
	}
}
