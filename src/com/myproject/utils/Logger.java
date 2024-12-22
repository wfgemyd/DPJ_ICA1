package com.myproject.utils;

import com.myproject.events.Event;
import com.myproject.events.EventScheduler;

public class Logger implements IEventListener {
    // Singleton
    private static final Logger instance = new Logger();
    private Logger() {}

    public static Logger getInstance() {

        return instance;
    }

    @Override
    public void onEvent(Event e) {
        System.out.println("[LOG " + EventScheduler.getInstance().getCurrentTime() + "]: " + e.getDescription());
    }
}
