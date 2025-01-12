package com.myproject.network_layer;

import com.myproject.core.INetworkInterface;
import com.myproject.core.Packet;

/**
 * Implementation of the IPv4 network layer protocol.
 * The IP Address should be in the following format: 255.255.255.255
 */
public class IPv4Protocol implements INetworkLayerProtocol {
    private final String ipv4Address;

    /**
     * Constructor for IPv4Protocol
     * @param ipv4Address the IPv4 address of the node
     */
    public IPv4Protocol(String ipv4Address) {

        this.ipv4Address = ipv4Address;
    }

    @Override
    public void routePacket(Packet packet, INetworkInterface sourceInterface) {
        // Check if this node is the destination
        if (ipv4Address.equals(packet.getDestination())) {
            // Packet is at destination, do not resend
        } else {
            // If not destination, forward it along
            sourceInterface.sendPacket(packet);
        }
    }

    @Override
    public Packet formatPacket(Packet packet, String destinationAddress) {
        packet.addHeader("IPv6 Header" + ipv4Address + "->" + destinationAddress);

        return packet;
    }

    @Override
    public String getAddress() {

        return ipv4Address;
    }
}

