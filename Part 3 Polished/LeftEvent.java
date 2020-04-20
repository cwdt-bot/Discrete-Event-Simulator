package cs2030.simulator;
/**
 * Extends from Event and models the event when a customer leaves after deciding not to wait.
 * No Customer/Server match.
 */

public class LeftEvent extends Event {
    /**
     * LeftEvents do not have a Server associated with them, 
     * hence null. 
     */
    private static final Server numServer = null;
    private static final int eventWeight = 4;

    /**
     * Creates LeftEvent that indicates a Customer leaving. 
     * A Customer leaves immediately after arrival if 
     * it cannot wait or be served. 
     * 
     * @param c Customer leaving
     */
    public LeftEvent(Customer c) {
        super(c,LeftEvent.numServer,c.arrTime(),LeftEvent.eventWeight);
    }

    @Override
    public String toString() {
        return String.format("%.3f", this.getTime()) + " " + c + " leaves";
    }
}