package arithmea.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class TermDeletedEvent extends GwtEvent<TermDeletedEventHandler>{
  public static Type<TermDeletedEventHandler> TYPE = new Type<TermDeletedEventHandler>();
  
  @Override
  public Type<TermDeletedEventHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(TermDeletedEventHandler handler) {
    handler.onTermDeleted(this);
  }
}
