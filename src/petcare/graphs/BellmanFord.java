package petcare.graphs;

import java.util.Arrays;

/**
 * Bellman-Ford — handles negative weights (e.g., discounted referral edges)
 * and detects negative-weight cycles.
 */
public class BellmanFord {

    public static class Result {
        public final int[] dist;
        public final boolean hasNegativeCycle;
        public Result(int[] dist, boolean hasNegativeCycle) {
            this.dist = dist; this.hasNegativeCycle = hasNegativeCycle;
        }
    }

    public static Result run(Graph g, int source) {
        int V = g.vertices();
        int[] dist = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE / 2);
        dist[source] = 0;
        for (int i = 0; i < V - 1; i++) {
            for (Graph.Edge e : g.edges()) {
                if (dist[e.from] + e.weight < dist[e.to])
                    dist[e.to] = dist[e.from] + e.weight;
                if (!g.isDirected() &&
                    dist[e.to] + e.weight < dist[e.from])
                    dist[e.from] = dist[e.to] + e.weight;
            }
        }
        boolean neg = false;
        for (Graph.Edge e : g.edges()) {
            if (dist[e.from] + e.weight < dist[e.to]) { neg = true; break; }
        }
        return new Result(dist, neg);
    }
}
