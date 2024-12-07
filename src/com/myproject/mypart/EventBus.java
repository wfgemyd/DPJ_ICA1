package com.myproject.mypart;

import java.util.ArrayList;
import java.util.List;

public class EventBus {
    private static final EventBus instance = new EventBus();
    private final List<IEventListener> listeners = new ArrayList<>();

    private EventBus(){}

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
