package cs2030.simulator;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Models a Server with an int as its id. 
 * The number of Server instances created globally is tracked via a static variable starting at 1. 
 * 
 * <p>
 * Each Server has a PriorityQueue of Customer indicating the queue of Customers waiting for 
 * the Server.
 * </p>
 */
public class Server {
    private final int id;
    /**
     * the globalServers static int tracks the number of instances of Servers globally, 
     * ensuring that the ID for each Server instance has a unique ID. 
     */
    private static int globalServers = 1;
    /**
     * A new Server is always created being able to serve a Customer straight away.
     * True indicates that the Server can serve a Customer. 
     */
    private boolean canServe = true;
    protected final int qmax;
    protected PriorityQueue<Customer> waiting = new PriorityQueue<>();
    /**
     * Used to denote a null Server. It does not have an ID, or a qmax
     */
    public static Server NULL_SERVER = new Server();

    /**
     * Creates a Server object with a specified maximum number of Customers that 
     * can wait for the Server. 
     * @param qmax Maximum queue length for the Server
     */
    public Server(int qmax) {
        this.id = Server.globalServers;
        this.qmax = qmax;
        Server.globalServers++;
    }

    /**
     * Used to construct a null Server. 
     */
    private Server() {
        this.id = 0;
        this.qmax = 0;
    }
    
    /**
     * Indicates if the Server can serve at time of calling the method. 
     * @return true if the Server is idle, else false
     */
    public boolean canServe() {
        return this.canServe;
    }

    /**
     * sets the canServe boolean to false, indicating that 
     * the Server is busy serving the given Customer. 
     * 
     * <p>
     * However since there is no need to track the customer, 
     * the Server object does not store it. The method requires 
     * a Customer as input as a reminder that server method should NOT be called
     * without a corresponding Customer as it would not make sense. 
     * </p>
     */
    public void serve(Customer c) {
        this.canServe = false;
    }

    /**
     * indicates that Server is done with service and now can serve another Customer.
     */
    public void done() {
        this.canServe = true;
    }

    /**
     * Gets the id of the Server. 
     * @return id of the Server object
     */
    public int id() {
        return this.id;
    }

    /**
     * Indicates the number of Customers in wait for the Server. 
     * 
     * <p>
     * If the queue is full, returns Server.QUEUE_FULL. 
     * If the server is idle, return Server.IDLE.
     * </p>
     * @return int describing the status of server
     */
    public int queue() {
        int curr = this.waiting.size();
        if (curr >= qmax) {
            return QueueStatus.QFULL;
        } else if (this.canServe()) {
            return QueueStatus.IDLE;
        } else {
            return curr;
        }
    }

    /**
     * Adds a Customer into the Server's queue. 
     * @param c Customer joining the queue for the Server
     */
    public void addToQueue(Customer c) {
        this.waiting.add(c);
    }

    /**
     * Removes the Customer that joined the queue the earliest. The queue has a 
     * First-in-First-Out structure. 
     * @return Customer at the front of the queue
     */
    public Customer removeFromQueue() {
        return this.waiting.poll(); //poll the PQ to get FIFO structure
    }

    /**
     * Checks if the Server has Customers in its queue waiting for it.
     * @return true if Server has Customers waiting for it, else false
     */
    public boolean hasNext() {
        return !this.waiting.isEmpty();

    }

    @Override
    public String toString() {
        return "server " + this.id();
    }
}