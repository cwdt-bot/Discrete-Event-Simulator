import java.util.ArrayList;
/**
 * Models Waiters that serve Customers
 * <p>
 * Due to the implementation of the class, Waiters can only work chronogically;
 * subsequent calls to Waiter.serve() must strictly be later or the same chronologically. 
 */

class Waiter {
    private final int id;
    /** time is takes for a waiter to complete a service */
    private final double serviceTime;
    /** tracks the ending time of all services that a waiter performs chronologically. 
    * Essentially the waiter's schedule
    */
    private ArrayList<Double> q = new ArrayList<>(); 
    Waiter(int id, double serviceTime) {
        this.id = id;
        this.serviceTime = serviceTime;
    }

    int id() {
        return this.id;
    }

    double finishTime(double time) {
        return time + this.serviceTime;
    }

    /**
     * Indicates how many customers are in wait for this particular waiter at a
     * specified point in time
     * @param time specified point in time
     * @return  integer indicating how many customers are in wait
     */
    int inWait(double time) {
        int r = this.q.size();
        if (r == 0) {
            return r;
        }
        for (double service : q) {
            if (service <= time) {
                r--;
            } else {
                break;
            }       
        }
        return r;
    }
    
    /**
     * Shows the ending time of the last service the waiter performs.
     * If the waiter has done nothing so far, 0 is returned.
     * @return time of latest service, or 0 if no service has been done
     */
    double lastTime() {
        if (this.q.size() != 0) {
            return this.q.get(this.q.size() - 1);
        } else {
            return 0;
        }
    }

    /**
     * Serves a customer, indicates how long a customer will have to wait
     * <p>
     * Waiter will start serving the customer as soon as it is able to.
     * The ending time of this specific service is added to the waiter's schedule.
     * <p>
     * Calls to this method must be made chronologically. 
     * @param c Customer to be served
     * @return  double indicating how long the cuustomer needs to wait
     */
    double serve(Customer c) {
        double lag = 0;
        if (this.lastTime() != 0) {
            if (this.lastTime() > c.arrTime()) {
                lag = this.lastTime() - c.arrTime();
            }
        }
        double newService = this.finishTime(Math.max(this.lastTime(), c.arrTime()));
        this.q.add(newService);
        return lag;
    }

    public String toString() {
        return q.toString();
    }
}



