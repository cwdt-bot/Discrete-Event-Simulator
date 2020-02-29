/**
 * Models an event that customer waits for a waiter to serve itself.
 * Customer/Waiter event.
 */

class WaitEvent extends Event {
    private static final int eventWeight = 3;

    WaitEvent(Customer c, Waiter w, double time) {
        super(c,w,time,WaitEvent.eventWeight);
    }

    int weight() {
        return WaitEvent.eventWeight;
    }

    @Override
    public String toString() {
        return String.format("%.3f", this.getTime()) + " " + c.id() + " waits";
    }
}