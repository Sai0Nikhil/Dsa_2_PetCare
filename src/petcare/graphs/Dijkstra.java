package petcare.graphs;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Dijkstra's algorithm — fastest non-negative weighted path from a source
 * clinic to all other clinics.
 */
public class Dijkstra {

    public static int[] shortestPaths(Graph g, int source) {
        int V = g.vertices();
        int[] dist = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[source] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        pq.add(new int[]{source, 0});
        while (!pq.isEmpty()) {
            int[] top = pq.poll();
            int u = top[0], d = top[1];
            if (d > dist[u]) continue;
            for (Graph.Edge e : g.neighbours(u)) {
                int nd = d + e.weight;
                if (nd < dist[e.to]) {
                    dist[e.to] = nd;
                    pq.add(new int[]{e.to, nd});
                }
            }
        }
        return dist;
    }
}
