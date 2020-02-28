/**
 * Models the event when a customer is finished with service and leaves.
 * Customer/Waiter match.
 */

class DoneEvent extends Event {
    private static final int eventWeight = 2;

    DoneEvent(Customer c, Waiter w, double time) {
        super(c,w,time,DoneEvent.eventWeight);
    }

    int weight() {
        return DoneEvent.eventWeight;
    }

    public String toString() {
        return String.format("%.3f", this.getTime()) + " " + c.id() + " done";
    }
}