import java.util.Scanner;
import java.util.PriorityQueue;

class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int count = 1;
		PriorityQueue<Customer> q =  new PriorityQueue<>();
		while (sc.hasNext()) {
			q.add(new Customer(count, sc.nextDouble()));
			count ++;
		}
		sc.close();
		int s = q.size();
		for (int i = 0; i < s; i ++) {
			System.out.println(q.poll());
		}
		System.out.println("Number of customers: " + s);
	}
}
