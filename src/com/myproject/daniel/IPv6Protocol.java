package com.myproject.daniel;

public class IPv6Protocol implements INetworkLayerProtocol {
    private final String ipAddress;

    public IPv6Protocol(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    @Override
    public void routePacket(Packet packet, INetworkInterface sourceInterface) {
        // Similar to IPv4, could be more complex
        sourceInterface.sendPacket(packet);
    }

    @Override
    public String getAddress() {
        return ipAddress;
    }
}
