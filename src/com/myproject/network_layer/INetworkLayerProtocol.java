package com.myproject.network_layer;

import com.myproject.core.INetworkInterface;
import com.myproject.core.Packet;

/**
 * The INetworkLayerProtocol defines the behavior of a network layer protocol (e.g. IPv4, IPv6).
 * It is responsible for handling packets,
 * including determining the next appropriate interface or device through
 * which the packet should be sent based on the destination address.
 */
public interface INetworkLayerProtocol {
    /**
     * Routes a packet to the appropriate interface/device based on the destination address.
     *
     * @param packet Packet instance containing data, destination and headers necessary to conduct network transmission.
     * @param sourceInterface the INetworkInterface from which the packet came.
     *
     */
    void routePacket(Packet packet, INetworkInterface sourceInterface);

    /**
     * Returns the address associated with specific network layer protocol.
     */
    String getAddress();
}
