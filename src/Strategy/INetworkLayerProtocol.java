package Strategy;

public interface INetworkLayerProtocol {
    void routePacket(Packet packet, INetworkInterface sourceInterface);
    String getAddress();
}