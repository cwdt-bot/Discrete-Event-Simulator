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
     * Takes in a QueueStatus object gets the first available Server 
     * from the QueueStatus object. 
     * @param qs QueueStatus to select from. 
     * @return int indicating the queue the Customer wants to join
     */
    public int assess(QueueStatus qs) {
        return qs.getFirst();
    }
}