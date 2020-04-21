package cs2030.simulator;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Simulates a Store environment where all Servers and Customers interact. 
 * The schedule of events stored in a PriorityQueue and Servers in a list. 
 * 
 * <p>
 * The Store object holds a RandomNumberGenerator to simulate random events such 
 * as Server rest and, service time and customer arrivals. 
 * </p>
 * 
 * <p>
 * The Store object also tracks some stats like 1. Total Waiting time 2. Number of 
 * Customers served 3. Number of Customers who left. 
 * </p> 
 */
public class Store {
    private final PriorityQueue<Event> events = new PriorityQueue<>();
    private final List<Server> servers = new ArrayList<>();
    private int served = 0;
    private int waited = 0;
    private int left = 0;
    private double waitTime = 0;
    private final RandomGenerator rgen;
    private final double restchance;
    private final double greedychance;


    /**
     * Creates a store object with a RandomGenerator object within. 
     * @param seed The random seed. New seeds will be derived based on this.
     * @param lambda The customer arrival rate.
     * @param mu The customer service rate.
     * @param rho The server resting rate. 
     * @param restchance The chance that a Server will rest.
     * @param greedychance The chance that a Customer is greedy. 
     */
    public Store(int seed, double lambda, double mu, double rho, 
        double restchance, double greedychance) {
        this.rgen = new RandomGenerator(seed, lambda, mu, rho);
        this.restchance = restchance;
        this.greedychance = greedychance;
    }

    /**
     * Employs a Server by adding it into the internal List of Servers.
     * @param s Server to be employed
     */
    public void employ(Server s) {
        this.servers.add(s);
    }

    /**
     * Adds events to the internal PriorityQueue. Checks what kind of event is being
     * added and updates statistics.
     * @param e any Event object
     */
    public void addEvent(Event e) {
        this.events.add(e);
        if (e instanceof LeftEvent) {
            this.left++;
        } else if (e instanceof WaitEvent) {
            this.waited++;
        } else if (e instanceof ServeEvent) {
            this.served++;
        }
    }

    /**
     * Builds a QueueStatus object that represents the status of each 
     * of the Servers in the Store. Note that the order of the Servers 
     * is preserved inside the QueueStatus object in order for the QueueStatus
     * to return the correct index corresponding to the index of the Server
     * in the Store.servers list. 
     * @return QueueStatus of the Store
     */
    private QueueStatus query() {
        QueueStatus qs = new QueueStatus();
        servers.stream().forEach(x -> qs.add(x.queue()));
        return qs;
    }

    /**
     * Shows the average waiting time for Customer who are served by 
     * the store. Returns 0.0 if no Customers are served. 
     * @return double representing average waiting time. 
     */
    private double avgWaitTime() {
        return (this.served == 0) ? 0.0 : this.waitTime / this.served; 
    }

    /**
     * Tells the Store to simulate a number of Customers. 
     * Customer arrival timings are created randomly, starting from 0. 
     * Each subsequent Customer arrives later than the previous. 
     * 
     * <p>
     * The Store object does not store Customer objects individually, 
     * but instead stores each Customer as an ArrivalEvent containing 
     * said Customer. 
     * </p>
     * 
     * <p>
     * After all the Customers are added into the Events PriorityQueue,
     * the Store will begin processing all the Customers that are slated to 
     * arrive by calling this.run().
     * </p>
     * @param numCustomers number of Customers to simulate
     * @throws IllegalArgumentException when the input is <1
     * @see run
     */    
    public void customers(int numCustomers) throws IllegalArgumentException {
        if (numCustomers < 1) {
            throw new IllegalArgumentException("must have at least 1 customer");
        }
        double clock = 0.0;
        boolean first = true;
        while (events.size() < numCustomers) { //add all the customers as arrival events
            Customer c;
            if (first) { //setting the clock first 
                first = false; //if first, then no need to change clock
            } else {
                clock += rgen.genInterArrivalTime(); //add the random timing 
            }
            double isgreedy = rgen.genCustomerType();
            if (isgreedy < greedychance) { //customer is greedy
                c = new GreedyCustomer(clock);
            } else { //customer is normal
                c = new Customer(clock);
            }

            Event e = new ArrivalEvent(c); //create the arrival event
            this.events.add(e); //add arrival into event queue
        }
        this.run(); //once the arrivals are settled, procede with service 
    }
    
