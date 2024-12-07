package com.myproject.daniel;

public class IPv4Protocol implements INetworkLayerProtocol {
    private final String ipAddress;

    public IPv4Protocol(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    @Override
    public void routePacket(Packet packet, INetworkInterface sourceInterface) {
        // Simple routing: just send out on the same interface
        sourceInterface.sendPacket(packet);
    }

    @Override
    public String getAddress() {
        return ipAddress;
    }
}
