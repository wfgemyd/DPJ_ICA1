package com.myproject.utils;

import com.myproject.events.Event;

/**
 * Interface for subsctibers (observers) of the EventBus.
 */
public interface IEventListener {
    /**
     * Method that is called when an event is published.
     * @param e event that was published
     */
    void onEvent(Event e);
}