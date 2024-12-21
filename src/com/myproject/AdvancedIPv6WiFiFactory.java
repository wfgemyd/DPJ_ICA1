package com.myproject;

import com.myproject.link_layer.ILinkLayerProtocol;
import com.myproject.network_layer.INetworkLayerProtocol;
import com.myproject.link_layer.WiFiProtocol;
import com.myproject.network_layer.IPv6Protocol;

public class AdvancedIPv6WiFiFactory implements ProtocolStackFactory {
    private String ipAddress;

    public AdvancedIPv6WiFiFactory(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    @Override
    public ILinkLayerProtocol createLinkLayer() {
        return new WiFiProtocol();
    }

    @Override
    public INetworkLayerProtocol createNetworkLayer() {
        return new IPv6Protocol(ipAddress);
    }
}