import java.util.Scanner;
import java.util.ArrayList;
import java.util.PriorityQueue;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int count = 1;
        ArrayList<Customer> inc = new ArrayList<>();
        while (sc.hasNext()) {
            inc.add(new Customer(count,sc.nextDouble()));
            count++;
        }
        sc.close();
        Store store = new Store("Noma");
        store.employ(new Waiter(1, 1.0));
        for (Customer c : inc) {
            double time = c.arrTime();
            Waiter w = store.receives(time);
            int toWait = w.inWait(time);
            boolean waits = c.assess(toWait);
            Event arrive = new Event(c, time, "arrives");
            store.addEvent(arrive);
            if (!waits) { //if customer chooses not to wait
                Event e = new Event(c, time, "left");
                store.addEvent(e); //add leave event
            } else { //customer chooses to stay
                Event f = new Event(c, w, time, "served");
                store.addEvent(f);
                w.serve(c);
            }
        }
        PriorityQueue<Event> schedule = store.showSchedule();
        while (schedule.peek() != null) {
            System.out.println(schedule.poll());
        }
        System.out.println("Number of customers: " + inc.size());
    }
}
