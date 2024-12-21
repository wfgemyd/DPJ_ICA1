package com.myproject.core;

import com.myproject.physical_layer.IPhysicalMedium;

/**
 * Represents a network interface of a network device.
 */

public interface INetworkInterface {

    void connect(IPhysicalMedium medium);
    void sendPacket(Packet packet);
    void receivePacket(Packet packet);
}
