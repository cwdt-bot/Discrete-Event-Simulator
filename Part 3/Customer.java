package cs2030.simulator;

import java.util.List;

/**
 * Simulates a customer, who has an an arrival time A static variable keeps
 * track of the number of customers created so far, starting at 1
 */
public class Customer implements Comparable<Customer> {
    private static int globalCustomers = 1;
    private final int id;
    private final double arrTime;

    /**
     * Creates a customer with an int as id and an arrival time. 
     * The id is generated through a count of customers created globally
     * @param arrTime
     */
    public Customer(double arrTime) {
        this.id = Customer.globalCustomers;
        this.arrTime = arrTime;
        Customer.globalCustomers++;
    }

    /**
     * returns the arrival time of the customer
     * @return arrival time of customer as double
     */
    public double arrTime() {
        return this.arrTime;
    }   

    /**
     * returns id of the customer
     * @return customer id as int
     */
    public int id() {
        return this.id;
    }

    public int compareTo(Customer o) {
        if (this.arrTime() < o.arrTime()) {
            return -1;
        } else {
            if (this.arrTime() == o.arrTime()) {
                return this.id() - o.id();
            }
            return 1;
        }
    }

    @Override
    public String toString() {
        return this.id() + "";
    }

    /**
     * This method takes in a list of int representing the number of people in queue for each respective queue. 
     * The index of the int is the queue it represents. 
     * 
     * For a non-greedy customer, it will look at the queues and join the first one that it can.
     * This means the first queue with value != -1. 
     * 
     * If all queues are -1, this method returns -1,
     * meaning that the customer cannot join any queue. 
     * @param list
     * @return index of queue to join as int, -1 if cannot join any queue
     */
    public int assess(List<Integer> list) {
        if (list.contains(-2)) return list.indexOf(-2);
        for (int i : list) {
            if (i >= 0 ) return list.indexOf(i);
        }
        return -1; 
    }
}