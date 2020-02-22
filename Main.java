import java.util.Scanner;
import java.util.PriorityQueue;
import java.util.ArrayList;

class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int count = 1;
        ArrayList<Customer> inc = new ArrayList<>();
        while (sc.hasNext()) {
            inc.add(new Customer(count, sc.nextDouble()));
            count++ ;
        }
        sc.close();
        Store store = new Store("Noma");
        for (Customer c : inc) {
            double time = c.arrTime();
            Waiter w = store.receives(time);
            int toWait = w.inWait(time);
            boolean waits = c.assess(toWait);
            if (!waits) {
                Event e = new Event(c,time);
                store.addEvent(e, e.state());           //add leaving event
            }
            else if (toWait == 0) {
                Event e = new Event(c,w,time,false);    //add served event
                w.serve(c);
                store.addEvent(e, e.state());
            } else {
                double sTime = w.lastTime();
                Event e = new Event(c,w,time,true);      //add waiting event
                Event f = new Event(c,w,sTime,false);    //add served event
                w.serve(c);
                store.addEvent(e, e.state());
                store.addEvent(f, f.state());
            }
        }
        PriorityQueue<Event> schedule = store.showSchedule();
        while (schedule.peek() != null) {
            System.out.println(schedule.poll());
        }
        System.out.println("Number Served: " + store.numServed());
        System.out.println("Number Waited: " + store.numWaited());
        System.out.println("Number Left: " + store.numLeft());


    }
}