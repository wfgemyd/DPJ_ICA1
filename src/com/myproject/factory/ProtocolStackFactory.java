package com.myproject.factory;

import com.myproject.link_layer.ILinkLayerProtocol;
import com.myproject.network_layer.INetworkLayerProtocol;
import com.myproject.core.Node;

/**
 * Factory interface for creating protocol stacks.
 */
public interface ProtocolStackFactory {
    Node createNode(String nodeName, String ipAddress, String linkLayerProtocol);
}