package com.myproject;

import com.myproject.link_layer.ILinkLayerProtocol;
import com.myproject.network_layer.INetworkLayerProtocol;

public interface ProtocolStackFactory {
    ILinkLayerProtocol createLinkLayer();
    INetworkLayerProtocol createNetworkLayer();
}