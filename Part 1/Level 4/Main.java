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
            double time = c.arrTime()
            Event arrive = new Event(c, time, "arrives");
            store.addEvent(arrive);
        }
        System.out.println("# Adding arrivals");
        for (Event e : show.showSchedule()) {
            System.out.println(e);
        }
        while (store.showSchedule.peek() != null) {
            Event e = store.showSchedule.poll();
            if (e.state().equals("arrives")) { //incomplete
            }
        }

}
