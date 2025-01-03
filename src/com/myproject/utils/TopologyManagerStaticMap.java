package com.myproject.utils;

import com.myproject.core.INetworkInterface;

import java.util.HashMap;
import java.util.Map;

/**
 * This class is used to store network interface routes in a static map. Binds a destination address to a network interface.
 */
public class TopologyManagerStaticMap {
    private static final Map<String, INetworkInterface> routeMap = new HashMap<>();

    public static void set(String address, INetworkInterface intf) {
        routeMap.put(address, intf);
    }

    public static INetworkInterface getTargetInterfaceFor(String destAddress) {
        return routeMap.get(destAddress);
    }
}
