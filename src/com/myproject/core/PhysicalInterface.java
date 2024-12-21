package com.myproject.core;

import com.myproject.utils.TopologyManagerStaticMap;
import com.myproject.link_layer.ILinkLayerProtocol;
import com.myproject.network_layer.INetworkLayerProtocol;
import com.myproject.physical_layer.IPhysicalMedium;

public class PhysicalInterface implements INetworkInterface {
    private final Node owner;
    private final ILinkLayerProtocol linkProtocol;
    private final INetworkLayerProtocol networkProtocol;
    private IPhysicalMedium medium;

    public PhysicalInterface(Node owner, ILinkLayerProtocol linkProtocol, INetworkLayerProtocol networkProtocol) {
        this.owner = owner;
        this.linkProtocol = linkProtocol;
        this.networkProtocol = networkProtocol;
    }

    @Override
    public void connect(IPhysicalMedium medium) {
        this.medium = medium;
    }

    @Override
    public void sendPacket(Packet packet) {
        Packet framed = linkProtocol.encapsulate(packet);
        INetworkInterface targetInterface = TopologyManagerStaticMap.getTargetInterfaceFor(packet.getDestination());
        if (targetInterface != null) {
            medium.transmit(framed, this, targetInterface);
        }
    }


    @Override
    public void receivePacket(Packet packet) {
        Packet unframed = linkProtocol.decapsulate(packet);
        networkProtocol.routePacket(unframed, this);
    }

    public Node getOwner() {
        return owner;
    }
}
