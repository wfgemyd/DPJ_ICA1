package com.myproject.physical_layer;

import com.myproject.core.INetworkInterface;
import com.myproject.core.Packet;

/**
 * Adds the extra noise (error rate) to the instance of the physical medium.
 */
public class NoiseMediumDecorator extends MediumDecorator {
    private double extraErrorRate;

    /**
     * Constructor.
     * @param wrapped Physical medium to decorate.
     * @param extraErrorRate The extra error rate (noise) to add.
     */
    public NoiseMediumDecorator(IPhysicalMedium wrapped, double extraErrorRate) {
        super(wrapped);
        this.extraErrorRate = extraErrorRate;
    }

    /**
     * Transmits a packet adding the extra errors rate.
     * @param packet The packet to transmit.
     * @param from Sender network interface.
     * @param to Receiver network interface.
     */
    @Override
    public void transmit(Packet packet, INetworkInterface from, INetworkInterface to) {
        double originalErrorRate = getErrorRate();
        double combinedRate = originalErrorRate + extraErrorRate;

        if (combinedRate > 1.0)
            combinedRate = 1.0;

        setErrorRate(combinedRate);
        super.transmit(packet, from, to);
        setErrorRate(originalErrorRate);
    }
}
