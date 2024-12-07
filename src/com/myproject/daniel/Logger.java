package com.myproject.daniel;

public class Logger implements IEventListener {
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
