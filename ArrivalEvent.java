
/** 
 * Models an arrival of a customer.
 * No Customer/Waiter match.
 */
class ArrivalEvent extends Event {
    private static final Waiter numWaiter = null;
    private static final int eventWeight = 0;

    public ArrivalEvent(Customer c, double time) {
        super(c,ArrivalEvent.numWaiter,time,ArrivalEvent.eventWeight);
    }

    public int weight() {
        return ArrivalEvent.eventWeight;
    }

    @Override
    public String toString() {
        return "" + this.c;
    }
}