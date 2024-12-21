package com.myproject.events;

/**
 * Abstract class to represent event during the simulation.
 *
 */
public abstract class Event {
    protected final long time;
    /** Constructor.
     * @param time Time when the event should be executed.
     */

    public Event(long time) {
        this.time = time;
    }

    public long getTime() {
        return time;
    }

    public abstract void execute();
    public abstract String getDescription();
}
