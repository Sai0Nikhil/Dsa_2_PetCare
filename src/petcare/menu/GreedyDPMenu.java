package petcare.menu;

import petcare.algorithms.ActivitySelection;
import petcare.algorithms.FractionalKnapsack;
import petcare.algorithms.LIS;
import petcare.algorithms.ZeroOneKnapsack;
import petcare.models.Appointment;
import petcare.utils.DataGenerator;
import petcare.utils.DisplayUtils;
import petcare.utils.InputUtils;

import java.util.List;

/**
 * Module M6 - Greedy Algorithms & Dynamic Programming.
 */
public class GreedyDPMenu {

    public void show() {
        while (true) {
            DisplayUtils.header("M6 - Greedy & Dynamic Programming");
            System.out.println("  1. Activity Selection: maximise appointments");
            System.out.println("  2. Fractional Knapsack: allocate clinic resources");
            System.out.println("  3. 0/1 Knapsack: pick treatment plans within budget");
            System.out.println("  4. Longest Increasing Subsequence: pet health trends");
            System.out.println("  0. Back to main menu");
            int c = InputUtils.readIntInRange("Choice: ", 0, 4);
            switch (c) {
                case 1: activity(); break;
                case 2: fractional(); break;
                case 3: zeroOne(); break;
                case 4: lis(); break;
                case 0: return;
                default: break;
            }
            InputUtils.pause();
        }
    }

    private void activity() {
        List<Appointment> apps = DataGenerator.generateAppointments(10);
        DisplayUtils.subHeader("All appointments");
        DisplayUtils.printAll(apps);
        DisplayUtils.subHeader("Greedy-selected (non-overlapping)");
        DisplayUtils.printAll(ActivitySelection.select(apps));
    }

    private void fractional() {
        FractionalKnapsack.Item[] items = new FractionalKnapsack.Item[]{
                new FractionalKnapsack.Item("Antiseptic(L)",  4, 28),
                new FractionalKnapsack.Item("Bandages(box)",  2, 14),
                new FractionalKnapsack.Item("Vaccine(vial)",  5, 35),
                new FractionalKnapsack.Item("Painkiller(kg)", 3, 18),
                new FractionalKnapsack.Item("X-Ray film",     1,  6)};
        double cap = InputUtils.readDouble("Resource capacity (units): ");
        FractionalKnapsack.Result r = FractionalKnapsack.solve(items, cap);
        DisplayUtils.subHeader("Allocation");
        for (int i = 0; i < items.length; i++)
            System.out.printf("  %-15s : %.2f x weight %.1f (value %.1f)%n",
                    items[i].name, r.fractions[i], items[i].weight, items[i].value);
        DisplayUtils.ok(String.format("Maximum value = %.2f", r.totalValue));
    }

    private void zeroOne() {
        ZeroOneKnapsack.Plan[] plans = new ZeroOneKnapsack.Plan[]{
                new ZeroOneKnapsack.Plan("Surgery-A",        50, 90),
                new ZeroOneKnapsack.Plan("Vaccination-Drive",20, 35),
                new ZeroOneKnapsack.Plan("Dental-Camp",      30, 55),
                new ZeroOneKnapsack.Plan("Diet-Plan",        10, 18),
                new ZeroOneKnapsack.Plan("Behaviour-Therapy",25, 40)};
        int budget = InputUtils.readInt("Clinic budget: ");
        ZeroOneKnapsack.Result r = ZeroOneKnapsack.solve(plans, budget);
        DisplayUtils.subHeader("Treatment plans");
        for (ZeroOneKnapsack.Plan p : plans)
            System.out.printf("  %-20s cost=%d benefit=%d%n", p.name, p.cost, p.benefit);
        DisplayUtils.ok("Best benefit = " + r.bestBenefit);
        DisplayUtils.subHeader("Plans chosen");
        for (ZeroOneKnapsack.Plan p : r.chosen) System.out.println("  - " + p.name);
    }

    private void lis() {
        int[] healthScore = {62, 65, 60, 68, 70, 64, 72, 75, 71, 78, 80};
        DisplayUtils.subHeader("Pet weekly health scores");
        StringBuilder sb = new StringBuilder("  ");
        for (int v : healthScore) sb.append(v).append(' ');
        System.out.println(sb);
        LIS.Result r = LIS.compute(healthScore);
        DisplayUtils.ok("Longest improving streak length = " + r.length);
        DisplayUtils.info("Sequence: " + r.sequence);
    }
}
