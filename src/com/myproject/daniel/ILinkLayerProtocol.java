package com.myproject.daniel;

public interface ILinkLayerProtocol {
    Packet encapsulate(Packet payload);
    Packet decapsulate(Packet frame);
}
