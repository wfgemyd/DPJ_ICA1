package com.myproject.utils;

import com.myproject.events.Event;
import com.myproject.events.EventScheduler;

/**
 * Logging events.
 */
public class Logger implements IEventListener {
    // Singleton pattern
    private static final Logger instance = new Logger();

    private Logger() {
    }

    /**
     * @return singleton instance of the Logger.
     */
    public static Logger getInstance() {
        return instance;
    }

    /**
     * Logs the current time and the description of the event (e.g. results of transmission).
     * @param e event to log
     */
    @Override
    public void onEvent(Event e) {
        System.out.println("[LOG " + EventScheduler.getInstance().getCurrentTime() + "]: " + e.getDescription());
    }
}
