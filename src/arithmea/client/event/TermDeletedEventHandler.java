package arithmea.client.event;

import com.google.gwt.event.shared.EventHandler;

/**
 * Interface to handle the event to delete words.
 */
public interface TermDeletedEventHandler extends EventHandler {
    void onTermDeleted(final TermDeletedEvent event);
}
