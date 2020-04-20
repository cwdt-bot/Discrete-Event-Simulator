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
     * A greedy customer will join the first queue that has an idle server. If not, it will join 
     * the queue with the least number of waiting people. 
     * </p>
     * 
     * <p>
     * If all queues are full, this method returns Customer.JOIN_FAIL,
     * meaning that the customer cannot join any queue. 
     * </p>
     * @param list list of ints representing the state of the each queue. 
     * @return index of queue to join as int, Customer.JOIN_FAIL if cannot join any queue.
     */
    @Override
    public int assess(List<Integer> list) {
        if (list.contains(Customer.INDICATE_IDLE)) { 
            return list.indexOf(Customer.INDICATE_IDLE);
        }
        int currMin = Integer.MAX_VALUE;
        for (int i : list) {
            if (i != Customer.JOIN_FAIL && i < currMin) {
                currMin = i;
            }
        }
        if (currMin == Integer.MAX_VALUE) { //since this means the whole list is Customer.JOIN_FAIL
            return Customer.JOIN_FAIL; //return JOIN_FAIL value to show that customer leaves
        } else {
            return list.indexOf(currMin); //get the number of the queue that they want
        }
    }

    @Override
    public String toString() {
        return this.id() + "(greedy)";
    }

}