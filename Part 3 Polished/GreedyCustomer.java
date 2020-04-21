package cs2030.simulator;

import java.util.List;

/**
 * Extends from Customer, and is functionally the same as Customer, except for the assess()
 * method. GreedyCustomers are created with ID drawing from the same pool as Customers.
 * 
 * <p>
 * Simulates a greedy customer, which joins the queue with lowest number of people
 * in wait, as compared to a normal customer, which joins the first available queue.
 * </p> 
 */
public class GreedyCustomer extends Customer {

    /**
     * Creates a GreedyCustomer with an int as id and an arrival time. 
     * The id is generated through a count of all Customers created globally.
     * @param arrTime double indicating the arrival time of the customer.
     */
    public GreedyCustomer(double arrTime) {
        super(arrTime);
    }

    /**
     * Takes in a QueueStatus object gets the earliest available Server 
     * from the QueueStatus object. 
     * @param qs QueueStatus to select from. 
     * @return int indicating the queue the GreedyCustomer wants to join
     */
    @Override
    public int assess(QueueStatus qs) {
        return qs.getShortest();
    }

    @Override
    public String toString() {
        return this.id() + "(greedy)";
    }

}