package cs2030.simulator;

import java.util.List;

/**
 * Simulates a customer, who has an an arrival time A static variable keeps
 * track of the number of customers created so far, starting at 1.
 */
public class Customer implements Comparable<Customer> {
    /**
     * the globalCustomers static int tracks the number of instances of Customers globally, 
     * ensuring that the ID for each Customer instance has a unique ID. 
     */
    private static int globalCustomers = 1;
    private final int id;
    private final double arrTime;
    /**
     * the JOIN_FAIL static int is used to indicate to a customer that a queue is full.
     */
    public static final int JOIN_FAIL = -1;
    /**
     * the INDICATE_IDLE static int is used to indicate to a customer that a server is idle.
     */
    public static final int INDICATE_IDLE = -2;
    /**
     * This is used to indicate a null customer. The null customer has 
     * arrival time and id of 0.
     */
    public static final Customer NULL_CUSTOMER = new Customer();

    /**
     * Creates a customer with an int as id and an arrival time. 
     * The id is generated through a count of all Customers created globally.
     * @param arrTime double indicating the arrival time of the customer.
     */
    public Customer(double arrTime) {
        this.id = Customer.globalCustomers;
        this.arrTime = arrTime;
        Customer.globalCustomers++;
    }

    /**
     * This is used to construct a null customer. 
     */
    private Customer() {
        this.id = 0;
        this.arrTime = 0.0;
    }

    /**
     * returns the arrival time of the customer.
     * @return arrival time of customer as double.
     */
    public double arrTime() {
        return this.arrTime;
    }   

    /**
     * returns id of the customer.
     * @return customer id as int.
     */
    public int id() {
        return this.id;
    }
    
    /**
     * implemented as part of the Comparable interface. 
     * The priority is checking arrival time then customer ID.
     * @return a negative int, 0, positive int indicating that the customer is smaller, equals to, 
     *      greater than the given customer. 
     */
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
     * This method takes in a list of ints representing the number of people in queue 
     * for each respective queue. The index of the int is the queue/server it represents. 
     * 
     * <p>
     * There are special values Customer.INDICATE_IDLE (to indicate
     * that the server for that queue is idle) and Customer.JOIN_FAIL 
     * (to indicate that the queue is full).
     * </p> 
     * 
     * <p>
     * A normal customer will join the first queue that has an idle server. If not, it will join 
     * the first available queue. 
     * </p>
     * 
     * <p>
     * If all queues are full, this method returns Customer.JOIN_FAIL,
     * meaning that the customer cannot join any queue. 
     * </p>
     * @param list list of ints representing the state of the each queue. 
     * @return index of queue to join as int, Customer.JOIN_FAIL if cannot join any queue.
     */
    public int assess(List<Integer> list) {
        if (list.contains(Customer.INDICATE_IDLE)) {
            return list.indexOf(Customer.INDICATE_IDLE);
        }
        for (int i : list) {
            if (i != Customer.JOIN_FAIL) {
                return list.indexOf(i);
            }
        }
        return Customer.JOIN_FAIL; 
    }
}