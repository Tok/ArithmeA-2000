package arithmea.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class ParseTextEvent extends GwtEvent<ParseTextEventHandler> {
  public static Type<ParseTextEventHandler> TYPE = new Type<ParseTextEventHandler>();
  
  @Override
  public Type<ParseTextEventHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(final ParseTextEventHandler handler) {
    handler.onParseText(this);
  }
}
