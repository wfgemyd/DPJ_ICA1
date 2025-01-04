package com.myproject.core;

import com.myproject.link_layer.ILinkLayerProtocol;
import com.myproject.network_layer.INetworkLayerProtocol;
import com.myproject.physical_layer.IPhysicalMedium;
import com.myproject.utils.RoutingManager;

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
     * @param medium The physical medium to connect to.sr
     */
    @Override
    public void connect(IPhysicalMedium medium) {
        this.medium = medium;
    }

    /**
     * Encapsulates, routes and transmits a packet to the target.
     * @param packet The packet to send.
     */
    @Override
    public void sendPacket(Packet packet) {
        if (medium == null) {
            throw new IllegalStateException("Interface not connected to a medium");
        }
        // Pass packet through layers encapsulations
        Packet headPacket = networkProtocol.formatPacket(packet, packet.getDestination());
        Packet framedPacket = linkProtocol.encapsulate(headPacket);

        // routing
        INetworkInterface targetInterface = RoutingManager.getTargetInterfaceFor(packet.getDestination());

        if (targetInterface != null) {
            medium.transmit(framedPacket, this, targetInterface);
        }
        else {
            throw new IllegalStateException("No route found for destination address");
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
