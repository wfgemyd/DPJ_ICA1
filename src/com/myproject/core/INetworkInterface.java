package com.myproject.core;

import com.myproject.physical_layer.IPhysicalMedium;

/**
 * Represents a network interface of a network device. It is used to send and receive packets.
 */

public interface INetworkInterface {
    /**
     * Connects the interface to a physical medium.
     * @param medium The physical medium to connect to.
     */
    void connect(IPhysicalMedium medium);

    /**
     * Sends a packet.
     * @param packet The packet to send.
     */
    void sendPacket(Packet packet);

    /**
     * Receives a packet.
     * @param packet The packet to receive.
     */
    void receivePacket(Packet packet);
}
