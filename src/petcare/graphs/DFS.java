package petcare.graphs;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class DFS {

    public static List<Integer> traverse(Graph g, int source) {
        List<Integer> order = new ArrayList<>();
        boolean[] visited = new boolean[g.vertices()];
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(source);
        while (!stack.isEmpty()) {
            int u = stack.pop();
            if (visited[u]) continue;
            visited[u] = true;
            order.add(u);
            List<Graph.Edge> neigh = g.neighbours(u);
            for (int i = neigh.size() - 1; i >= 0; i--) {
                int v = neigh.get(i).to;
                if (!visited[v]) stack.push(v);
            }
        }
        return order;
    }

    public static int connectedComponents(Graph g) {
        boolean[] visited = new boolean[g.vertices()];
        int count = 0;
        for (int i = 0; i < g.vertices(); i++) {
            if (!visited[i]) {
                count++;
                Deque<Integer> stack = new ArrayDeque<>();
                stack.push(i);
                while (!stack.isEmpty()) {
                    int u = stack.pop();
                    if (visited[u]) continue;
                    visited[u] = true;
                    for (Graph.Edge e : g.neighbours(u))
                        if (!visited[e.to]) stack.push(e.to);
                }
            }
        }
        return count;
    }
}
