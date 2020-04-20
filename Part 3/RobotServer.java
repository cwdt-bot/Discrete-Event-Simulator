package cs2030.simulator;

import java.util.PriorityQueue;
/**
 * This is a subclass of Server where all instances share the same queue. 
 * The queue is initialised as a static variable for the class. 
 */
public class RobotServer extends Server {
    private static final PriorityQueue<Customer> queue = new PriorityQueue<>();

    /**
     * Constructs a RobotServer with a int indicating the max number of customers allowed in a queue. 
     * Note that the qmax is shared among all instances, so different instances must have the same qmax. 
     * @param qmax
     */
    public RobotServer(int qmax) {
        super(qmax);
        this.waiting = RobotServer.queue;
    }

    @Override
    public String toString() {
        return "self-check " + this.id();
    }

}