package arithmea.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class EditTermEvent extends GwtEvent<EditTermEventHandler>{
  public static Type<EditTermEventHandler> TYPE = new Type<EditTermEventHandler>();
  private final String id;
  
  public EditTermEvent(final String id) {
    this.id = id;
  }
  
  public String getId() { return id; }
  
  @Override
  public Type<EditTermEventHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(EditTermEventHandler handler) {
    handler.onEditTerm(this);
  }
}
