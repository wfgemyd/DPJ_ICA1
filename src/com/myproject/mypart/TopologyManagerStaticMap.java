package com.myproject.mypart;

import java.util.HashMap;
import java.util.Map;

public class TopologyManagerStaticMap {
    private static final Map<String, INetworkInterface> routeMap = new HashMap<>();

    public static void set(String address, INetworkInterface intf) {
        routeMap.put(address, intf);
    }

    public static INetworkInterface getTargetInterfaceFor(String destAddress) {
        return routeMap.get(destAddress);
    }
}
