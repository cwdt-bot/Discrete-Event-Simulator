package cs2030.simulator;

/** 
 * Models an event where a customer is served by a Server.
 * Customer/Server match.
 * 
 */

public class ServeEvent extends Event {
    private static final int eventWeight = 1;

    public ServeEvent(Customer c, Server s, double time) {
        super(c,s,time,ServeEvent.eventWeight);
    }

    public int weight() {
        return ServeEvent.eventWeight;
    }

    @Override
    public String toString() {
        return String.format("%.3f", this.getTime()) + " " + c + " served by " + s;
    }
}
