/**
 * Models a customer which has its own logic as part of the Customer.assess() method.
 * A Customer is initialised with a unique integer id for each customer 
 * and double value indicating arrival time.
 * A Customer needs its own CustomerLogic object to assess wait times.
 */

class Customer {
    private static final double noTimePref = 0;
    private static final int noCustPref = 0;
    private static int globalCustomers = 0;
    private final int id;
    private final double arrTime;
    private final CustomerLogic logic;


    /**
     * Constructs a Customer object with an ID and time of arrival that is 
     * willing to wait for 1 other customer.
     * 
     * @param id integer indicating the identity of Customer object
     * @param arrTime double indicating the time of arrival of Customer
     */
    Customer(double arrTime, CustomerLogic l) {
        this.id = Customer.globalCustomers + 1;
        this.arrTime = arrTime;
        this.logic = l;
        Customer.globalCustomers++;

    }

    @Override
    public String toString() {
        return String.format("%.3f",this.arrTime()) + " " + this.id() + " arrives";
    }


    double arrTime() {
        return this.arrTime;
    }

    int id() {
        return this.id;
    }
    
    /**
     * Decides if customer will wait for (toWait) number of customers.
     * @param toWait the number of other customers ahead in line
     * @return true if Customer is willing to wait, else false
     */
    boolean assess(int numPeople) {
        return this.logic.assess(numPeople, Customer.noTimePref);
    }

    boolean assess(double waitTime) {
        return this.logic.assess(Customer.noCustPref, waitTime);
    }


}
    

