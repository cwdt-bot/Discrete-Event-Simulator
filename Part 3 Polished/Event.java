package cs2030.simulator;
/**
 * Models occurences within a store. This class is abstract because we need to know 
 * what kind of event it is. An event by itself lacks information. 
 * 
 * <p>
 * There are different events current tracked by the state string parameter. All are compared
 * via the time > id as indicated in the compareTo() method defined as 
 * part of the Comparable interface. 
 * </p>
 * 
 * <p>
 * Implementing classes: ArrivalEvent, DoneEvent, LeftEvent, ServeEvent, WaitEvent, ReturnEvent.
 * </p>
 */

public abstract class Event implements Comparable<Event> {
    /**
     * Each event can hold a Customer, a Server and must have a time. 
     */
    protected final Customer c;
    protected final Server s;
    private final double time;

    /**
     * Constructs an event. Events that do not include a Server/Customer match has a null
     * representative in the corresponding field. 
     * @param c Customer involved
     * @param s Waiter involved
     * @param time event time 
     */
    protected Event(Customer c, Server s, double time) {
        this.c = c;
        this.s = s;
        this.time = time;
    }

    /**
     * Gets the time of the event. 
     * @return double representing the time of the event occurence.
     */
    public double getTime() {
        return this.time;
    }
    
    /**
     * Implements compareTo() as part of the Comparable Interface. 
     * 
     * <p> Priority heirarchy goes time > customer id > </p>
     * 
     * <p> Note that ReturnEvent has a NULL_CUSTOMER and will win all timing tiebreaks
     * </p>
     */
    public int compareTo(Event e) {
        if (e.getTime() == this.getTime()) {
            return this.cust().compareTo(e.cust());
        } else if (this.getTime() < e.getTime()) {
            return -1;
        } else {
            return 1;
        }
    }

    /**
     * Gets the customer involved with the event.
     * @return Customer object involved with the event.
     */
    public Customer cust() {
        return this.c;
    }

    /**
     * Gets the Server involved with the event.
     * @return Server object involved with the event.
     */
    public Server server() {
        return this.s;
    }
}
