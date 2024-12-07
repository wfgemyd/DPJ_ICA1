package com.myproject.mypart;

public interface ILinkLayerProtocol {
    Packet encapsulate(Packet payload);
    Packet decapsulate(Packet frame);
}
