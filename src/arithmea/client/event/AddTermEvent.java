package arithmea.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class AddTermEvent extends GwtEvent<AddTermEventHandler> {
  public static Type<AddTermEventHandler> TYPE = new Type<AddTermEventHandler>();
  
  @Override
  public Type<AddTermEventHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(AddTermEventHandler handler) {
    handler.onAddTerm(this);
  }
}
