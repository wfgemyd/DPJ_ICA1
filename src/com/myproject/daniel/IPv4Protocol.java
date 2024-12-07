package com.myproject.daniel;

public class IPv4Protocol implements INetworkLayerProtocol {
    private final String ipAddress;

    public IPv4Protocol(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    @Override
    public void routePacket(Packet packet, INetworkInterface sourceInterface) {
        // Check if this node is the destination
        if (ipAddress.equals(packet.getDestination())) {
            // Packet is at destination, do not resend
            // If you want to log or process it here, you can
        } else {
            // If not destination, forward it along
            sourceInterface.sendPacket(packet);
        }
    }

    @Override
    public String getAddress() {
        return ipAddress;
    }
}

