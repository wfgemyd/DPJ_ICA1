package com.myproject.main;

import com.myproject.factory.ProtocolStackFactory;
import com.myproject.config.ConfigLoader;
import com.myproject.core.INetworkInterface;
import com.myproject.core.Node;
import com.myproject.core.Packet;
import com.myproject.core.PhysicalInterface;
import com.myproject.link_layer.WiFiProtocol;
import com.myproject.utils.*;
import com.myproject.events.EventBus;
import com.myproject.events.EventScheduler;
import com.myproject.link_layer.EthernetProtocol;
import com.myproject.network_layer.IPv4Protocol;
import com.myproject.physical_layer.CableMedium;
import com.myproject.physical_layer.IPhysicalMedium;
import com.myproject.physical_layer.WirelessMedium;
import com.myproject.factory.AutoSelectingFactory;
import com.myproject.network_layer.IPv6Protocol;

public class MainSimulation {
    public static void main(String[] args) {
        // create Logger singleton
        EventBus.getInstance().registerListener(Logger.getInstance());
        ProtocolStackFactory autoFactory = new AutoSelectingFactory();

        // create Nodes
        Node nodeA = autoFactory.createNode(
                "A",
                ConfigLoader.getConfig("ipAddressA"),
                ConfigLoader.getConfig("linkLayerProtocolA")
        );

        Node nodeB = autoFactory.createNode(
                "B",
                ConfigLoader.getConfig("ipAddressB"),
                ConfigLoader.getConfig("linkLayerProtocolB")
        );

        Node nodeC = autoFactory.createNode(
                "C",
                ConfigLoader.getConfig("ipAddressC"),
                ConfigLoader.getConfig("linkLayerProtocolC")
        );

        // manually add second interface to the node
        INetworkInterface intfA2 = new PhysicalInterface(nodeA, new WiFiProtocol(), new IPv6Protocol("fe80::1"));
        nodeA.addInterface(intfA2);

        // get interfaces
        INetworkInterface intfA = nodeA.getInterfaces().getFirst();
        INetworkInterface intfB = nodeB.getInterfaces().getFirst();
        INetworkInterface intfC = nodeC.getInterfaces().getFirst();

        // read physical medium config
        String cableDelayStr = ConfigLoader.getConfig("cableDelay");
        String cableErrorRateStr = ConfigLoader.getConfig("cableErrorRate");
        String noiseExtraErrorStr = ConfigLoader.getConfig("noiseExtraError");

        // set defaults in case null
        long cableDelayValue = cableDelayStr != null ? Long.parseLong(cableDelayStr) : 50;
        double cableErrorRateValue = cableErrorRateStr != null ? Double.parseDouble(cableErrorRateStr) : 0.05;
        double noiseExtraErrorValue = noiseExtraErrorStr != null ? Double.parseDouble(noiseExtraErrorStr) : 0.1;

        System.out.println("Cable Delay: " + cableDelayValue);
        System.out.println("Cable Error Rate: " + cableErrorRateValue);
        System.out.println("Noise Extra Error: " + noiseExtraErrorValue);

        // Connect A + B via cable with noise
        IPhysicalMedium cable = new CableMedium();
        cable.setDelay(cableDelayValue);
        cable.setErrorRate(cableErrorRateValue);
        IPhysicalMedium noisyCable = new NoiseMediumDecorator(cable, noiseExtraErrorValue);

        intfA.connect(noisyCable);
        intfB.connect(noisyCable);

        // Connect A + C via wireless
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


        EventScheduler.getInstance().run();

        stats.printStats();
    }
}
