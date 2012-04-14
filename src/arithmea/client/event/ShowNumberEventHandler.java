package arithmea.client.event;

import com.google.gwt.event.shared.EventHandler;

/**
 * Interface to handle the event to show the page that compares numbers by methods.
 */
public interface ShowNumberEventHandler extends EventHandler {
    void onShowNumber(final ShowNumberEvent event);
}
