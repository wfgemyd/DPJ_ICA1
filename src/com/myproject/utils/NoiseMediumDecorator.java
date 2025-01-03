package com.myproject.utils;

import com.myproject.core.INetworkInterface;
import com.myproject.core.Packet;
import com.myproject.physical_layer.IPhysicalMedium;

public class NoiseMediumDecorator extends MediumDecorator {
    private double extraErrorRate;

    public NoiseMediumDecorator(IPhysicalMedium wrapped, double extraErrorRate) {
        super(wrapped);
        this.extraErrorRate = extraErrorRate;
    }

    @Override
    public void transmit(Packet packet, INetworkInterface from, INetworkInterface to) {
        double originalErrorRate = getErrorRate(); // Inherited from MediumDecorator
        double combinedRate = originalErrorRate + extraErrorRate;

        if (combinedRate > 1.0)
            combinedRate = 1.0; // Cap at 100%

        setErrorRate(combinedRate);
        super.transmit(packet, from, to);
        setErrorRate(originalErrorRate);
    }
}
