package cs2030.simulator;

/** 
 * Extends from Event and models an event where a customer is served by a Server.
 * There is a Customer/Server match.
 * 
 */

public class ServeEvent extends Event {

    /**
     * Creates a ServeEvent indicate that a Customer is being served by a
     * Server at the specified timing.
     * @param c Customer being served
     * @param s Server serving the Customer
     * @param time double representing time of occurence 
     */
    public ServeEvent(Customer c, Server s, double time) {
        super(c,s,time);
    }

    @Override
    public String toString() {
        return String.format("%.3f", this.getTime()) + " " + c + " served by " + s;
    }
}
