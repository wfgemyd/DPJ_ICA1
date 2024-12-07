package com.myproject.hazmin;

import com.myproject.daniel.IPhysicalMedium;
import com.myproject.daniel.Packet;
import com.myproject.daniel.INetworkInterface;

public class NoiseMediumDecorator extends MediumDecorator {
    private double extraErrorRate;

    public NoiseMediumDecorator(IPhysicalMedium wrapped, double extraErrorRate) {
        super(wrapped);
        this.extraErrorRate = extraErrorRate;
    }

    @Override
    public void transmit(Packet packet, INetworkInterface from, INetworkInterface to) {
        // Just a placeholder. Actual logic to increase error rate would be here.
        super.transmit(packet, from, to);
    }
}
