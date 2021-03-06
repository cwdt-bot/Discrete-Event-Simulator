/**
 * Models occurences within a store. This class is abstract because we need to know 
 * what kind of event it is. An event by itself lacks information. 
 * <p>There are different events current tracked by the state string parameter. All are compared
 * via the time they occur</p>
 * 
 * <p>Event weight is the priority given to each type of event subclass which indicates
 * which should event the restaurant should process first </p>
 * 
 * <p>Implementing classes: ArrivalEvent, DoneEvent, LeftEvent, ServeEvent, WaitEvent
 */

abstract class Event implements Comparable<Event> {
    protected final Customer c;
    protected final Waiter w;
    private final double time;
    private final int eventWeight;

    /**
     * Constructs an event that has a Customer-Waiter match. Events that fall into
     * this category are: wait, served, done
     * @param c Customer involved 
     * @param w Waiter involved
     * @param time event time 
     * @param state describes the event
     */
    Event(Customer c, Waiter w, double time, int weight) {
        this.c = c;
        this.w = w;
        this.time = time;
        this.eventWeight = weight;
    }

    double getTime() {
        return this.time;
    }
    
    public int compareTo(Event e) {
        if (e.getTime() == this.getTime()) {
            if (this.cust().id() == e.cust().id()) {  
                // 'arrive' event should be higher priority than 'leave' event
                if (this.weight() == e.weight()) {
                    return 0;
                } else if (this.weight() < e.weight()) {
                    return -1;
                } else {
                    return 1;
                }
            //smaller Customer ID has priority    
            } else if (this.cust().id() < e.cust().id()) {
                return -1;
            } else {
                return 1;
            }
        } else if (this.getTime() < e.getTime()) {
            return -1;
        } else {
            return 1;
        }
    }

    Customer cust() {
        return this.c;
    }

    Waiter waiter() {
        return this.w;
    }

    int weight() {
        return this.eventWeight;
    }
    
}
