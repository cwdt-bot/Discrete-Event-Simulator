class Customer {
	private final int id;
	private final double arrTime;
	private final int patience;

	Customer(int id, double arrTime) {
		this.id = id;
		this.arrTime = arrTime;
		this.patience = 0;
	}

	Customer(int id, double arrTime, int p) {
		this.id = id;
		this.arrTime = arrTime;
		this.patience = p;
	}

	@Override
	public String toString() {
		return this.id() + " arrives at " + this.arrTime();
	}

	double arrTime() {
		return this.arrTime;
	}

	int id() {
		return this.id;
	}
	
	// this method is how the customer will react to store situation
	boolean assess(int toWait) {
		if (toWait <= patience) {
			return true;
		} else {
			return false;
		}
	}


}
	

