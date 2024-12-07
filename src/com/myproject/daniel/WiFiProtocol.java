package com.myproject.daniel;

public class WiFiProtocol implements ILinkLayerProtocol {
    @Override
    public Packet encapsulate(Packet payload) {
        payload.addHeader("WiFiHeader");
        return payload;
    }

    @Override
    public Packet decapsulate(Packet frame) {
        frame.removeHeader("WiFiHeader");
        return frame;
    }
}
