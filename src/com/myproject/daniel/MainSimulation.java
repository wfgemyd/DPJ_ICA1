package com.myproject.daniel;

import com.myproject.anna.*;
import com.myproject.hazmin.*;


public class MainSimulation {
    public static void main(String[] args) {
        // Register the Logger as an observer
        EventBus.getInstance().registerListener(Logger.getInstance());

        // get configs to determine which factory and IP to use
        String selectedFactory = ConfigLoader.getConfig("factoryType");
        String ipAddress = ConfigLoader.getConfig("ipAddress");

        // selection of factory for node A
        ProtocolStackFactory factoryA;
        if ("IPv4Ethernet".equals(selectedFactory)) {
            factoryA = new BasicIPv4EthernetFactory(ipAddress);
        } else if ("IPv6WiFi".equals(selectedFactory)) {
            factoryA = new AdvancedIPv6WiFiFactory(ipAddress);
        } else {
            factoryA = new BasicIPv4EthernetFactory(ipAddress); // default
        }

        // Create another factory for Node C with a different protocol set
        // hardcode IPv6WiFi for demonstration
        ProtocolStackFactory factoryC = new AdvancedIPv6WiFiFactory("fe80::1");

        // create devices
        Node nodeA = new Node("A");
        Node nodeB = new Node("B");
        Node nodeC = new Node("C");

        // A -> B connection
        INetworkInterface intfA = new PhysicalInterface(nodeA, factoryA.createLinkLayer(), factoryA.createNetworkLayer());
        INetworkInterface intfB = new PhysicalInterface(nodeB, new EthernetProtocol(), new IPv4Protocol("192.168.0.2"));

        // A -> C connection
        INetworkInterface intfA2 = new PhysicalInterface(nodeA, factoryC.createLinkLayer(), factoryC.createNetworkLayer());
        INetworkInterface intfC = new PhysicalInterface(nodeC, factoryC.createLinkLayer(), factoryC.createNetworkLayer());

        nodeA.addInterface(intfA);
        nodeA.addInterface(intfA2);  // Node A now has two interfaces
        nodeB.addInterface(intfB);
        nodeC.addInterface(intfC);

        // Read cable/wireless config
        String cableDelayStr = ConfigLoader.getConfig("cableDelay");
        String cableErrorRateStr = ConfigLoader.getConfig("cableErrorRate");
        String noiseExtraErrorStr = ConfigLoader.getConfig("noiseExtraError");

        long cableDelayValue = cableDelayStr != null ? Long.parseLong(cableDelayStr) : 50;
        double cableErrorRateValue = cableErrorRateStr != null ? Double.parseDouble(cableErrorRateStr) : 0.05;
        double noiseExtraErrorValue = noiseExtraErrorStr != null ? Double.parseDouble(noiseExtraErrorStr) : 0.1;

        System.out.println("Cable Delay: " + cableDelayValue);
        System.out.println("Cable Error Rate: " + cableErrorRateValue);
        System.out.println("Noise Extra Error: " + noiseExtraErrorValue);

        // First connection (A-B) via cable + noise
        IPhysicalMedium cable = new CableMedium();
        cable.setDelay(cableDelayValue);
        cable.setErrorRate(cableErrorRateValue);
        IPhysicalMedium noisyCable = new NoiseMediumDecorator(cable, noiseExtraErrorValue);

        intfA.connect(noisyCable);
        intfB.connect(noisyCable);

        // Second connection (A-C) via wireless (optional: we could also decorate this)
        IPhysicalMedium wireless = new WirelessMedium();
        wireless.setDelay(30);      // different delay for wireless
        wireless.setErrorRate(0.02); // different base error rate

        // If it will be commented out then it will not be decorated.
         IPhysicalMedium noisyWireless = new NoiseMediumDecorator(wireless, 0.05);
         intfA2.connect(noisyWireless);
         intfC.connect(noisyWireless);

        // For now, let's leave this wireless link without extra noise:
        intfA2.connect(wireless);
        intfC.connect(wireless);

        // Set routing info for node B and node C
        TopologyManagerStaticMap.set("192.168.0.2", intfB);
        TopologyManagerStaticMap.set("fe80::1", intfC);

        StatisticsCollector stats = new StatisticsCollector();
        EventBus.getInstance().registerListener(stats);

        // Send multiple packets to B
        Packet p = new Packet("Hello from A to B", "192.168.0.2");
        Packet p1 = new Packet("Hello 1 from A to B", "192.168.0.2");
        Packet p2 = new Packet("Hello 2 from A to B", "192.168.0.2");
        Packet p3 = new Packet("Hello 3 from A to B", "192.168.0.2");

        intfA.sendPacket(p);
        intfA.sendPacket(p1);
        intfA.sendPacket(p2);
        intfA.sendPacket(p3);

        // Send packets to C as well
        Packet pC = new Packet("Hello from A to C", "fe80::1");
        Packet pC1 = new Packet("Another msg to C", "fe80::1");
        intfA2.sendPacket(pC);
        intfA2.sendPacket(pC1);

        System.out.println("\n");
        System.out.println("Selected factory: " + selectedFactory);
        System.out.println("Selected IP: " + ipAddress);
        System.out.println("\n");


        EventScheduler.getInstance().run();

        stats.printStats();
    }
}
