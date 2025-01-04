package com.myproject.network_layer;

import com.myproject.core.INetworkInterface;
import com.myproject.core.Packet;

/**
 * The INetworkLayerProtocol defines the behavior of protocol of a network layer (e.g. IPv4, IPv6).
 * It is responsible for formatting packets, and routing them to the appropriate interface based on the destination address.
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
     * Formats a packet by adding headers.
     *
     * @param packet Packet instance.
     */
    Packet formatPacket(Packet packet, String destinationAddress);

    /**
     * Returns the address associated with specific network layer protocol.
     */
    String getAddress();
}
