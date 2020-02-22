import java.util.PriorityQueue;
import java.util.ArrayList;

class Store {
	private final String name;
	private ArrayList<Waiter> waiters = new ArrayList<>();
	private PriorityQueue<Event> q = new PriorityQueue<>();
	private int served = 0;
	private int waited = 0;
	private int left = 0;

	Store(String name) {
		this.name = name;
	}

	void employ(Waiter w) {
		this.waiters.add(w);
	}

	String name() {
		return this.name;
	}

	// if customer can be served, returned the assigned waiter, if not, null
	// nulls occur when customer input is chronogically earlier than previous inputs 
	// if inputs for customers are strictly chronological, nulls should never occur
	Waiter receives(double time) {
		int min = Integer.MAX_VALUE;
		Waiter assigned = null;
		for (Waiter w : waiters) {
			int waiting = w.inWait(time);
			if (waiting == 0) {
				assigned = w;
				break; //if there is no wait, means that waiter can serve customer right away
			} else if (waiting <= min) {
				min = waiting;
				assigned = w;
			}
		}
		return assigned;
	}

	void addEvent(Event e, String state) {
		this.q.add(e);
		if (state.equals("left")) {
			this.left++;
		} else if (state.equals("waits")){
			this.waited++;
		} else {
			this.served++;
		}
	}

	PriorityQueue<Event> showSchedule() {
		return this.q;
	}

	int numServed() {
		return this.served;
	}

	int numWaited() {
		return this.waited;
	}

	int numLeft() {
		return this.left;
	}

}
			
			

	
