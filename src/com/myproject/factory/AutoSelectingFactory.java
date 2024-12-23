package com.myproject.factory;
import com.myproject.core.INetworkInterface;
import com.myproject.core.Node;
import com.myproject.core.PhysicalInterface;
import com.myproject.link_layer.ILinkLayerProtocol;
import com.myproject.network_layer.INetworkLayerProtocol;
import com.myproject.link_layer.WiFiProtocol;
import com.myproject.link_layer.EthernetProtocol;
import com.myproject.network_layer.IPv6Protocol;
import com.myproject.network_layer.IPv4Protocol;

/**
 * Factory for automatic selection and creation of appropriate protocol stacks.
 */
public class AutoSelectingFactory implements ProtocolStackFactory {
    /**
     * Creates a node with the appropriate protocol stack based on the given arguments.
     *
     * @param nodeName the name of the node
     * @param ipAddress the IP address of the node
     * @param linkLayerProtocol the link layer protocol to use (WiFi or Ethernet)
     *
     * @return Node instance with the appropriate protocol stack
     */
    @Override
    public Node createNode(String nodeName, String ipAddress, String linkLayerProtocol) {
        Node node = new Node(nodeName);
        ILinkLayerProtocol linkLayer;
        INetworkLayerProtocol networkLayer;

        boolean isIPv6 = ipAddress.contains(":");

        if (isIPv6) {
            networkLayer  = new IPv6Protocol(ipAddress);
        } else {
            networkLayer  = new IPv4Protocol(ipAddress);
        }

        if (linkLayerProtocol.equals("WiFi")) {
            linkLayer = new WiFiProtocol();
        } else {
            linkLayer = new EthernetProtocol();
        }

        INetworkInterface intf = new PhysicalInterface(node, linkLayer, networkLayer);
        node.addInterface(intf);

        return node;
    }
}
