package com.myproject.physical_layer;

import com.myproject.core.INetworkInterface;
import com.myproject.core.Packet;
import com.myproject.events.EventScheduler;
import com.myproject.events.TransmissionEvent;

/**
 * Represents a wireless medium, such as WiFi or Bluetooth signals for transmitting the data.
 */
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
