package com.myproject.daniel;

public interface INetworkLayerProtocol {
    void routePacket(Packet packet, INetworkInterface sourceInterface);
    String getAddress();
}
