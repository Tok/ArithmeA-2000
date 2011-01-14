package arithmea.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class EditTermCancelledEvent extends GwtEvent<EditTermCancelledEventHandler>{
  public static Type<EditTermCancelledEventHandler> TYPE = new Type<EditTermCancelledEventHandler>();
  
  @Override
  public Type<EditTermCancelledEventHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(final EditTermCancelledEventHandler handler) {
    handler.onEditTermCancelled(this);
  }
}
