package com.myproject.physical_layer;

import com.myproject.core.INetworkInterface;
import com.myproject.core.Packet;

public interface IPhysicalMedium {
    void transmit(Packet packet, INetworkInterface from, INetworkInterface to);
    void setDelay(long delay);
    void setErrorRate(double rate);
    double getErrorRate(); // Add this method
}