/**
 * Models occurences within a store
 * <p>
 * There are different events current tracked by the state string parameter. All are compared
 * via the time they occur
 */

class Event implements Comparable<Event> {
    private final Customer c;
    private final Waiter w;
    private final double time;
    /**
     * states are: arrives, left, waits, served, done
     * <p>
     * arrives: Customer arrival 
     * <p>
     * left: Customer leaves after deciding not to wait
     * <p> 
     * waits: Customer arrives and chooses to wait
     * <p>
     * served: Customer is being served by Waiter
     * <p>
     * done: Customer service has been completed
     */
    private final String state;

    /**
     * Constructs an event that has a Customer-Waiter match. Events that fall into
     * this category are: wait, served, done
     * @param c Customer involved 
     * @param w Waiter involved
     * @param time event time 
     * @param state describes the event
     */
    Event(Customer c, Waiter w, double time, String state) {
        this.c = c;
        this.w = w;
        this.time = time;
        this.state = state;

    }

    /**
     * Constructs an event that has no Customer-Waiter match. Events that fall into
     * this category are: arrives, left
     * @param c Customer involved
     * @param time  event time
     * @param state describes the event
     */
    Event(Customer c, double time, String state) {
        this.c = c;
        this.w = null;
        this.time = time;
        this.state = state;
    }

    double getTime() {
        return this.time;
    }
    
    public String toString() {
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
    }
    /**
     * Implements a natural order for events according to the time of occurence
     * ties are broken by Customer ID, followed by whether the event is an 'arrives'
     * event. 
     * Earlier events return a negative int when compared to a later event
     */
    public int compareTo(Event e) {
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
    }


    String state() {
        return this.state;
    }

    Customer cust() {
        return this.c;
    }

    Waiter waiter() {
        return this.w;
    }
    
}
