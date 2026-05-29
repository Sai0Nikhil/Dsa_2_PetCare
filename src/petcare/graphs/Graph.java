package petcare.graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graph {

    public static class Edge {
        public final int from, to;
        public final int weight;
        public Edge(int from, int to, int weight) {
            this.from = from; this.to = to; this.weight = weight;
        }
    }

    private final int vertices;
    private final Map<Integer, List<Edge>> adj = new HashMap<>();
    private final List<Edge> edgeList = new ArrayList<>();
    private final boolean directed;

    public Graph(int vertices) { this(vertices, false); }
    public Graph(int vertices, boolean directed) {
        this.vertices = vertices;
        this.directed = directed;
        for (int i = 0; i < vertices; i++) adj.put(i, new ArrayList<>());
    }

    public void addEdge(int u, int v, int w) {
        adj.get(u).add(new Edge(u, v, w));
        edgeList.add(new Edge(u, v, w));
        if (!directed) adj.get(v).add(new Edge(v, u, w));
    }

    public int vertices() { return vertices; }
    public boolean isDirected() { return directed; }
    public List<Edge> neighbours(int u) { return adj.get(u); }
    public List<Edge> edges() { return edgeList; }
}
