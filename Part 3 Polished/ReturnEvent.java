package cs2030.simulator;
/**
 * Extends from event and models the event when a server returns from his break. 
 * No customer/server match, only has server
 */

public class ReturnEvent extends Event {
    private static final Customer customer = null;
    private static final int eventWeight = 8;

    /**
     * Creates a ReturnEvent modelling the return of a Server from a break.
     * @param s Server returning from break
     * @param time double representing the time the Server returns
     */
    public ReturnEvent(Server s, double time) {
        super(ReturnEvent.customer, s, time, ReturnEvent.eventWeight);
    }

    @Override
    public String toString() {
        return String.format("%.3f", this.getTime()) + " server " + s.id() + " returns from break";
    }
}