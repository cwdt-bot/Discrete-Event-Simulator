package cs2030.simulator;

/**
 * Extends from Event and models the event when a customer is finished with service and leaves.
 * There is a Customer/Server match.
 */

public class DoneEvent extends Event {
    private static final int eventWeight = 2;

    /**
     * Creates a DoneEvent with Customer/Server match at the specified time.
     * @param c Customer involved
     * @param s Server involved
     * @param time double indicating time of occurence
     */
    public DoneEvent(Customer c, Server s, double time) {
        super(c,s,time,DoneEvent.eventWeight);
    }

    @Override
    public String toString() {
        return String.format("%.3f", this.getTime()) + " " + c + " done serving by " + s;
    }
}