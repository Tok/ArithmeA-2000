package arithmea.client.event;

import com.google.gwt.event.shared.EventHandler;

/**
 * Interface to handle the event to show info page.
 */
public interface ShowInfoEventHandler extends EventHandler {
    void onShowInfo(final ShowInfoEvent event);
}
