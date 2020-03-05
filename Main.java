import java.util.Scanner;
import java.util.PriorityQueue;
import java.util.ArrayList;

class Main {

    public static ArrayList<Customer> scanIn() {
        Scanner sc = new Scanner(System.in);
        ArrayList<Customer> inc = new ArrayList<>();
        CustomerLogic baseLogic = new CustomerLogic(1);
        while (sc.hasNext()) {
            inc.add(new Customer(sc.nextDouble(), baseLogic));
        }
        sc.close();
        return inc;
    }

    public static void main(String[] args) {
        ArrayList<Customer> inc = Main.scanIn();
        Store store = new Store("Noma");
        store.employ(new Waiter(1,1));
        store.serve(inc);
        PriorityQueue<Event> schedule = store.showSchedule();
        while (schedule.peek() != null) {
            System.out.println(schedule.poll());
        }
        System.out.println("[" + String.format("%.3f",store.avgWaitTime()) + " " 
            + store.numServed() + " " + store.numLeft() + "]");


    }
}