package com.myproject.utils;

import com.myproject.events.Event;

import java.util.ArrayList;
import java.util.List;

/**
 * Publisher object, stores an array of subscribers. Observer Pattern implementation for events.
 */
public class EventBus {
    // declaring as singleton
    private static final EventBus instance = new EventBus();
    private EventBus(){}
    // list of subscribers
    private final List<IEventListener> listeners = new ArrayList<>();

    /**
     * @return singleton instance of the EventBus
     */
    public static EventBus getInstance() {
        return instance;
    }

    /**
     * Register a subscriber to the EventBus.
     * @param listener listener to register
     */
    public void registerListener(IEventListener listener) {
        listeners.add(listener);
    }

    /**
     * Notify all subscribers about the event.
     * @param e event.
     */
    public void notify(Event e) {
        for (IEventListener listener : listeners) {
            listener.onEvent(e);
        }
    }
}
