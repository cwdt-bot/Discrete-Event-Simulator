package cs2030.simulator;

/** 
 * Extends from Event and models an arrival of a customer. There is no Customer/Server match, 
 * so the event contains only a Customer and the Customer's arrival timing. 
 *
 */
public class ArrivalEvent extends Event {
    /**
     * Arrival events do not have a server associated with them,
     * hence the null. 
     */
    private static final Server numServer = null;
    /**
     * Arrival events should always come first, so they have the 
     * highest priority (smallest int).
     */
    private static final int eventWeight = 0;

    /**
     * Creates ArrivalEvent for given Customer. 
     * @param c Customer arriving
     */
    public ArrivalEvent(Customer c) {
        super(c,ArrivalEvent.numServer,c.arrTime(),ArrivalEvent.eventWeight);
    }

    @Override
    public String toString() {
        return String.format("%.3f",c.arrTime()) + " " + c + " arrives";
    }
}