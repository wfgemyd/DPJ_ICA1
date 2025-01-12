package com.myproject.network_layer;

import com.myproject.core.INetworkInterface;
import com.myproject.core.Packet;

/**
 * Implementation of the IPv6 network layer protocol.
 * The IP Address should be in the following format: 2001:0db8:85a3:0000:0000:8a2e:0370:7334
 */
public class IPv6Protocol implements INetworkLayerProtocol {
    private final String ipv6Address;

    /**
     * Constructor for IPv6Protocol.
     * @param ipAddress the IPv6 address.
     */
    public IPv6Protocol(String ipAddress) {
        this.ipv6Address = ipAddress;
    }

    @Override
    public void routePacket(Packet packet, INetworkInterface sourceInterface) {
        sourceInterface.sendPacket(packet);
    }

    @Override
    public Packet formatPacket(Packet packet, String destinationAddress) {
        packet.addHeader("IPv6 Header" + ipv6Address + "->" + destinationAddress);

        return packet;
    }

    @Override
    public String getAddress() {
        return ipv6Address;
    }
}
