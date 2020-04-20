import java.util.Scanner;
import java.util.PriorityQueue;
import java.util.ArrayList;

class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Customer> inc = new ArrayList<>();
        CustomerLogic baseLogic = new CustomerLogic(1);
        int numWaiter = sc.nextInt();
        while (sc.hasNext()) {
            inc.add(new Customer(sc.nextDouble(), baseLogic));
        }
        Store store = new Store("Noma");
        for (int x = 0; x < numWaiter; x++) {
            store.employ(new Waiter(x + 1, 1));
        }
        store.serve(inc);
        PriorityQueue<Event> schedule = store.showSchedule();
        while (schedule.peek() != null) {
            System.out.println(schedule.poll());
        }
        System.out.println("[" + String.format("%.3f",store.avgWaitTime()) + " " 
            + store.numServed() + " " + store.numLeft() + "]"); 
        sc.close();
    }
}