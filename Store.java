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
    /** Store-employed waiters. */
    private ArrayList<Waiter> waiters = new ArrayList<>();
    /**Stores events in chronological order. */
    private PriorityQueue<Event> q = new PriorityQueue<>();
    private int served = 0;
    private int waited = 0;
    private int left = 0;
    private double waitTime = 0;

    public Store(String name) {
        this.name = name;
    }

    public void employ(Waiter w) {
        this.waiters.add(w);
    }

    public String name() {
        return this.name;
    }

    /**
     * Returns the earliest available employed waiter that can serve a customer at the
     * specified time. If all are equally busy, then the first waiter is returned.
     * @param time specified time when a waiter is wanted
     * @return Waiter object 
     */
    public Waiter receives(double time) {
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
     * added and updates statistics.
     * @param e any Event object
     */
    public void addEvent(Event e) {
        this.q.add(e);
        if (e instanceof LeftEvent) {
            this.left++;
        } else if (e instanceof WaitEvent) {
            this.waited++;
        } else if (e instanceof ServeEvent) {
            this.served++;
        }
    }

    /** Returns the events that occur in store. 
     * @return PriorityQueue representing schedule of the store.
     */
    public PriorityQueue<Event> showSchedule() {
        return this.q;
    }

    /** Returns number of customers served by the store.
     * @return number of customers served by the store.
     */
    public int numServed() {
        return this.served;
    }

    /**
     * Returns number of customers who waited to be served. 
     * @return number of customers who waited to be served. 
     */
    public int numWaited() {
        return this.waited;
    }

    /** Returns number of customers who left without being served. 
     * @return number of customers who left without being served.
     */
    public int numLeft() {
        return this.left;
    }

    public double avgWaitTime() {
        return this.waitTime / this.numServed();
    }

    /**
     * Tracks the total amount of time that all customers wait. 
     * Updates internal clock this.waitTime.
     * @param x time added to be added to total.
     */
    private void addToWaitTime(double x) {
        this.waitTime += x;
    }

    /**
     * Models the logic of a store accepting a group of customers. 
     * The events that transpire in the store is contained in internal PriorityQueue. 
     * Each customer is processed completely upon arrival, with all associated events
     * going into the store's PriorityQueue. 
     * 
     * <p>Each waiter has their internal service timings updated as part of waiter.serves(). 
     * 
     * @param inc ArrayList of Customers that visit the store
     */
    public void serve(ArrayList<Customer> inc) {
        for (Customer c : inc) {
            double time = c.arrTime();
            Waiter w = this.receives(time);
            int toWait = w.inWait(time);
            boolean waits = c.assess(toWait);
            ArrivalEvent arrive = new ArrivalEvent(c, time); 
            this.addEvent(arrive);                         //add arrives event
            if (!waits) {                                   //doesnt want to wait
                LeftEvent e = new LeftEvent(c,time);
                this.addEvent(e);                          //add leaves event             
            } else if (toWait == 0) {                         // there is no wait
                ServeEvent e = new ServeEvent(c,w,time);     //add served event
                this.addToWaitTime(w.serve(c));
                this.addEvent(e);           
                DoneEvent f = new DoneEvent(c,w,w.finishTime(time));     //add done event
                this.addEvent(f);
            } else {                                         //willing to wait
                double nextService = w.lastTime();
                WaitEvent e = new WaitEvent(c,w,time);       //add waiting event
                ServeEvent f = new ServeEvent(c,w,nextService);     //add served event
                this.addToWaitTime(w.serve(c));
                this.addEvent(e);
                this.addEvent(f);
                DoneEvent g = new DoneEvent(c,w,w.finishTime(nextService));        //add done event
                this.addEvent(g);
            }
        }
    }

}
            
            

    
