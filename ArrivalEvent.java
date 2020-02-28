
/** Models an arrival of a customer.
 * No Customer/Waiter match.
 */
class ArrivalEvent extends Event {
    private static final Waiter numWaiter = null;
    private static final int eventWeight = 0;

    ArrivalEvent(Customer c, double time) {
        super(c,ArrivalEvent.numWaiter,time,ArrivalEvent.eventWeight);
    }

    int weight() {
        return ArrivalEvent.eventWeight;
    }

    public String toString() {
        return "" + this.c;
    }
}