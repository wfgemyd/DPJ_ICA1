package com.myproject.daniel;

public abstract class Event {
    protected final long time;

    public Event(long time) {
        this.time = time;
    }

    public long getTime() {
        return time;
    }

    public abstract void execute();
    public abstract String getDescription();
}
