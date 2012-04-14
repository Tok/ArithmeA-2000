package arithmea.client.event;

import com.google.gwt.event.shared.EventHandler;

/**
 * Interface to handle the event showing the list view.
 */
public interface ShowListEventHandler extends EventHandler {
    void onShowList(final ShowListEvent event);
}
