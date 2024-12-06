//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class MainSimulation {
    public static void main(String[] args) {
        // Register the Logger as an observer
        EventBus.getInstance().registerListener(Logger.getInstance());

        // Create nodes
        Node nodeA = new Node("A");
        Node nodeB = new Node("B");

        // Run the simulation
        EventScheduler.getInstance().run();
    }
}