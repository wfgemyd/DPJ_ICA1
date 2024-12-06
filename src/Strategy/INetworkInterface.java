package Strategy;

public interface INetworkInterface {
    void connect(IPhysicalMedium medium);
    void sendPacket(Packet packet);
    void receivePacket(Packet packet);
}