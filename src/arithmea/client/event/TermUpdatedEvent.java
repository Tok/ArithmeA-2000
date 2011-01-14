package arithmea.client.event;

import arithmea.shared.Term;

import com.google.gwt.event.shared.GwtEvent;

public class TermUpdatedEvent extends GwtEvent<TermUpdatedEventHandler>{
  public static Type<TermUpdatedEventHandler> TYPE = new Type<TermUpdatedEventHandler>();
  private final Term updatedTerm;
  
  public TermUpdatedEvent(Term updatedTerm) {
    this.updatedTerm = updatedTerm;
  }
  
  public Term getUpdatedTerm() { return updatedTerm; }
  

  @Override
  public Type<TermUpdatedEventHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(TermUpdatedEventHandler handler) {
    handler.onTermUpdated(this);
  }
}
