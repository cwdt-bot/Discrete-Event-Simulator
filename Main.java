import java.util.Scanner;
import java.util.PriorityQueue;
import java.util.ArrayList;

class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int count = 1;
        ArrayList<Customer> inc = new ArrayList<>();
        CustomerLogic baseLogic = new CustomerLogic(1);
        while (sc.hasNext()) {
            inc.add(new Customer(count, sc.nextDouble(), baseLogic));
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
            ArrivalEvent arrive = new ArrivalEvent(c, time); 
            store.addEvent(arrive);                         //add arrives event
            if (!waits) {                                   //doesnt want to wait
                LeftEvent e = new LeftEvent(c,time);
                store.addEvent(e);                          //add leaves event             
            } else if (toWait == 0) {                         // there is no wait
                ServeEvent e = new ServeEvent(c,w,time);     //add served event
                store.addToWaitTime(w.serve(c));
                store.addEvent(e);           
                DoneEvent f = new DoneEvent(c,w,w.finishTime(time));     //add done event
                store.addEvent(f);
            } else {                                         //willing to wait
                double nextService = w.lastTime();
                WaitEvent e = new WaitEvent(c,w,time);       //add waiting event
                ServeEvent f = new ServeEvent(c,w,nextService);     //add served event
                store.addToWaitTime(w.serve(c));
                store.addEvent(e);
                store.addEvent(f);
                DoneEvent g = new DoneEvent(c,w,w.finishTime(nextService));        //add done event
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