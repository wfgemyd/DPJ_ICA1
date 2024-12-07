package com.myproject.mypart;

import java.util.ArrayList;
import java.util.List;

public class Packet {
    private String data;
    private final List<String> headers = new ArrayList<>();

    public Packet(String data) {
        this.data = data;
    }

    public void addHeader(String header) {
        headers.add(header);
    }

    public void removeHeader(String header) {
        headers.remove(header);
    }
}
