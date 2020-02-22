import java.util.ArrayList;

class Waiter {
	private final int id;
	private final double serviceTime;
	private ArrayList<Customer> q = new ArrayList<>(); 

	Waiter(int id, double serviceTime) {
		this.id = id;
		this.serviceTime = serviceTime;
	}

	int id() {
		return this.id;
	}

	double finishTime(Customer c) {
		return c.arrTime() + this.serviceTime;
	}


	int inWait(double time) {
		int r = this.q.size();
		if (r == 0) {
			return r;
		}
		for (Customer c : q) {
			if (this.finishTime(c) <= time) {
				r--;
			} else {
				break;
			}		
		}
		return r;
	}
	
	double lastTime() {
		return this.finishTime(this.q.get(-1));
	}

	void serve(Customer c) {
		this.q.add(c);
	}
}



