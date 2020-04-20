package cs2030.simulator;
/**
 * Models occurences within a store. This class is abstract because we need to know 
 * what kind of event it is. An event by itself lacks information. 
 * 
 * <p>
 * There are different events current tracked by the state string parameter. All are compared
 * via the time > id > event weight as indicated in the compareTo() method defined as 
 * part of the Comparable interface. 
 * </p>
 * 
 * <p>
 * Event weight is the priority given to each type of event subclass which indicates
 * which should event the store should process first. 
 * </p>
 * 
 * <p>
 * Implementing classes: ArrivalEvent, DoneEvent, LeftEvent, ServeEvent, WaitEvent, ReturnEvent.
 * </p>
 */

public abstract class Event implements Comparable<Event> {
    /**
     * Each event can hold a Customer, a Server and must have a time. 
     * Each event should also have a weight to indicate priority. 
     */
    protected final Customer c;
    protected final Server s;
    private final double time;
    private final int eventWeight;

    /**
     * Constructs an event. Events that do not include a Waiter/Customer match has a null value for 
     * Event.Waiter.
     * @param c Customer involved
     * @param s Waiter involved
     * @param time event time 
     * @param weight priority of the event; smaller means higher priority.
     */
    protected Event(Customer c, Server s, double time, int weight) {
        this.c = c;
        this.s = s;
        this.time = time;
        this.eventWeight = weight;
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
     * <p> Priority heirarchy goes time > customer id > event weight. </p>
     */
    public int compareTo(Event e) {
        if (e.getTime() == this.getTime()) {
            if (this.cust().id() == e.cust().id()) {
                return this.weight() - e.weight();
            } else {
                return this.cust().id() - e.cust().id();
            }
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

    /**
     * Gets the event priority. A higher number means a lower priority. 
     * @return int indicated priority of the event. 
     */
    private int weight() {
        return this.eventWeight;
    }
    
}
