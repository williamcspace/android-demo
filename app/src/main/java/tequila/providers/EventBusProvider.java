package tequila.providers;

import de.greenrobot.event.EventBus;

/**
 * Created by williamc1986 on 7/13/15.
 */
public class EventBusProvider {
    public static EventBus sInstance;

    private EventBusProvider() {}

    public static synchronized EventBus getInstance() {
        if (sInstance == null) {
            sInstance = EventBus.builder().logNoSubscriberMessages(false).sendNoSubscriberEvent(false).build();;
        }
        return sInstance;
    }
}
