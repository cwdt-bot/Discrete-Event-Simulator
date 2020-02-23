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
            count++;
        }
        sc.close();
        Store store = new Store("Noma");
        store.employ(new Waiter(1,1));
        for (Customer c : inc) {
            double time = c.arrTime();
            Waiter w = store.receives(time);
            int toWait = w.inWait(time);
            boolean waits = c.assess(toWait);
            Event arrive = new Event(c, time, "arrives"); 
            store.addEvent(arrive);                         //add arrives event
            if (!waits) {                                   //doesnt want to wait
                Event e = new Event(c,time,"left");
                store.addEvent(e);                          //add leaves event             
            } else if (toWait == 0) {                         // there is no wait
                Event e = new Event(c,w,time,"served");     //add served event
                store.addToWaitTime(w.serve(c));
                store.addEvent(e);           
                Event f = new Event(c,w,w.finishTime(time),"done");     //add done event
                store.addEvent(f);
            } else {                                         //willing to wait
                double nextService = w.lastTime();
                Event e = new Event(c,w,time,"waits");       //add waiting event
                Event f = new Event(c,w,nextService,"served");     //add served event
                store.addToWaitTime(w.serve(c));
                store.addEvent(e);
                store.addEvent(f);
                Event g = new Event(c,w,w.finishTime(nextService),"done");        //add done event
                store.addEvent(g);
            }
        }
        PriorityQueue<Event> schedule = store.showSchedule();
        while (schedule.peek() != null) {
            System.out.println(schedule.poll());
        }
        System.out.println("[" + String.format("%.3f",store.avgWaitTime()) + " " 
            + store.numServed() + " " + store.numLeft() + "]");


    }
}