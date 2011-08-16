package arithmea.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class AddTermEvent extends GwtEvent<AddTermEventHandler> {
  public static Type<AddTermEventHandler> TYPE = new Type<AddTermEventHandler>();
  private String word;
  
  public AddTermEvent(String word) {
	  this.word = word;
  }
  
  @Override
  public Type<AddTermEventHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(final AddTermEventHandler handler) {
    handler.onAddTerm(this);
  }

  public String getWord() {
	return word;
  }

}
