package com.myproject.anna;

import com.myproject.daniel.ILinkLayerProtocol;
import com.myproject.daniel.INetworkLayerProtocol;

public interface ProtocolStackFactory {
    ILinkLayerProtocol createLinkLayer();
    INetworkLayerProtocol createNetworkLayer();
}