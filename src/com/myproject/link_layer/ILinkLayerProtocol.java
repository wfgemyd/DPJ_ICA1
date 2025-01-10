package com.myproject.link_layer;

import com.myproject.core.Packet;

/**
 * Abstraction for Data Link Layer protocols.
 */
public interface ILinkLayerProtocol {
    /**
     * Encapsulates a packet with the appropriate MAC address headers, returning a frame (still of Packet class for simplicity). Did not add tail for simplicity.
     *
     * @param packet Packet instance accepted from the network layer.
     * @return Frame containing the packet, header and tail.
     */
    Packet encapsulate(Packet packet);

    /**
     * Decapsulates a frame to extract the packet by removing MAC address header.
     *
     * @param frame Packet instance (frame) accepted from the physical layer.
     * @return Packet extracted from the frame.
     */
    Packet decapsulate(Packet frame);
}
