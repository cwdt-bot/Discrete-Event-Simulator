package cs2030.simulator;

import java.util.NoSuchElementException;

/** 
 * Extends from Event and models an arrival of a customer. There is no Customer/Server match, 
 * so the event contains only a Customer and the Customer's arrival timing. 
 *
 */
public class ArrivalEvent extends Event {
    /**
     * Arrival events do not have a server associated with them,
     * hence the NULL SERVER. 
     */
    private static final Server numServer = Server.NULL_SERVER;


    /**
     * Creates ArrivalEvent for given Customer. 
     * @param c Customer arriving
     */
    public ArrivalEvent(Customer c) {
        super(c,ArrivalEvent.numServer,c.arrTime());
    }

    @Override
    public String toString() {
        return String.format("%.3f",c.arrTime()) + " " + c + " arrives";
    }
    
    @Override
    public Server server() {
        throw new NoSuchElementException("ArrivalEvent does not have a Server associated with it");
    }
}