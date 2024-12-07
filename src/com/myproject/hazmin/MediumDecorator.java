package com.myproject.hazmin;

import com.myproject.daniel.IPhysicalMedium;
import com.myproject.daniel.Packet;
import com.myproject.daniel.INetworkInterface;

public abstract class MediumDecorator implements IPhysicalMedium {
    protected final IPhysicalMedium wrapped;

    public MediumDecorator(IPhysicalMedium wrapped) {
        this.wrapped = wrapped;
    }

    @Override
    public void transmit(Packet packet, INetworkInterface from, INetworkInterface to) {
        wrapped.transmit(packet, from, to);
    }

    @Override
    public void setDelay(long delay) {
        wrapped.setDelay(delay);
    }

    @Override
    public void setErrorRate(double rate) {
        wrapped.setErrorRate(rate);
    }
}
