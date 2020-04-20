package cs2030.simulator;

/** 
 * Models an arrival of a customer.
 * No Customer/Waiter match.
 */
public class ArrivalEvent extends Event {
    private static final Server numServer = null;
    private static final int eventWeight = 0;

    public ArrivalEvent(Customer c) {
        super(c,ArrivalEvent.numServer,c.arrTime(),ArrivalEvent.eventWeight);
    }

    public int weight() {
        return ArrivalEvent.eventWeight;
    }

    @Override
    public String toString() {
        return String.format("%.3f",c.arrTime()) + " " + c + " arrives";
    }
}