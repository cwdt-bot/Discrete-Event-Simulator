package cs2030.simulator;

import java.util.PriorityQueue;

/**
 * This is a subclass of Server where all instances share the same queue. 
 * The queue is initialised as a static variable for the class. 
 * 
 * <p>
 * RobotServer shares the same count tracker as the superclass Server. 
 * </p>
 */
public class RobotServer extends Server {
    private static final PriorityQueue<Customer> queue = new PriorityQueue<>();

    /**
     * Constructs a RobotServer with a max number of customers allowed in the shared queue. 
     * Note that the qmax is shared among all instances, 
     * so different instances must have the same qmax. 
     * @param qmax max number of Customer in the shared queue. 
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