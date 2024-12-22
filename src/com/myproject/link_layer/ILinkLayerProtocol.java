package com.myproject.link_layer;

import com.myproject.core.Packet;

public interface ILinkLayerProtocol {
    /**
     * Encapsulates a packet with the appropriate link layer headers. Did not add tail for simplicity.
     *
     * @param payload Packet instance accepted from the network layer.
     * @return Frame containing the packet, header and tail.
     */

    Packet encapsulate(Packet payload);
    Packet decapsulate(Packet frame);
}
