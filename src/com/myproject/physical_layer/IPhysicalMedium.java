package com.myproject.physical_layer;

import com.myproject.core.INetworkInterface;
import com.myproject.core.Packet;

public interface IPhysicalMedium {
    /**
     * Transmits a packet from one network interface to another.
     * @param packet The packet to transmit.
     * @param from The network interface the packet is transmitted from.
     * @param to The network interface the packet is transmitted to.
     */
    void transmit(Packet packet, INetworkInterface from, INetworkInterface to);

    /**
     * Determines how long the transmission will take.
     *
     * @param delay The delay in milliseconds.
     */
    void setDelay(long delay);

    /**
     * Adds noise to the medium.
     * @param rate The error rate to set.
     */
    void setErrorRate(double rate);

    /**
     * Gets the noise (error rate) of the medium.
     * @return The error rate.
     */
    double getErrorRate(); // Add this method
}