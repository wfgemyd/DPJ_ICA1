package com.myproject.core;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the data that can be sent over the network.
 */
public class Packet {
    private String data;
    private String destination;
    private final List<String> headers = new ArrayList<>();

    /**
     * Packet constructor.
     *
     * @param data The string data to be sent.
     * @param destination IP Address of the destination.
     */
    public Packet(String data, String destination) {
        this.data = data;
        this.destination = destination;
    }

    /**
     * @return The IP address of the destination.
     */
    public String getDestination() {
        return destination;
    }

    /**
     * Encapsulate the packet with a header on various layers. Currently stored as a list of strings for simplicity.
     * @param header String to add.
     */
    public void addHeader(String header) {
        headers.add(header);
    }

    /**
     * Decapsulate the packet by removing a header.
     * @param header String to remove.
     */
    public void removeHeader(String header) {
        headers.remove(header);
    }
}