package petcare.menu;

import petcare.graphs.BellmanFord;
import petcare.graphs.Dijkstra;
import petcare.graphs.FloydWarshall;
import petcare.graphs.Graph;
import petcare.graphs.TopologicalSort;
import petcare.models.Clinic;
import petcare.utils.DataGenerator;
import petcare.utils.DisplayUtils;
import petcare.utils.InputUtils;

import java.util.List;

public class ShortestPathMenu {

    private final List<Clinic> clinics = DataGenerator.generateClinics();
    private final Graph weighted = buildWeighted();
    private final String[] procedures = {
            "Admit", "Diagnose", "X-Ray", "Lab Test", "Surgery", "Recovery", "Discharge"};

    private Graph buildWeighted() {
        Graph g = new Graph(clinics.size());
        g.addEdge(0, 1, 35);
        g.addEdge(0, 2, 63);
        g.addEdge(1, 2, 57);
        g.addEdge(1, 3, 98);
        g.addEdge(2, 3, 71);
        g.addEdge(2, 4, 150);
        g.addEdge(3, 4, 140);
        g.addEdge(3, 5, 15);
        g.addEdge(4, 5, 145);
        return g;
    }

    public void show() {
        while (true) {
            DisplayUtils.header("M4 - Shortest Path Optimisation");
            System.out.println("  1. Dijkstra (fastest routes from a clinic)");
            System.out.println("  2. Bellman-Ford (handles negative-weight referral edges)");
            System.out.println("  3. Floyd-Warshall (all-pairs connectivity)");
            System.out.println("  4. Topological sort (treatment procedure schedule)");
            System.out.println("  0. Back to main menu");
            int c = InputUtils.readIntInRange("Choice: ", 0, 4);
            switch (c) {
                case 1: doDijkstra(); break;
                case 2: doBellmanFord(); break;
                case 3: doFloyd(); break;
                case 4: doTopo(); break;
                case 0: return;
                default: break;
            }
            InputUtils.pause();
        }
    }

    private int pickClinic(String prompt) {
        for (int i = 0; i < clinics.size(); i++)
            System.out.printf("  [%d] %s%n", i, clinics.get(i).getName());
        return InputUtils.readIntInRange(prompt, 0, clinics.size() - 1);
    }

    private void doDijkstra() {
        int src = pickClinic("Source clinic index: ");
        int[] d = Dijkstra.shortestPaths(weighted, src);
        DisplayUtils.subHeader("Dijkstra distances from " + clinics.get(src).getName());
        for (int i = 0; i < d.length; i++)
            System.out.printf("  -> %-12s : %s%n", clinics.get(i).getName(),
                    d[i] == Integer.MAX_VALUE ? "INF" : d[i]);
    }

    private void doBellmanFord() {
        int src = pickClinic("Source clinic index: ");
        BellmanFord.Result r = BellmanFord.run(weighted, src);
        DisplayUtils.subHeader("Bellman-Ford distances from " + clinics.get(src).getName());
        for (int i = 0; i < r.dist.length; i++)
            System.out.printf("  -> %-12s : %d%n", clinics.get(i).getName(), r.dist[i]);
        DisplayUtils.info("Negative cycle? " + r.hasNegativeCycle);
    }

    private void doFloyd() {
        int[][] d = FloydWarshall.run(weighted);
        DisplayUtils.subHeader("Floyd-Warshall all-pairs shortest distances");
        System.out.print("          ");
        for (Clinic c : clinics) System.out.printf("%-10s", c.getName());
        System.out.println();
        for (int i = 0; i < d.length; i++) {
            System.out.printf("  %-8s", clinics.get(i).getName());
            for (int j = 0; j < d[i].length; j++) {
                String v = d[i][j] >= FloydWarshall.INF ? "INF" : String.valueOf(d[i][j]);
                System.out.printf("%-10s", v);
            }
            System.out.println();
        }
    }

    private void doTopo() {
        Graph dag = new Graph(procedures.length, true);
        dag.addEdge(0, 1, 1);
        dag.addEdge(1, 2, 1);
        dag.addEdge(1, 3, 1);
        dag.addEdge(2, 4, 1);
        dag.addEdge(3, 4, 1);
        dag.addEdge(4, 5, 1);
        dag.addEdge(5, 6, 1);
        DisplayUtils.subHeader("Topological order of treatment procedures");
        for (int v : TopologicalSort.sort(dag)) System.out.println("  -> " + procedures[v]);
    }
}
