package com.myproject.utils;

import com.myproject.events.Event;
import com.myproject.events.TransmissionEvent;

/**
 * Determines the number of delivered and lost packets during the simulation.
 */
public class StatisticsCollector implements IEventListener {
    private int deliveredCount = 0;
    private int lostCount = 0;

    /**
     * Determine whether the packet was delivered or lost and update the statistics of delivered and lost packets.
     * @param e Event instance based on which the statistics are updated.
     */
    @Override
    public void onEvent(Event e) {
        if (e instanceof TransmissionEvent) {
            TransmissionEvent te = (TransmissionEvent) e;
            if (te.getDescription().contains("lost")) {
                lostCount++;
            } else if (te.getDescription().contains("delivered")) {
                deliveredCount++;
            }
        }
    }

    /**
     * Print the statistics of the simulation, showcasing the number of delivered and lost packets.
     */
    public void printStats() {
        System.out.println("Delivered: " + deliveredCount);
        System.out.println("Lost: " + lostCount);
    }
}
