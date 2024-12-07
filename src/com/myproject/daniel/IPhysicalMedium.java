package com.myproject.daniel;

public interface IPhysicalMedium {
    void transmit(Packet packet, INetworkInterface from, INetworkInterface to);
    void setDelay(long delay);
    void setErrorRate(double rate);
}
