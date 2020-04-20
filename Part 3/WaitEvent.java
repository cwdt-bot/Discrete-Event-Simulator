package cs2030.simulator;

/**
 * Models an event that customer waits for a Server to serve itself.
 * Customer/Server event.
 */

public class WaitEvent extends Event {
    private static final int eventWeight = 3;

    public WaitEvent(Customer c, Server s, double time) {
        super(c,s,time,WaitEvent.eventWeight);
    }

    public int weight() {
        return WaitEvent.eventWeight;
    }

    @Override
    public String toString() {
        return String.format("%.3f", this.getTime()) + " " + c + 
            " waits to be served by " + s;
    }
}