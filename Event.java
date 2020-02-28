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
    
    /*public String toString() {
        String state = this.state();
        if (state.equals("left")) {
            return String.format("%.3f", this.getTime()) + " " + c.id() + " leaves";
        } else if (state.equals("done")) {
            return String.format("%.3f", this.getTime()) + " " + c.id() + " done";
        } else if (state.equals("waits")) {
            return String.format("%.3f", this.getTime()) + " " + c.id() +  " waits";
        } else if (state.equals("served")) {
            return String.format("%.3f", this.getTime()) + " " + c.id() + " served";
        } else { //arrives
            return "" + this.c;
        }
    }  doesnt need toString method anymore*/


    /**
     * Implements a natural order for events according to the time of occurence
     * ties are broken by Customer ID, followed by whether the event is an 'arrives'
     * event. 
     * Earlier events return a negative int when compared to a later event
     */
    /*public int compareTo(Event e) {
        if (e.getTime() == this.getTime()) {
            if (this.cust().id() == e.cust().id()) {  
                // 'arrive' event should be higher priority than 'leave' event
                if (this.state().equals(e.state())) {
                    return 0;
                } else if (this.state().equals("arrives")) {
                    return -1;
                } else {
                    return 1;
                }
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
    } */

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
