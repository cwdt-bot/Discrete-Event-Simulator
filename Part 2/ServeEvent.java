/** 
 * Models an event where a customer is served by a waiter.
 * Customer/Waiter match.
 * 
 */

class ServeEvent extends Event {
    private static final int eventWeight = 1;

    public ServeEvent(Customer c, Waiter w, double time) {
        super(c,w,time,ServeEvent.eventWeight);
    }

    public int weight() {
        return ServeEvent.eventWeight;
    }

    @Override
    public String toString() {
        return String.format("%.3f", this.getTime()) + " " + c.id() + " served by " + w.id();
    }
}
