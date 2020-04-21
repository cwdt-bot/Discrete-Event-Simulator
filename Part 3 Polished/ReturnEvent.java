package cs2030.simulator;

import java.util.NoSuchElementException;

/**
 * Extends from event and models the event when a server returns from his break.
 * No customer/server match, only has server. Hence the NULL_CUSTOMER
 */

public class ReturnEvent extends Event {
    private static final Customer customer = Customer.NULL_CUSTOMER;
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

    @Override
    public Customer cust() {
        throw new NoSuchElementException("ReturnEvent has no Customer associated with it");
    }
}