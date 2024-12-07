package com.myproject.mypart;

public class MainSimulation {
    public static void main(String[] args) {
        // Register the Logger as an observer
        EventBus.getInstance().registerListener(Logger.getInstance());

        // Create nodes
        Node nodeA = new Node("A");
        Node nodeB = new Node("B");

        // For now, manually create protocols.
        // Later, the Abstract Factory will replace these manual steps.
        ILinkLayerProtocol ethernet = new EthernetProtocol();
        INetworkLayerProtocol ipv4A = new IPv4Protocol("192.168.0.1");
        INetworkLayerProtocol ipv4B = new IPv4Protocol("192.168.0.2");

        // Build interfaces
        INetworkInterface intfA = new PhysicalInterface(nodeA, ethernet, ipv4A);
        INetworkInterface intfB = new PhysicalInterface(nodeB, ethernet, ipv4B);

        nodeA.addInterface(intfA);
        nodeB.addInterface(intfB);

        // Create a physical medium
        IPhysicalMedium cable = new CableMedium();

        // TODO: Teams' Part:
        // When the Decorator will be added (e.g., NoiseMediumDecorator) and Abstract Factory (e.g., BasicIPv4EthernetFactory),
        // we will use code like this:

        // ProtocolStackFactory factoryA = new BasicIPv4EthernetFactory("192.168.0.1");
        // ProtocolStackFactory factoryB = new BasicIPv4EthernetFactory("192.168.0.2");

        // INetworkInterface intfA = new PhysicalInterface(nodeA, factoryA.createLinkLayer(), factoryA.createNetworkLayer());
        // INetworkInterface intfB = new PhysicalInterface(nodeB, factoryB.createLinkLayer(), factoryB.createNetworkLayer());

        // IPhysicalMedium noisyCable = new NoiseMediumDecorator(cable, 0.1);
        // intfA.connect(noisyCable);
        // intfB.connect(noisyCable);

        // For now, just connect the plain cable
        intfA.connect(cable);
        intfB.connect(cable);

        // Configure topology
        TopologyManagerStaticMap.set("192.168.0.2", intfB);

        // Send a packet from A to B
        Packet p = new Packet("Hello from A to B");
        intfA.sendPacket(p);

        // Run the simulation
        EventScheduler.getInstance().run();
    }
}
