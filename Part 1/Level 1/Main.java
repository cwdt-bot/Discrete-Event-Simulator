import java.util.Scanner;
import java.util.ArrayList;

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
        for (Customer c : inc) {
            System.out.println(c);
        }
        System.out.println("Number of customers: " + inc.size());
    }
}
