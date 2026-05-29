package petcare.trees;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

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
