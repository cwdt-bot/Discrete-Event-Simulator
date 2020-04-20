package cs2030.simulator;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Simulates a server with an int as its id
 * The number of waiters created is tracked via a static variable starting at 1. 
 * 
 * Each waiter has the ending of each service/break tracked in List<Double> 
 * and a priorityqueue of customers. 
 */
public class Server {
    private final int id;
    private static int globalServers = 1;
    private boolean canServe = true;
    protected final int qmax;
    protected PriorityQueue<Customer> waiting = new PriorityQueue<>();

    public Server(int qmax) {
        this.id = Server.globalServers;
        this.qmax = qmax;
        Server.globalServers++;
    }
    
    public boolean canServe() {
        return this.canServe;
    }

    public void serve(Customer c) {
        this.canServe = false;
    }

    public void done() {
        this.canServe = true;
    }

    public int id() {
        return this.id;
    }

    /**
     * returns the number of people waiting in their queue.
     * 
     * If the queue is full, returns -1. If the server is idle, return -2
     * @return int describing the status of server
     */
    public int queue() {
        int curr = this.waiting.size();
        if (curr >= qmax) {
            return -1;
        } else if (this.canServe()) {
            return -2;
        } else {
            return curr;
        }
    }

    public void addToQueue(Customer c) {
        this.waiting.add(c);
    }

    public Customer removeFromQueue() {
        return this.waiting.poll(); //since it is a PQ, we can just remove the head to get FIFO structure
    }

    public boolean hasNext() {
        return !this.waiting.isEmpty();

    }

    public Customer peek() {
        return this.waiting.peek();
    }

    @Override
    public String toString() {
        return "server " + this.id();
    }
}