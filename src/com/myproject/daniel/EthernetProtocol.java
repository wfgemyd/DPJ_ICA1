package com.myproject.daniel;

public class EthernetProtocol implements ILinkLayerProtocol {
    @Override
    public Packet encapsulate(Packet payload) {
        payload.addHeader("EthernetHeader");
        return payload;
    }

    @Override
    public Packet decapsulate(Packet frame) {
        frame.removeHeader("EthernetHeader");
        return frame;
    }
}
