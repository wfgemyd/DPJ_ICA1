package com.myproject.link_layer;

import com.myproject.core.Packet;

public class WiFiProtocol implements ILinkLayerProtocol {

    @Override
    public Packet encapsulate(Packet packet) {
        packet.addHeader("WiFiHeader");
        return packet;
    }

    @Override
    public Packet decapsulate(Packet frame) {
        frame.removeHeader("WiFiHeader");
        return frame;
    }
}
