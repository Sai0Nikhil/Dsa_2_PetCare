package petcare.menu;

import petcare.models.Vaccination;
import petcare.trees.BPlusTree;
import petcare.trees.BTree;
import petcare.trees.FenwickTree;
import petcare.trees.SegmentTree;
import petcare.utils.DataGenerator;
import petcare.utils.DisplayUtils;
import petcare.utils.InputUtils;

import java.util.List;
import java.util.Random;

public class MultiwayMenu {

    public void show() {
        while (true) {
            DisplayUtils.header("M2 - Multiway Trees & Range Queries");
            System.out.println("  1. B-Tree: index clinic record IDs");
            System.out.println("  2. B+ Tree: range query on vaccination schedule");
            System.out.println("  3. Segment Tree: clinic visits across a date range");
            System.out.println("  4. Fenwick Tree: cumulative treatment statistics");
            System.out.println("  0. Back to main menu");
            int c = InputUtils.readIntInRange("Choice: ", 0, 4);
            switch (c) {
                case 1: bTreeDemo(); break;
                case 2: bPlusDemo(); break;
                case 3: segmentDemo(); break;
                case 4: fenwickDemo(); break;
                case 0: return;
                default: break;
            }
            InputUtils.pause();
        }
    }

    private void bTreeDemo() {
        BTree bt = new BTree(3);
        int[] keys = {1001, 1015, 1003, 1090, 1020, 1005, 1077, 1002, 1050, 1011};
        for (int k : keys) bt.insert(k);
        DisplayUtils.subHeader("B-Tree (order 3) - inserted keys");
        DisplayUtils.printArray(keys);
        DisplayUtils.subHeader("In-order traversal");
        System.out.println("  " + bt.levelOrder());
        int q = InputUtils.readInt("Search key: ");
        System.out.println(bt.search(q) ? "  [FOUND]" : "  [NOT FOUND]");
    }

    private void bPlusDemo() {
        BPlusTree bp = new BPlusTree();
        List<Vaccination> vs = DataGenerator.generateVaccinations(15);
        for (Vaccination v : vs) bp.insert(v.getDueDay(), v.getVaccineName() + "[pet#" + v.getPetId() + "]");
        DisplayUtils.subHeader("Vaccination index (B+ Tree-like)");
        DisplayUtils.printAll(vs);
        int from = InputUtils.readInt("Range FROM day: ");
        int to   = InputUtils.readInt("Range TO   day: ");
        DisplayUtils.subHeader("Vaccinations due in [" + from + "," + to + "]");
        DisplayUtils.printAll(bp.rangeQuery(from, to));
    }

    private void segmentDemo() {
        int n = 14;
        int[] visits = new int[n];
        Random r = new Random(42);
        for (int i = 0; i < n; i++) visits[i] = 5 + r.nextInt(40);
        DisplayUtils.subHeader("Daily clinic visits (day 1.." + n + ")");
        DisplayUtils.printArray(visits);
        SegmentTree st = new SegmentTree(visits);
        int from = InputUtils.readIntInRange("Range FROM (1.." + n + "): ", 1, n);
        int to   = InputUtils.readIntInRange("Range TO   (1.." + n + "): ", from, n);
        long total = st.rangeSum(from - 1, to - 1);
        DisplayUtils.ok("Total visits in [" + from + "," + to + "] = " + total);
    }

    private void fenwickDemo() {
        int n = 12;
        int[] treatments = {2, 5, 4, 3, 8, 7, 2, 9, 4, 3, 6, 5};
        FenwickTree ft = new FenwickTree(treatments);
        DisplayUtils.subHeader("Daily treatments (day 1.." + n + ")");
        DisplayUtils.printArray(treatments);
        int upto = InputUtils.readIntInRange("Prefix sum upto day (1.." + n + "): ", 1, n);
        DisplayUtils.ok("Cumulative treatments through day " + upto + " = " + ft.prefix(upto - 1));
        int l = InputUtils.readIntInRange("Range FROM (1.." + n + "): ", 1, n);
        int r = InputUtils.readIntInRange("Range TO   (1.." + n + "): ", l, n);
        DisplayUtils.ok("Treatments in [" + l + "," + r + "] = " + ft.range(l - 1, r - 1));
    }
}
