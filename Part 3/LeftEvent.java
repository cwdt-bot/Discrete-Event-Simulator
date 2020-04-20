package cs2030.simulator;
/**
 * Models the event when a customer leaves after deciding not to wait.
 * No Customer/Server match.
 */

public class LeftEvent extends Event {
    private static final Server numServer = null;
    private static final int eventWeight = 4;

    public LeftEvent(Customer c) {
        super(c,LeftEvent.numServer,c.arrTime(),LeftEvent.eventWeight);
    }

    public int weight() {
        return LeftEvent.eventWeight;
    }

    @Override
    public String toString() {
        return String.format("%.3f", this.getTime()) + " " + c + " leaves";
    }
}