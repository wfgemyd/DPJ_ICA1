package com.myproject.daniel;

import java.util.ArrayList;
import java.util.List;

public class Packet {
    private String data;
    private String destination;
    private final List<String> headers = new ArrayList<>();

    public Packet(String data, String destination) {
        this.data = data;
        this.destination = destination;
    }

    public String getDestination() {
        return destination;
    }

    public void addHeader(String header) {
        headers.add(header);
    }

    public void removeHeader(String header) {
        headers.remove(header);
    }
}

