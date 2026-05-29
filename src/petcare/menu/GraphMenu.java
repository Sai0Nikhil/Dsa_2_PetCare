package petcare.menu;

import petcare.graphs.BFS;
import petcare.graphs.DFS;
import petcare.graphs.Graph;
import petcare.graphs.MST;
import petcare.models.Clinic;
import petcare.utils.DataGenerator;
import petcare.utils.DisplayUtils;
import petcare.utils.InputUtils;

import java.util.List;

public class GraphMenu {

    private final List<Clinic> clinics = DataGenerator.generateClinics();
    private final Graph network = buildNetwork();

    private Graph buildNetwork() {
        Graph g = new Graph(clinics.size());
        g.addEdge(0, 1, 350);
        g.addEdge(0, 2, 630);
        g.addEdge(1, 2, 570);
        g.addEdge(1, 3, 980);
        g.addEdge(2, 3, 710);
        g.addEdge(2, 4, 1500);
        g.addEdge(3, 4, 1400);
        g.addEdge(3, 5, 150);
        g.addEdge(4, 5, 1450);
        return g;
    }

    public void show() {
        while (true) {
            DisplayUtils.header("M3 - Graph Algorithms (Healthcare Network)");
            System.out.println("  1. List clinics and edges");
            System.out.println("  2. BFS: nearest facilities (hop-count)");
            System.out.println("  3. DFS: workflow connectivity");
            System.out.println("  4. MST (Kruskal)");
            System.out.println("  5. MST (Prim)");
            System.out.println("  0. Back to main menu");
            int c = InputUtils.readIntInRange("Choice: ", 0, 5);
            switch (c) {
                case 1: listNetwork(); break;
                case 2: doBFS(); break;
                case 3: doDFS(); break;
                case 4: doKruskal(); break;
                case 5: doPrim(); break;
                case 0: return;
                default: break;
            }
            InputUtils.pause();
        }
    }

    private void listNetwork() {
        DisplayUtils.subHeader("Clinics");
        for (int i = 0; i < clinics.size(); i++)
            System.out.printf("  [%d] %s%n", i, clinics.get(i));
        DisplayUtils.subHeader("Edges (km)");
        for (Graph.Edge e : network.edges())
            System.out.printf("  %s <--%d km--> %s%n",
                    clinics.get(e.from).getName(), e.weight, clinics.get(e.to).getName());
    }

    private void doBFS() {
        int src = pickClinic("Source clinic index: ");
        List<Integer> order = BFS.traverse(network, src);
        int[] dist = BFS.hopDistances(network, src);
        DisplayUtils.subHeader("BFS visit order");
        for (int v : order) System.out.println("  " + clinics.get(v).getName());
        DisplayUtils.subHeader("Hop distances from " + clinics.get(src).getName());
        for (int i = 0; i < dist.length; i++)
            System.out.printf("  -> %-12s : %d hops%n", clinics.get(i).getName(), dist[i]);
    }

    private void doDFS() {
        int src = pickClinic("Source clinic index: ");
        DisplayUtils.subHeader("DFS visit order");
        for (int v : DFS.traverse(network, src)) System.out.println("  " + clinics.get(v).getName());
        DisplayUtils.info("Connected components in network: " + DFS.connectedComponents(network));
    }

    private void doKruskal() { printMST("Kruskal", MST.kruskal(network)); }
    private void doPrim()    { printMST("Prim",    MST.prim(network)); }

    private void printMST(String name, MST.Result r) {
        DisplayUtils.subHeader(name + " MST edges");
        for (Graph.Edge e : r.edges)
            System.out.printf("  %s <--%d--> %s%n",
                    clinics.get(e.from).getName(), e.weight, clinics.get(e.to).getName());
        DisplayUtils.ok("Total collaboration cost = " + r.totalWeight + " km");
    }

    private int pickClinic(String prompt) {
        for (int i = 0; i < clinics.size(); i++)
            System.out.printf("  [%d] %s%n", i, clinics.get(i).getName());
        return InputUtils.readIntInRange(prompt, 0, clinics.size() - 1);
    }
}
