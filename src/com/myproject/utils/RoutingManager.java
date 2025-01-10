package com.myproject.utils;

import com.myproject.core.INetworkInterface;

import java.util.HashMap;
import java.util.Map;

/**
 * Storing, setting and getting routing information.
 * A simple routing manager that maps destination IP addresses to corresponsing network interfaces.
 */
public class RoutingManager {
    private static final Map<String, INetworkInterface> routeMap = new HashMap<>();

    public static void setRoute(String address, INetworkInterface intf) {
        routeMap.put(address, intf);
    }

    public static INetworkInterface getTargetInterfaceFor(String destAddress) {
        return routeMap.get(destAddress);
    }
}
