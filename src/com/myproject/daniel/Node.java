package com.myproject.daniel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A Node represents a network device (e.g. a computer or a router).
 */
public class Node {
    /**
     * The name of the node.
     */
    private final String name;

    /**
     * The list of network interfaces (e.g., Ethernet ports, WiFi adapters) associated with this node, representing Node's network connections.
     */
    private final List<INetworkInterface> interfaces = new ArrayList<>();

    /**
     * Constructor.
     *
     * @param name Unique name identifying the node.
     */
    public Node(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Node name can't be null.");
        }
        this.name = name;
    }

    /**
     * Adds a network interface to this node.
     *
     * @param intf an implementation of {@code INetworkInterface} representing a connection to some network medium
     */
    public void addInterface(INetworkInterface intf) {
        if (intf == null) {
            throw new IllegalArgumentException("Interface can't be null.");
        }
        if (interfaces.contains(intf)) {
            throw new IllegalStateException("Interface already added to this node.");
        }
        interfaces.add(intf);
    }

    /**
     * Returns the list of network interfaces associated with this node.
     *
     * @return immutable list of network interfaces
     */
    public List<INetworkInterface> getInterfaces() {
        return Collections.unmodifiableList(interfaces);
    }

    /**
     * Returns the name of the node.
     *
     * @return the name of the node
     */
    public String getName() {
        return name;
    }
}
