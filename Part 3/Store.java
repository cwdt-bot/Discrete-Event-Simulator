package cs2030.simulator;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

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


    public Store(int seed, double lambda, double mu, double rho, double restchance, double greedychance) {
        this.rgen = new RandomGenerator(seed, lambda, mu, rho);
        this.restchance = restchance;
        this.greedychance = greedychance;
    }

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

    private List<Integer> query() {
        List<Integer> result = new ArrayList<>();
        for (Server s : servers) {
            result.add(s.queue());
        }
        return result;
    }

    private double avgWaitTime() {
        return (this.served ==0) ? 0.0 : this.waitTime/this.served;
    }

    public void customers(int numCustomers) throws IllegalArgumentException {
        if (numCustomers < 1) throw new IllegalArgumentException("must have at least 1 customer");
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
            } else {
                c = new Customer(clock);
            }

            Event e = new ArrivalEvent(c); //create the arrival event
            this.events.add(e); //add arrival into event queue
        }
        this.run(); //once the arrivals are settled, procede with service 
    }
    
    /**
     * Once all the customers have been created and put into arrival events, the service can 
     * start. The console will just print 
     */
    public void run() {
        while (events.peek() != null) {
            Event e = events.poll();
            if (!(e instanceof ReturnEvent)) System.out.println(e); //print the event out, except returnevent
            if (e instanceof ArrivalEvent) {
                Customer c = e.cust();
                int join = c.assess(this.query()); //customer indicates which queue to join
                if (join == -1) { //customer fails to join any queue
                    LeftEvent leave = new LeftEvent(c); //create leave event at the same time
                    this.addEvent(leave); //add into events 
                } else {
                    Server s = this.servers.get(join); //get the corresponding server from server list
                    if (s.canServe()) {
                        ServeEvent served = new ServeEvent(c, s,e.getTime()); //create a new serve event presently
                        this.addEvent(served); //add into events
                    } else {
                        WaitEvent waits = new WaitEvent(c, s, e.getTime()); //create a new wait event presently
                        this.addEvent(waits); //add into events
                    }
                }
            } else if (e instanceof WaitEvent) {
                Server s = e.server();
                Customer c = e.cust();
                s.addToQueue(c); //add customer to server's queue
            } else if (e instanceof ServeEvent) {
                Server s = e.server();
                Customer c = e.cust();
                s.serve(c); //canServe = false now 
                this.waitTime += (e.getTime() - c.arrTime()); // add the waiting time
                DoneEvent done = new DoneEvent(c, s, e.getTime() + rgen.genServiceTime()); //create done event at random timing
                this.addEvent(done); //add done event to events
            } else if (e instanceof DoneEvent) {
                Server s = e.server();
                double now = e.getTime();
                if (!(s instanceof RobotServer)) {  //only humans need a rest
                    double restnow = rgen.genRandomRest(); //gen the number to compare against
                    if (restnow < this.restchance) { // the server rests
                        now += rgen.genRestPeriod(); // now is the rest end time 
                        ReturnEvent breakEnd = new ReturnEvent(s, now); //create event where server returns from break
                        this.addEvent(breakEnd); //add to events
                    } else {
                        s.done();  //can only change canServe to true if no rest
                        if (s.hasNext()) {
                            ServeEvent next = new ServeEvent(s.removeFromQueue(), s, now); //create the next serve event, removing the first waiting customer from q
                            this.addEvent(next);
                        }
                    }
                } else { //for robots, just need to check if theres anyone in line
                    s.done();
                    if (s.hasNext()) {
                        ServeEvent next = new ServeEvent(s.removeFromQueue(), s, now); //create the next serve event, removing the first waiting customer from q
                        this.addEvent(next);
                    }
                }
            } else if (e instanceof ReturnEvent) {
                Server s = e.server();
                if (s.hasNext()) {
                    ServeEvent served = new ServeEvent(s.removeFromQueue(), s, e.getTime()); //remove next cust from queue and serve
                    this.addEvent(served);
                }
                s.done();
            }
        }
        System.out.println("[" + String.format("%.3f",this.avgWaitTime()) + " " 
            + this.served + " " + this.left + "]"); 
    }

}