package com.myproject.events;

import com.myproject.core.INetworkInterface;
import com.myproject.core.Packet;
import com.myproject.core.PhysicalInterface;

/**
 * Event representing packet transmission between network interfaces.
 */
public class TransmissionEvent extends Event {
    private final Packet packet;
    private final INetworkInterface from;
    private final INetworkInterface to;
    private final double errorRate;
    private boolean lost;

    /**
     * Constructor.
     * @param time Time when the event should be executed.
     * @param packet Packet to transmit.
     * @param from Source interface.
     * @param to Destination interface.
     * @param errorRate Error rate for the transmission.
     */
    public TransmissionEvent(long time, Packet packet, INetworkInterface from, INetworkInterface to, double errorRate) {
        super(time);
        this.packet = packet;
        this.from = from;
        this.to = to;
        this.errorRate = errorRate;
    }

    /**
     * Transmit packet from one interface to another.
     * The packet is lost when the random number < error rate, representing the real world probaility of packet loss.
     */
    @Override
    public void execute() {
        double roll = Math.random();
        lost = (roll < errorRate);

        if (!lost) {
            to.receivePacket(packet);
        }
        EventBus.getInstance().notify(this);
    }

    /**
     * Get result of the event as a String for further usage in logging statistics.
     *
     * @return Description of the event.
     */
    @Override
    public String getDescription() {
        PhysicalInterface fromPI = (PhysicalInterface) from;
        PhysicalInterface toPI = (PhysicalInterface) to;
        if (lost) {
            return "Packet lost in transmission from " + fromPI.getOwner().getName();
        } else {
            return "Packet delivered to " + toPI.getOwner().getName();
        }
    }
}
