/**
 * Models a customer which has its own logic as part of the Customer.assess() method.
 * A Customer is initialised with a unique integer id for each customer and double value indicating arrival time.
 * A Customer has default patience of 1 (i.e will wait for one other customer), but can be created with other values.
 */

class Customer {
    private final int id;
    private final double arrTime;
    /** number of other customers this customer is willing to wait for */
    private final int patience;

    /**
     * Constructs a Customer object with an ID and time of arrival that is willing to wait for 1 other customer
     * 
     * @param id integer indicating the identity of Customer object
     * @param arrTime double indicating the time of arrival of Customer
     */
    Customer(int id, double arrTime) {
        this.id = id;
        this.arrTime = arrTime;
        this.patience = 1;
    }
    /**
     * Constructs a Customer object with an ID, time of arrival and given patience
     * @param id integer indicating the identity of Customer object
     * @param arrTime double indicating the time of arrival of Customer
     * @param p integer indicating how many other customers the created customer will wait for
     */
    Customer(int id, double arrTime, int p) {
        this.id = id;
        this.arrTime = arrTime;
        this.patience = p;
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
     * Decides if customer will wait for (toWait) number of customers
     * @param toWait the number of other customers ahead in line
     * @return true if Customer is willing to wait, else false
     */
    boolean assess(int toWait) {
        if (toWait <= patience) {
            return true;
        } else {
            return false;
        }
    }


}
    

