package com.myproject.daniel;

public class WirelessMedium implements IPhysicalMedium {
    private long delay = 15;
    private double errorRate = 0.05;

    @Override
    public void transmit(Packet packet, INetworkInterface from, INetworkInterface to) {
        long eventTime = EventScheduler.getInstance().getCurrentTime() + delay;
        EventScheduler.getInstance().schedule(new TransmissionEvent(eventTime, packet, from, to, errorRate));
    }

    @Override
    public void setDelay(long delay) {
        this.delay = delay;
    }


    @Override
    public void setErrorRate(double rate) {
        this.errorRate = rate;
    }

    @Override
    public double getErrorRate() {
        return this.errorRate;
    }
}
