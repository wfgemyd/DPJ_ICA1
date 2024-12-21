package com.myproject.daniel;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Manaaging and executing events in the simulation. Singleton class.
 */
public class EventScheduler {
    // singleton pattern
    private static final EventScheduler instance = new EventScheduler();
    // stores list of events to be executed, sorted by execution time, ensuring earliest event goes first
    private final PriorityQueue<Event> eventQueue = new PriorityQueue<>(Comparator.comparingLong(Event::getTime));
    private long currentTime = 0;
    // private constructor to prevent instantiation from outside (use only getInstance())
    private EventScheduler(){}

    /**
     * Get the singleton instance of the scheduler, ensuring only one scheduler is created.
     * @return Instance of the scheduler.
     */
    public static EventScheduler getInstance() {
        return instance;
    }

    /**
     * Add event to the priority queue.
     * @param event Event to execute.
     */
    public void schedule(Event event) {
        eventQueue.add(event);
    }

    public long getCurrentTime() {
        return currentTime;
    }

    /**
     * Run the simulation.
     */
    public void run() {
        while(!eventQueue.isEmpty()) {
            Event e = eventQueue.poll();
            currentTime = e.getTime();
            e.execute();
        }
    }
}
