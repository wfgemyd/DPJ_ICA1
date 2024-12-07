package com.myproject.anna;

import com.myproject.daniel.ILinkLayerProtocol;
import com.myproject.daniel.INetworkLayerProtocol;
import com.myproject.daniel.WiFiProtocol;
import com.myproject.daniel.IPv6Protocol;

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