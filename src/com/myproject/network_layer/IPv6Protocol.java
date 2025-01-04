package com.myproject.network_layer;

import com.myproject.core.INetworkInterface;
import com.myproject.core.Packet;

public class IPv6Protocol implements INetworkLayerProtocol {
    private final String ipv6Address;

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
