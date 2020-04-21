package cs2030.simulator;

/**
 * Extends from Event and models an event that customer waits for a Server to serve itself.
 * There is a Customer/Server match.
 */

public class WaitEvent extends Event {
    /**
     * Creates a WaitEvent modelling a Customer waiting in queue to be served 
     * by a Server. 
     * @param c Customer that waits
     * @param s Serve that will eventually serve the Customer
     * @param time double representing the time the Customer starts waiting
     */
    public WaitEvent(Customer c, Server s, double time) {
        super(c,s,time);
    }

    @Override
    public String toString() {
        return String.format("%.3f", this.getTime()) + " " + c + 
            " waits to be served by " + s;
    }
}