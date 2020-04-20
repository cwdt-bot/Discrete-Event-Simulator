import java.util.Scanner;

import cs2030.simulator.Store;
import cs2030.simulator.Server;
import cs2030.simulator.RobotServer;


/**
 * The Main class takes in the input from the user and uses it to create a Store object, 
 * along with specified number of Servers and RobotServers. The input order is as such:
 * <p>an int value denoting the base seed for the RandomGenerator object;</p>
 * <p>an int value representing the number of servers</p>
 * <p>an int value representing the number of self-checkout counters, Nself</p>
 * <p>an int value for the maximum queue length, Qmax</p>
 * <p>an int representing the number of customers (or the number of arrival events) to simulate</p>
 * <p>a positive double parameter for the arrival rate, lambda</p>
 * <p>a positive double parameter for the service rate, mu</p>
 * <p>a positive double parameter for the resting rate, rho</p>
 * <p>a double parameter for the probability of resting, Pr</p>
 * <p>a double parameter for the probability of a greedy customer occurring, Pg.</p>
 * 
 * <p>The events are printed out through the execution of store.run().</p>
 */
public class Main {
    /**
     * Runs the program.
     * @param args default
     */
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
        //create all the servers and put them into the store
        for (int x = 0; x < numServers; x++) {
            store.employ(new Server(qmax)); 
        }
        //create all the robot servers and put them into the store
        for (int x = 0; x < numRobot; x++) {
            store.employ(new RobotServer(qmax));
        }
        store.customers(numCust);
        sc.close();
    }
}