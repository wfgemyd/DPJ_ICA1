package com.myproject.anna;

import com.myproject.daniel.ILinkLayerProtocol;
import com.myproject.daniel.INetworkLayerProtocol;
import com.myproject.daniel.EthernetProtocol;
import com.myproject.daniel.IPv4Protocol;

public class BasicIPv4EthernetFactory implements ProtocolStackFactory {
    private String ipAddress;

    public BasicIPv4EthernetFactory(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    @Override
    public ILinkLayerProtocol createLinkLayer() {
        return new EthernetProtocol();
    }

    @Override
    public INetworkLayerProtocol createNetworkLayer() {
        return new IPv4Protocol(ipAddress);
    }
}
