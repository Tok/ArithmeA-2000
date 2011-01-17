package arithmea.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class ShowNumberEvent extends GwtEvent<ShowNumberEventHandler> {
  public static Type<ShowNumberEventHandler> TYPE = new Type<ShowNumberEventHandler>();
  
  @Override
  public Type<ShowNumberEventHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(final ShowNumberEventHandler handler) {
    handler.onShowNumber(this);
  }
}
