package cs2030.simulator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/**
 * Models an object that contains the QueueStatus of all the Servers in a 
 * Store. 
 */

public class QueueStatus {
    /**
     * QueueStatus.QFULL is a static int that indicates the queue is 
     * full. 
     */
    public static int QFULL = Integer.MAX_VALUE;
    /**
     * QueueStatus is a static int that indicates that Server is idle.
     */
    public static int IDLE = -1;
    private final List<Integer> list;
    private boolean isAllFull = true; 

    /**
     * Constructs an empty QueueStatus object that has no available 
     * Servers i.e. isAllFull == true. 
     */
    public QueueStatus() {
        this.list = new ArrayList<>();
    }

    /**
     * Adds an int indicating the status of a Server. 
     * The order of the elements being added in is significant
     * so the value for the first Server must be added first etc. 
     * @param x int representing status of Server. 
     */
    public void add(int x) {
        this.list.add(x);
        if (x != QueueStatus.QFULL) {
            this.isAllFull = false;
        } 
    }

    /**
     * Returns the index of the first idle Server, if not, 
     * the Server with the Shortest queue. If none of the Servers
     * can serve the customer, returns QFULL. 
     * @return index of the Server with the shortest wait, or QFULL
     */
    public int getShortest() {
        if (!this.allFull()) {
            return list.indexOf(Collections.min(this.list));
        } else {
            return QueueStatus.QFULL;
        }
    }

    /**
     * Returns the first idle Server, if not, the first available 
     * Server, if not, QFULL. 
     * @return index of first available Server, or QFULL
     */
    public int getFirst() {
        if (!this.allFull()) {
            if (this.list.contains(QueueStatus.IDLE)) {
                return this.list.indexOf(QueueStatus.IDLE);
            } else {
                return this.list.indexOf(this.list.stream()
                    .filter(x -> x != QueueStatus.QFULL).findFirst().get());
            }
        } else {
            return QueueStatus.QFULL;
        }
    }

    /**
     * Internal checker method to see if all 
     * Servers are unavailable. 
     * @return true if all Servere are unavailable, else false. 
     */
    private boolean allFull() {
        return this.isAllFull;
    }
}
