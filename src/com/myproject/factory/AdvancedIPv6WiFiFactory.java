package com.myproject.factory;

import com.myproject.link_layer.ILinkLayerProtocol;
import com.myproject.network_layer.INetworkLayerProtocol;
import com.myproject.link_layer.WiFiProtocol;
import com.myproject.network_layer.IPv6Protocol;

public class AdvancedIPv6WiFiFactory implements ProtocolStackFactory {
    /**
     * The IP address of the device.
     */
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