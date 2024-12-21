package com.myproject.physical_layer;

import com.myproject.core.INetworkInterface;
import com.myproject.core.Packet;
import com.myproject.events.EventScheduler;
import com.myproject.events.TransmissionEvent;

/**
 * Cable medium for physical layer.
 */
public class CableMedium implements IPhysicalMedium {

    private long delay = 10;
    private double errorRate = 0.0;

    /**
     * Transmit packet from one interface to another.
     * @param packet Packet to transmit.
     * @param from Source interface.
     * @param to Destination interface.
     */
    @Override
    public void transmit(Packet packet,
                         INetworkInterface from,
                         INetworkInterface to) {
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
