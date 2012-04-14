package arithmea.client.event;

import com.google.gwt.event.shared.EventHandler;

/**
 * Interface to handle the event to show the page for adding new words.
 */
public interface AddTermEventHandler extends EventHandler {
    void onAddTerm(final AddTermEvent event);
}
