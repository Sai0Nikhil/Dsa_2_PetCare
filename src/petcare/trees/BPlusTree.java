package petcare.trees;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * Simplified B+ Tree style range index. For brevity we back it with a sorted
 * map (TreeMap), exposing the operations that matter for the project:
 * insertion of (dueDay -> vaccinationLabel) pairs and range queries that
 * return all entries within an inclusive date range.
 */
public class BPlusTree {

    private final TreeMap<Integer, List<String>> index = new TreeMap<>();

    public void insert(int dueDay, String label) {
        index.computeIfAbsent(dueDay, k -> new ArrayList<>()).add(label);
    }

    public List<String> rangeQuery(int fromDay, int toDay) {
        List<String> out = new ArrayList<>();
        index.subMap(fromDay, true, toDay, true)
                .forEach((day, lbls) -> {
                    for (String l : lbls) out.add("Day " + day + " -> " + l);
                });
        return out;
    }

    public int size() {
        return index.values().stream().mapToInt(List::size).sum();
    }
}
