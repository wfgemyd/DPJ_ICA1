package com.myproject.mypart;

import java.util.Comparator;
import java.util.PriorityQueue;

public class EventScheduler {
    private static final EventScheduler instance = new EventScheduler();
    private final PriorityQueue<Event> eventQueue = new PriorityQueue<>(Comparator.comparingLong(Event::getTime));
    private long currentTime = 0;

    private EventScheduler(){}

    public static EventScheduler getInstance() {
        return instance;
    }

    public void schedule(Event event) {
        eventQueue.add(event);
    }

    public long getCurrentTime() {
        return currentTime;
    }

    public void run() {
        while(!eventQueue.isEmpty()) {
            Event e = eventQueue.poll();
            currentTime = e.getTime();
            e.execute();
        }
    }
}
