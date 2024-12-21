package com.myproject.link_layer;

import com.myproject.core.Packet;

public interface ILinkLayerProtocol {
    Packet encapsulate(Packet payload);
    Packet decapsulate(Packet frame);
}
