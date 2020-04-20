package cs2030.simulator;

/**
 * Models the event when a customer is finished with service and leaves.
 * Customer/Server match.
 */

public class DoneEvent extends Event {
    private static final int eventWeight = 2;

    public DoneEvent(Customer c, Server s, double time) {
        super(c,s,time,DoneEvent.eventWeight);
    }

    public int weight() {
        return DoneEvent.eventWeight;
    }

    @Override
    public String toString() {
        return String.format("%.3f", this.getTime()) + " " + c + " done serving by " + s;
    }
}