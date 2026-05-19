package petcare.graphs;

/**
 * Floyd-Warshall — all-pairs shortest paths between every pair of clinics.
 */
public class FloydWarshall {

    public static final int INF = Integer.MAX_VALUE / 2;

    public static int[][] run(Graph g) {
        int V = g.vertices();
        int[][] d = new int[V][V];
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) d[i][j] = (i == j) ? 0 : INF;
        }
        for (Graph.Edge e : g.edges()) {
            if (e.weight < d[e.from][e.to]) d[e.from][e.to] = e.weight;
            if (!g.isDirected() && e.weight < d[e.to][e.from]) d[e.to][e.from] = e.weight;
        }
        for (int k = 0; k < V; k++) {
            for (int i = 0; i < V; i++) {
                for (int j = 0; j < V; j++) {
                    if (d[i][k] + d[k][j] < d[i][j]) d[i][j] = d[i][k] + d[k][j];
                }
            }
        }
        return d;
    }
}
