package arithmea.client.event;

import com.google.gwt.event.shared.EventHandler;

/**
 * Interface to handle the event to show the page for editing terms.
 */
public interface EditTermEventHandler extends EventHandler {
    void onEditTerm(final EditTermEvent event);
}
