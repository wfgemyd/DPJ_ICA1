package com.myproject;

import com.myproject.link_layer.ILinkLayerProtocol;
import com.myproject.network_layer.INetworkLayerProtocol;
import com.myproject.link_layer.EthernetProtocol;
import com.myproject.network_layer.IPv4Protocol;

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
