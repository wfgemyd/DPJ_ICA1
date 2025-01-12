package com.myproject.main;

import com.myproject.factory.ProtocolStackFactory;
import com.myproject.config.ConfigLoader;
import com.myproject.core.INetworkInterface;
import com.myproject.core.Node;
import com.myproject.core.Packet;
import com.myproject.core.PhysicalInterface;
import com.myproject.link_layer.WiFiProtocol;
import com.myproject.physical_layer.NoiseMediumDecorator;
import com.myproject.utils.*;
import com.myproject.utils.EventBus;
import com.myproject.events.EventScheduler;
import com.myproject.physical_layer.CableMedium;
import com.myproject.physical_layer.IPhysicalMedium;
import com.myproject.physical_layer.WirelessMedium;
import com.myproject.factory.AutoSelectingFactory;
import com.myproject.network_layer.IPv6Protocol;

public class MainSimulation {
    public static void main(String[] args) {
        // setup Logger and StatisticsCollector, observer pattern
        EventBus.getInstance().registerListener(Logger.getInstance());
        StatisticsCollector stats = new StatisticsCollector();
        EventBus.getInstance().registerListener(stats);

        ProtocolStackFactory autoFactory = new AutoSelectingFactory();

        // read config
        String ipAddressA = ConfigLoader.getConfig("ipAddressA");
        String linkLayerProtocolA = ConfigLoader.getConfig("linkLayerProtocolA");

        String ipAddressB = ConfigLoader.getConfig("ipAddressB");
        String linkLayerProtocolB = ConfigLoader.getConfig("linkLayerProtocolB");

        String ipAddressC = ConfigLoader.getConfig("ipAddressC");
        String linkLayerProtocolC = ConfigLoader.getConfig("linkLayerProtocolC");

        // create Nodes
        Node nodeA = autoFactory.createNode("A", ipAddressA, linkLayerProtocolA);
        Node nodeB = autoFactory.createNode("B", ipAddressB, linkLayerProtocolB);
        Node nodeC = autoFactory.createNode("C", ipAddressC, linkLayerProtocolC);

        // get interfaces from the created Nodes
        INetworkInterface intfA = nodeA.getInterfaces().getFirst();
        INetworkInterface intfB = nodeB.getInterfaces().getFirst();
        INetworkInterface intfC = nodeC.getInterfaces().getFirst();

        // manually add second interface to the node A
        INetworkInterface intfA2 = new PhysicalInterface(nodeA, new WiFiProtocol(), new IPv6Protocol("fe80::1"));
        nodeA.addInterface(intfA2);

        // read physical medium config
        String cableDelayStr = ConfigLoader.getConfig("cableDelay");
        String cableErrorRateStr = ConfigLoader.getConfig("cableErrorRate");
        String noiseExtraErrorStr = ConfigLoader.getConfig("noiseExtraError");

        // set defaults in case null
        long cableDelayValue = cableDelayStr != null ? Long.parseLong(cableDelayStr) : 50;
        double cableErrorRateValue = cableErrorRateStr != null ? Double.parseDouble(cableErrorRateStr) : 0.05;
        double noiseExtraErrorValue = noiseExtraErrorStr != null ? Double.parseDouble(noiseExtraErrorStr) : 0.1;

        // Configure cable with noise
        IPhysicalMedium cable = new CableMedium();
        cable.setDelay(cableDelayValue);
        cable.setErrorRate(cableErrorRateValue);
        IPhysicalMedium noisyCable = new NoiseMediumDecorator(cable, noiseExtraErrorValue);

        // Connect A + B via cable with noise
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

        // Send multiple packets to B
        Packet p = new Packet("Hello from A to B", ipAddressB);
        Packet p1 = new Packet("Hello 1 from A to B", ipAddressB);
        Packet p2 = new Packet("Hello 2 from A to B", ipAddressB);
        Packet p3 = new Packet("Hello 3 from A to B", ipAddressB);

        intfA.sendPacket(p);
        intfA.sendPacket(p1);
        intfA.sendPacket(p2);
        intfA.sendPacket(p3);

        // Send packets to C as well
        Packet pC = new Packet("Hello from A2 to C", ipAddressC);
        Packet pC1 = new Packet("Another msg to C", ipAddressC);
        intfA2.sendPacket(pC);
        intfA2.sendPacket(pC1);

        EventScheduler.getInstance().run();

        System.out.println("Cable Delay: " + cableDelayValue);
        System.out.println("Cable Error Rate: " + cableErrorRateValue);
        System.out.println("Noise Extra Error: " + noiseExtraErrorValue);

        stats.printStats();
    }
}
