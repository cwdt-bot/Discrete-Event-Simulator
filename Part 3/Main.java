import java.util.Scanner;

import cs2030.simulator.Store;
import cs2030.simulator.Server;
import cs2030.simulator.RobotServer;



public class Main{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int seed = sc.nextInt();
        int numServers = sc.nextInt();
        int numRobot = sc.nextInt();
        int qmax = sc.nextInt();
        int numCust = sc.nextInt();
        double lambda = sc.nextDouble();
        double mu = sc.nextDouble();
        double rho = sc.nextDouble();
        double restchance = sc.nextDouble();
        double greedychance = sc.nextDouble();
        Store store = new Store(seed, lambda, mu, rho, restchance, greedychance);
        for (int x = 0; x < numServers; x++) {
            store.employ(new Server(qmax)); //create all the servers and put them into the store
        }
        for (int x = 0; x < numRobot; x++) {
            store.employ(new RobotServer(qmax)); //create all the servers and put them into the store
        }
        store.customers(numCust);
        sc.close();
    }
}