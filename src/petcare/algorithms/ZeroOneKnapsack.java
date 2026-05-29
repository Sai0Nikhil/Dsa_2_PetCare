package petcare.algorithms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ZeroOneKnapsack {

    public static class Plan {
        public final String name;
        public final int cost;
        public final int benefit;
        public Plan(String name, int cost, int benefit) {
            this.name = name; this.cost = cost; this.benefit = benefit;
        }
    }

    public static class Result {
        public final int bestBenefit;
        public final List<Plan> chosen;
        public Result(int bestBenefit, List<Plan> chosen) {
            this.bestBenefit = bestBenefit; this.chosen = chosen;
        }
    }

    public static Result solve(Plan[] plans, int budget) {
        int n = plans.length;
        int[][] dp = new int[n + 1][budget + 1];
        for (int i = 1; i <= n; i++) {
            Plan p = plans[i - 1];
            for (int w = 0; w <= budget; w++) {
                dp[i][w] = dp[i - 1][w];
                if (p.cost <= w) {
                    dp[i][w] = Math.max(dp[i][w], dp[i - 1][w - p.cost] + p.benefit);
                }
            }
        }
        List<Plan> picked = new ArrayList<>();
        int w = budget;
        for (int i = n; i >= 1; i--) {
            if (dp[i][w] != dp[i - 1][w]) {
                picked.add(plans[i - 1]);
                w -= plans[i - 1].cost;
            }
        }
        Collections.reverse(picked);
        return new Result(dp[n][budget], picked);
    }
}
