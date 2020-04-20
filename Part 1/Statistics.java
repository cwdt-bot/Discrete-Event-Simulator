class Statistics {
    private int numServed = 0;
    private int numLeft = 0;
    private int numWaited = 0;
    private int numVisited = 0 ;
    private double timeWaited = 0;
    private final Store store;

    Statistics(Store s) {
        this.store = s;
    }
}