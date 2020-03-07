/**
 * Models the event when a customer leaves after deciding not to wait.
 * No Customer/Waiter match.
 */

class LeftEvent extends Event {
    private static final Waiter numWaiter = null;
    private static final int eventWeight = 4;

    public LeftEvent(Customer c, double time) {
        super(c,LeftEvent.numWaiter,time,LeftEvent.eventWeight);
    }

    public int weight() {
        return LeftEvent.eventWeight;
    }

    @Override
    public String toString() {
        return String.format("%.3f", this.getTime()) + " " + c.id() + " leaves";
    }
}