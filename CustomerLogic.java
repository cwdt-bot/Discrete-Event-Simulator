/**
 * Models Customer Logic for deciding whether to wait or not. boolean pref true means
 * that the logic will prioritise time over people limit
 */
class CustomerLogic {
    private final int personLimit;
    private final double timeLimit;
    private static final int nullPerson = 0;
    private static final double nullTime = 0.0;
    private static final boolean defaultTimePref = false;
    /**
     * timepref = true means that a customer would place importance on time rather than
     * number of people ahead
     */
    private final boolean timePref; 

    /**
     * Constructs a logic with no patience
     */
    CustomerLogic() {
        this.personLimit = CustomerLogic.nullPerson;
        this.timeLimit = CustomerLogic.nullTime;
        this.timePref = CustomerLogic.defaultTimePref;
    }
    /**
     * Constructs a logic which only considers the number of people to wait for
     * @param l max number of people logic is willing to wait
     */
    CustomerLogic(int l) {
        this.personLimit = l;
        this.timeLimit = CustomerLogic.nullTime;
        this.timePref = CustomerLogic.defaultTimePref;
        
    }
    /**
     * Constructs a logic which only considers the time of the wait
     * @param l max time the logic is willing to wait
     */
    CustomerLogic(double l) {
        this.personLimit = CustomerLogic.nullPerson;
        this.timeLimit = l;
        this.timePref = CustomerLogic.defaultTimePref;
        
    }
    /**
     * Constructs a logic that considers both people in queue and time to wait
     * @param x max number of people to wait
     * @param y max time to wait 
     */
    CustomerLogic(int x, double y) {
        this.personLimit  = x;
        this.timeLimit = y;
        this.timePref = CustomerLogic.defaultTimePref;
    }

    /**
     * Assesses the wait time and returns a boolean. 
     * @param x number of people ahead
     * @param y amount of time to wait
     * @return true if customer will wait, else false
     */
    boolean assess(int x, double y) {
        if (this.timePref && y <= this.timeLimit) {
            return true;
        } else if (!this.timePref && x <= this.personLimit) {
            return true;
        } else {
            return false;
        }

    }
}