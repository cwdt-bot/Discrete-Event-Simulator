import java.util.PriorityQueue;
import java.util.ArrayList;

/**
 * Creates an environment for Waiters and Customers to interact.
 * 
 * <p> Each store tracks statistics such as number of people served, number of people 
 * who waited, who left as well as time spent waiting. </p>
 */
class Store {
    private final String name;
    /** Store-employed waiters */
    private ArrayList<Waiter> waiters = new ArrayList<>();
    /**Stores events in chronological order */
    private PriorityQueue<Event> q = new PriorityQueue<>();
    private int served = 0;
    private int waited = 0;
    private int left = 0;
    private double waitTime = 0;

    Store(String name) {
        this.name = name;
    }

    void employ(Waiter w) {
        this.waiters.add(w);
    }

    String name() {
        return this.name;
    }

    /**
     * Returns the earliest available employed waiter that can serve a customer at the
     * specified time. If all are equally busy, then the first waiter is returned
     * @param time specified time when a waiter is wanted
     * @return Waiter object 
     */
    Waiter receives(double time) {
        int min = Integer.MAX_VALUE;
        Waiter assigned = waiters.get(0);
        for (Waiter w : waiters) {
            int waiting = w.inWait(time);
            if (waiting == 0) {
                assigned = w;
                break; //if there is no wait, means that waiter can serve customer right away
            } else if (waiting <= min) {
                min = waiting;
                assigned = w;
            }
        }
        return assigned;
    }

    /**
     * Adds events to the internal PriorityQueue. Checks what kind of event is being
     * added and updates statistics
     * @param e any Event object
     */
    void addEvent(Event e) {
        String state = e.state();
        this.q.add(e);
        if (state.equals("left")) {
            this.left++;
        } else if (state.equals("waits")) {
            this.waited++;
        } else if (state.equals("served")) {
            this.served++;
        }
    }

    /**
     * Returns the full schedule of the store
     * @return PriorityQueue represent schedule of the store
     */
    PriorityQueue<Event> showSchedule() {
        return this.q;
    }

    int numServed() {
        return this.served;
    }

    int numWaited() {
        return this.waited;
    }

    int numLeft() {
        return this.left;
    }

    double avgWaitTime() {
        return this.waitTime / this.numServed();
    }

    void addToWaitTime(double x) {
        this.waitTime += x;
    }

}
            
            

    
