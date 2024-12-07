package com.myproject.daniel;

public class TransmissionEvent extends Event {
    private final Packet packet;
    private final INetworkInterface from;
    private final INetworkInterface to;
    private final double errorRate;
    // We'll store a random value here to avoid calling Math.random() multiple times in getDescription()
    private final double roll;

    public TransmissionEvent(long time, Packet packet, INetworkInterface from, INetworkInterface to, double errorRate) {
        super(time);
        this.packet = packet;
        this.from = from;
        this.to = to;
        this.errorRate = errorRate;
        this.roll = Math.random();
    }

    @Override
    public void execute() {
        if (roll < errorRate) {
            // Packet lost
            EventBus.getInstance().notify(this);
        } else {
            to.receivePacket(packet);
            EventBus.getInstance().notify(this);
        }
    }

    @Override
    public String getDescription() {
        PhysicalInterface fromPI = (PhysicalInterface) from;
        PhysicalInterface toPI = (PhysicalInterface) to;
        if (roll < errorRate) {
            return "Packet lost in transmission from " + fromPI.getOwner().getName();
        } else {
            return "Packet delivered to " + toPI.getOwner().getName();
        }
    }
}
