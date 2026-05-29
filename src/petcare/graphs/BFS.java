package petcare.graphs;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

public class BFS {

    public static List<Integer> traverse(Graph g, int source) {
        List<Integer> order = new ArrayList<>();
        boolean[] visited = new boolean[g.vertices()];
        Deque<Integer> q = new ArrayDeque<>();
        q.add(source);
        visited[source] = true;
        while (!q.isEmpty()) {
            int u = q.poll();
            order.add(u);
            for (Graph.Edge e : g.neighbours(u)) {
                if (!visited[e.to]) {
                    visited[e.to] = true;
                    q.add(e.to);
                }
            }
        }
        return order;
    }

    public static int[] hopDistances(Graph g, int source) {
        int[] dist = new int[g.vertices()];
        Arrays.fill(dist, -1);
        dist[source] = 0;
        Deque<Integer> q = new ArrayDeque<>();
        q.add(source);
        while (!q.isEmpty()) {
            int u = q.poll();
            for (Graph.Edge e : g.neighbours(u)) {
                if (dist[e.to] == -1) {
                    dist[e.to] = dist[u] + 1;
                    q.add(e.to);
                }
            }
        }
        return dist;
    }
}
