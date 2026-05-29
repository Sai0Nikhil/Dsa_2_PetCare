package petcare.algorithms;

import java.util.Arrays;
import java.util.Comparator;

public class FractionalKnapsack {

    public static class Item {
        public final String name;
        public final double weight;
        public final double value;
        public Item(String name, double weight, double value) {
            this.name = name; this.weight = weight; this.value = value;
        }
        public double density() { return value / weight; }
    }

    public static class Result {
        public final double totalValue;
        public final double[] fractions;
        public Result(double totalValue, double[] fractions) {
            this.totalValue = totalValue; this.fractions = fractions;
        }
    }

    public static Result solve(Item[] items, double capacity) {
        Integer[] order = new Integer[items.length];
        for (int i = 0; i < order.length; i++) order[i] = i;
        Arrays.sort(order, Comparator.comparingDouble((Integer i) -> -items[i].density()));

        double[] fractions = new double[items.length];
        double total = 0, remaining = capacity;
        for (int i : order) {
            if (remaining <= 0) break;
            if (items[i].weight <= remaining) {
                fractions[i] = 1.0;
                total += items[i].value;
                remaining -= items[i].weight;
            } else {
                fractions[i] = remaining / items[i].weight;
                total += items[i].value * fractions[i];
                remaining = 0;
            }
        }
        return new Result(total, fractions);
    }
}
