package com.myproject.core;

import com.myproject.utils.TopologyManagerStaticMap;
import com.myproject.link_layer.ILinkLayerProtocol;
import com.myproject.network_layer.INetworkLayerProtocol;
import com.myproject.physical_layer.IPhysicalMedium;

/**
 * Represents a physical network interface of a network device, linking protocols and mediums together. It is used to send and receive packets.
 */
public class PhysicalInterface implements INetworkInterface {
    // The network device that owns this interface
    private final Node owner;
    private final ILinkLayerProtocol linkProtocol;
    private final INetworkLayerProtocol networkProtocol;
    private IPhysicalMedium medium;

    /**
     * Constructor, creates a new physical interface.
     * @param owner The network device that owns this interface.
     * @param linkProtocol The link layer protocol to use.
     * @param networkProtocol The network layer protocol to use.
     */
    public PhysicalInterface(Node owner, ILinkLayerProtocol linkProtocol, INetworkLayerProtocol networkProtocol) {
        this.owner = owner;
        this.linkProtocol = linkProtocol;
        this.networkProtocol = networkProtocol;
    }

    /**
     * Connects the interface to a physical medium.
     * @param medium The physical medium to connect to.
     */
    @Override
    public void connect(IPhysicalMedium medium) {
        this.medium = medium;
    }

    /**
     * Encapsulates and routes a packet to the target.
     * @param packet The packet to send.
     */
    @Override
    public void sendPacket(Packet packet) {
        if (medium == null) {
            throw new IllegalStateException("Interface not connected to a medium");
        }
        // Encapsulate the packet using the link layer protocol
        Packet framed = linkProtocol.encapsulate(packet);
        INetworkInterface targetInterface = TopologyManagerStaticMap.getTargetInterfaceFor(packet.getDestination());
        if (targetInterface != null) {
            medium.transmit(framed, this, targetInterface);
        }
    }

    /**
     * Receives a packet, decapsulates it and routes it to the network layer.
     * @param packet The packet to receive.
     */
    @Override
    public void receivePacket(Packet packet) {
        Packet unframed = linkProtocol.decapsulate(packet);
        networkProtocol.routePacket(unframed, this);
    }

    public Node getOwner() {
        return owner;
    }
}
