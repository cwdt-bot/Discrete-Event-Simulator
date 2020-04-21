package cs2030.simulator;

import java.util.NoSuchElementException;

/**
 * Extends from Event and models the event when a customer leaves after deciding
 * not to wait. No Customer/Server match.
 */

public class LeftEvent extends Event {
    /**
     * LeftEvents do not have a Server associated with them, 
     * hence the NULL_SERVER
     */
    private static final Server numServer = Server.NULL_SERVER;

    /**
     * Creates LeftEvent that indicates a Customer leaving. 
     * A Customer leaves immediately after arrival if 
     * it cannot wait or be served. 
     * 
     * @param c Customer leaving
     */
    public LeftEvent(Customer c) {
        super(c,LeftEvent.numServer,c.arrTime());
    }

    @Override
    public String toString() {
        return String.format("%.3f", this.getTime()) + " " + c + " leaves";
    }

    @Override
    public Server server() {
        throw new NoSuchElementException("LeftEvent does not have a Server associated with it");
    }
}