    /**
     * This method is called within this.customers() and cannot be called from the outside,
     * since it does not make sense for the Store to run when there are no Customers. 
     * 
     * <p>
     * This method prints all the events in order prescribed by the PriorityQueue of Events.
     * New events are dynamically added to the PQ as the method runs, according to the ascribed 
     * logic. When the method ends, there are no more events in the events PQ and the method will 
     * conclude with printing out the stats of the service [avg waiting time, number of Customers
     * Served, number of Customers who left]. 
     * </p>
     * 
     * <p>
     * The logic works by only updating the state of the involved Server only when required. 
     * e.g. Server.serve(Customer) is only at the occurence of a ServeEvent and not at the 
     * ArrivalEvent where it is decided that the Customer can be served immediately. 
     * </p>
     */
    private void run() {
        while (events.peek() != null) {
            Event e = events.poll();
            if (!(e instanceof ReturnEvent)) {
                System.out.println(e); //print the event out, except ReturnEvent
            }
            if (e instanceof ArrivalEvent) {
                Customer c = e.cust();
                //customer indicates which queue to join
                int join = c.assess(this.query()); 
                if (join == QueueStatus.QFULL) { //customer fails to join any queue
                    //create leave event at the same time
                    LeftEvent leave = new LeftEvent(c); 
                    //add into events
                    this.addEvent(leave);  
                } else {
                    //get the server from server list
                    Server s = this.servers.get(join); 
                    if (s.canServe()) {
                        //create a new serve event presently
                        ServeEvent served = new ServeEvent(c, s,e.getTime()); 
                        this.addEvent(served); //add into events
                    } else {
                        //create a new wait event presently
                        WaitEvent waits = new WaitEvent(c, s, e.getTime()); 
                        //add into events
                        this.addEvent(waits); 
                    }
                }
            } else if (e instanceof WaitEvent) {
                Server s = e.server();
                Customer c = e.cust();
                //add customer to server's queue
                s.addToQueue(c); 
            } else if (e instanceof ServeEvent) {
                Server s = e.server();
                Customer c = e.cust();
                s.serve(c); //canServe = false now 
                this.waitTime += (e.getTime() - c.arrTime()); // add the waiting time
                //create done event at random timing
                DoneEvent done = new DoneEvent(c, s, e.getTime() + rgen.genServiceTime()); 
                //add done event to events
                this.addEvent(done); 
            } else if (e instanceof DoneEvent) {
                Server s = e.server();
                double now = e.getTime();
                if (!(s instanceof RobotServer)) {  //only humans need a rest
                    //gen the number to compare against
                    double restnow = rgen.genRandomRest(); 
                    if (restnow < this.restchance) { // the server rests
                        now += rgen.genRestPeriod(); // now is the rest end time 
                        //create event where server returns from break
                        ReturnEvent breakEnd = new ReturnEvent(s, now); 
                        //add to events
                        this.addEvent(breakEnd); 
                    } else {
                        s.done();  //can only change canServe to true if no rest
                        if (s.hasNext()) {
                            //create next serve event, removing the first waiting customer from q
                            ServeEvent next = new ServeEvent(s.removeFromQueue(), s, now); 
                            this.addEvent(next);
                        }
                    }
                } else { //for robots, just need to check if theres anyone in line
                    s.done(); //true, because it finishes service and doesnt rest 
                    if (s.hasNext()) {
                        //create next serve event, removing the first waiting customer from q
                        ServeEvent next = new ServeEvent(s.removeFromQueue(), s, now); 
                        this.addEvent(next);
                    }
                }
            } else if (e instanceof ReturnEvent) {
                Server s = e.server();
                if (s.hasNext()) {
                    //remove next cust from queue and serve
                    ServeEvent served = new ServeEvent(s.removeFromQueue(), s, e.getTime()); 
                    this.addEvent(served);
                }
                s.done();
            }
        }
        //[avg waiting time, number of Customers Served, number of Customers who left]
        System.out.println("[" + String.format("%.3f",this.avgWaitTime()) + " " 
            + this.served + " " + this.left + "]"); 
    }

}