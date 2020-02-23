/**
 * Models Customer Logic for deciding whether to wait or not. boolean pref true means
 * that the logic will prioritise time over people limit
 */
class CustomerLogic {
    private final int personLimit;
    private final double timeLimit;
    private final boolean pref; 

    /**
     * Constructs a logic with no patience
     */
    CustomerLogic() {
        this.personLimit = 0;
        this.timeLimit = 0;
        this.pref = true;
    }
    /**
     * Constructs a logic which only considers the number of people to wait for
     * @param l max number of people logic is willing to wait
     */
    CustomerLogic(int l) {
        this.personLimit = l;
        this.timeLimit = 0;
        this.pref = true;
    }
    /**
     * Constructs a logic which only considers the time of the wait
     * @param l max time the logic is willing to wait
     */
    CustomerLogic(double l) {
        this.personLimit = 0;
        this.timeLimit = l;
    }
    /**
     * Constructs a logic that considers both people in queue and time to wait
     * @param x max number of people to wait
     * @param y max time to wait 
     */
    CustomerLogic(int x, double y) {
        this.personLimit  = x;
        this.timeLimit = y;
    }

    boolean assess(int x, double y) {

    }
}