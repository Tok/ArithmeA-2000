package arithmea.client.event;

import com.google.gwt.event.shared.EventHandler;

/**
 * Interface to handle the event to show the page for parsing texts.
 */
public interface ParseTextEventHandler extends EventHandler {
  void onParseText(final ParseTextEvent event);
}
