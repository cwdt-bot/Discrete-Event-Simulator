/**
 * Models the event when a customer leaves after deciding not to wait.
 * No Customer/Waiter match.
 */

class LeftEvent extends Event {
    private static final Waiter numWater = null;
    private static final int eventWeight = 4;

    LeftEvent(Customer c, double time) {
        super(c,LeftEvent.numWater,time,LeftEvent.eventWeight);
    }

    int weight() {
        return LeftEvent.eventWeight;
    }

    public String toString() {
        return String.format("%.3f", this.getTime()) + " " + c.id() + " leaves";
    }
}