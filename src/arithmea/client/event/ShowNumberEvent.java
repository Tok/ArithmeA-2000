package arithmea.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class ShowNumberEvent extends GwtEvent<ShowNumberEventHandler> {
	public static Type<ShowNumberEventHandler> TYPE = new Type<ShowNumberEventHandler>();
	private String method;
	private String number;

	public ShowNumberEvent(String method, String number) {
		this.method = method;
		this.number = number;
	}

	@Override
	public Type<ShowNumberEventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(final ShowNumberEventHandler handler) {
		handler.onShowNumber(this);
	}

	public String getNumber() {
		return number;
	}

	public String getMethod() {
		return method;
	}

}
