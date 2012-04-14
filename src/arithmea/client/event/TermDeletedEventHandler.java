package arithmea.client.event;

import com.google.gwt.event.shared.EventHandler;

public interface TermDeletedEventHandler extends EventHandler {
    void onTermDeleted(final TermDeletedEvent event);
}
