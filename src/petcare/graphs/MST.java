package petcare.graphs;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class MST {

    public static class Result {
        public final List<Graph.Edge> edges;
        public final long totalWeight;
        public Result(List<Graph.Edge> edges, long totalWeight) {
            this.edges = edges; this.totalWeight = totalWeight;
        }
    }

    public static Result kruskal(Graph g) {
        List<Graph.Edge> all = new ArrayList<>(g.edges());
        all.sort(Comparator.comparingInt(e -> e.weight));
        int[] parent = new int[g.vertices()];
        int[] rank = new int[g.vertices()];
        for (int i = 0; i < parent.length; i++) parent[i] = i;

        List<Graph.Edge> taken = new ArrayList<>();
        long total = 0;
        for (Graph.Edge e : all) {
            int a = find(parent, e.from);
            int b = find(parent, e.to);
            if (a != b) {
                taken.add(e);
                total += e.weight;
                union(parent, rank, a, b);
            }
        }
        return new Result(taken, total);
    }

    private static int find(int[] p, int x) {
        while (p[x] != x) { p[x] = p[p[x]]; x = p[x]; }
        return x;
    }
    private static void union(int[] p, int[] r, int a, int b) {
        if (r[a] < r[b]) p[a] = b;
        else if (r[a] > r[b]) p[b] = a;
        else { p[b] = a; r[a]++; }
    }

    public static Result prim(Graph g) {
        int V = g.vertices();
        boolean[] inMst = new boolean[V];
        List<Graph.Edge> taken = new ArrayList<>();
        long total = 0;
        PriorityQueue<Graph.Edge> pq = new PriorityQueue<>(Comparator.comparingInt(e -> e.weight));
        inMst[0] = true;
        for (Graph.Edge e : g.neighbours(0)) pq.add(e);
        while (!pq.isEmpty() && taken.size() < V - 1) {
            Graph.Edge e = pq.poll();
            if (inMst[e.to]) continue;
            inMst[e.to] = true;
            taken.add(e);
            total += e.weight;
            for (Graph.Edge ne : g.neighbours(e.to))
                if (!inMst[ne.to]) pq.add(ne);
        }
        return new Result(taken, total);
    }
}
