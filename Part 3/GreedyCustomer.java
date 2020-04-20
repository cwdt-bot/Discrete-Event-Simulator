package cs2030.simulator;

import java.util.List;

public class GreedyCustomer extends Customer {

    public GreedyCustomer(double arrTime) {
        super(arrTime);
    }

    @Override
    public int assess(List<Integer> list) {
        if (list.contains(-2)) return list.indexOf(-2);
        int currMin = Integer.MAX_VALUE;
        for (int i : list) {
            if (i != -1 && i < currMin) {
                currMin = i;
            }
        }
        if (currMin == Integer.MAX_VALUE) { //since this means the whole list is -1
            return -1; //return -1 to show that customer leaves
        } else {
            return list.indexOf(currMin); //get the number of the queue that they want
        }
    }

    @Override
    public String toString() {
        return this.id() + "(greedy)";
    }

}