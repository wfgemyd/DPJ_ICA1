public class Node {
    private final String name;
    private final List<INetworkInterface> interfaces = new ArrayList<>();

    public Node(String name) {
        this.name = name;
    }

    public void addInterface(INetworkInterface intf) {
        interfaces.add(intf);
    }

    public List<INetworkInterface> getInterfaces() {
        return interfaces;
    }

    public String getName() {
        return name;
    }
}