package com.myproject.daniel;

/**
 * Represents a network interface of a network device.
 */

public interface INetworkInterface {

    void connect(IPhysicalMedium medium);
    void sendPacket(Packet packet);
    void receivePacket(Packet packet);
}
