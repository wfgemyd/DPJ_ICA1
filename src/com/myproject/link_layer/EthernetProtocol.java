package com.myproject.link_layer;

import com.myproject.core.Packet;

public class EthernetProtocol implements ILinkLayerProtocol {
    @Override
    public Packet encapsulate(Packet packet) {
        packet.addHeader("EthernetHeader MAC Address");
        return packet;
    }

    @Override
    public Packet decapsulate(Packet frame) {
        frame.removeHeader("EthernetHeader MAC Address");
        return frame;
    }
}
