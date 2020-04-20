package cs2030.simulator;
/**
 * Models the event when a server returns from his break
 * No customer/server match, only has server
 */

public class ReturnEvent extends Event {
    private static final Customer customer = null;
    private static final int eventWeight = 8;

    public ReturnEvent(Server s, double time) {
        super(ReturnEvent.customer, s, time, ReturnEvent.eventWeight);
    }

    public int weight() {
        return ReturnEvent.eventWeight;
    }

    @Override
    public String toString() {
        return String.format("%.3f", this.getTime()) + " server " + s.id() + " returns from break";
    }
}