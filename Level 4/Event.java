class Event implements Comparable<Event> {
    private final Customer c;
    private final Waiter w;
    private final double time;
    private final String state;

    // this is used when there is a waiter/customer match
    // used to create 'wait'/'served' events
    Event(Customer c, Waiter w, double time, String state) {
        this.c = c;
        this.w = w;
        this.time = time;
        this.state = state;

    }

    // this constructor is used when the customer cannot be served, so no waiter can be assigned
    // this is used to create 'arrives'/'left' events
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
            return String.format("%.3f", this.getTime()) + ": Customer " + c.id() + " is done";
        } else if (state.equals("waits")) {
            return String.format("%.3f", this.getTime()) + ": Customer " + c.id() +  " waits";
        } else if (state.equals("served")) {
            return String.format("%.3f", this.getTime()) + " " + c.id() + " served";
        } else { //arrives
            return "" + this.c;
        }
    }
    
    public int compareTo(Event e) {
        if (e.getTime() == this.getTime()) {
            if (this.cust().id() == e.cust().id()) { 
                //arrive event should be higher priority than 'leave' event
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
    
}
