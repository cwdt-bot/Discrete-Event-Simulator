class Event implements Comparable<Event> {
	private final Customer c;
	private final Waiter w;
	private final double time;
	private final boolean wait;

	/* this is used when there is a waiter/customer match, the wait boolean indicates if service 
	is immediate*/
	Event(Customer c, Waiter w, double time, boolean wait) {
		this.c = c;
		this.w = w;
		this.time = time;
		this.wait = wait;
	}

	// this constructor is used when the customer cannot be served, so no waiter can be assigned 
	Event(Customer c, double time) {
		this.c = c;
		this.w = null;
		this.time = time;
		this.wait = false;
	}

	double getTime() {
		return this.time;
	}
	
	public String toString() {
		String state = this.state();
		if (state.equals("left")) {
			return "Customer " + c.id() + " leaves";
		} else if (state.equals("waits")) {
			return "Customer " + c.id() +  " waits";
		} else {
			return "Customer " + c.id() + " served by Waiter " + w.id();
		}
	}
	
	public int compareTo(Event e) {
		if 	(e.getTime() == this.getTime()) {
			return 0;
		} else if (this.getTime() < e.getTime()) {
			return -1;
		} else {
			return 1;
		}
	}

	String state() {
		if (this.w == null) {
			return "left";
		} else if (this.wait == true) {
			return "waits";
		} else {
			return "served";
		}
	}
	
}