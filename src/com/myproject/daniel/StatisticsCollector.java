package com.myproject.daniel;

public class StatisticsCollector implements IEventListener {
    private int deliveredCount = 0;
    private int lostCount = 0;

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

    public void printStats() {
        System.out.println("Delivered: " + deliveredCount);
        System.out.println("Lost: " + lostCount);
    }
}
