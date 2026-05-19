package petcare.graphs;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * Kahn's algorithm — topological order for scheduling treatment procedures
 * with prerequisite dependencies. Operates on a DIRECTED graph.
 */
public class TopologicalSort {

    public static List<Integer> sort(Graph directed) {
        int V = directed.vertices();
        int[] indeg = new int[V];
        for (int u = 0; u < V; u++) {
            for (Graph.Edge e : directed.neighbours(u)) indeg[e.to]++;
        }
        Deque<Integer> q = new ArrayDeque<>();
        for (int i = 0; i < V; i++) if (indeg[i] == 0) q.add(i);

        List<Integer> order = new ArrayList<>();
        while (!q.isEmpty()) {
            int u = q.poll();
            order.add(u);
            for (Graph.Edge e : directed.neighbours(u)) {
                if (--indeg[e.to] == 0) q.add(e.to);
            }
        }
        if (order.size() != V) {
            throw new IllegalStateException("Cycle detected - topological order not possible.");
        }
        return order;
    }
}
