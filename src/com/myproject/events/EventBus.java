package com.myproject.events;

import com.myproject.utils.IEventListener;

import java.util.ArrayList;
import java.util.List;

public class EventBus {
    // declaring as singleton
    private static final EventBus instance = new EventBus();
    private final List<IEventListener> listeners = new ArrayList<>();

    private EventBus(){}

    /**
     * @return singleton instance of the EventBus
     */
    public static EventBus getInstance() {
        return instance;
    }

    public void registerListener(IEventListener listener) {
        listeners.add(listener);
    }

    public void notify(Event e) {
        for (IEventListener listener : listeners) {
            listener.onEvent(e);
        }
    }
}
