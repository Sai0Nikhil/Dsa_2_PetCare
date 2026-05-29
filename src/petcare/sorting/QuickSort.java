package petcare.sorting;

import java.util.List;
import java.util.Map;

public class QuickSort {

    public static String[] rankByFrequency(Map<String, Integer> freq) {
        int n = freq.size();
        String[] labels = freq.keySet().toArray(new String[0]);
        int[] counts = new int[n];
        for (int i = 0; i < n; i++) counts[i] = freq.get(labels[i]);
        quickSort(counts, labels, 0, n - 1);
        return labels;
    }

    public static void sortDescending(List<Integer> data) {
        int[] a = new int[data.size()];
        for (int i = 0; i < a.length; i++) a[i] = data.get(i);
        quickSort(a, null, 0, a.length - 1);
        for (int i = 0; i < a.length; i++) data.set(i, a[i]);
    }

    private static void quickSort(int[] a, String[] sib, int lo, int hi) {
        if (lo >= hi) return;
        int p = partition(a, sib, lo, hi);
        quickSort(a, sib, lo, p - 1);
        quickSort(a, sib, p + 1, hi);
    }

    private static int partition(int[] a, String[] sib, int lo, int hi) {
        int pivot = a[hi];
        int i = lo - 1;
        for (int j = lo; j < hi; j++) {
            if (a[j] >= pivot) {
                i++;
                swap(a, sib, i, j);
            }
        }
        swap(a, sib, i + 1, hi);
        return i + 1;
    }

    private static void swap(int[] a, String[] sib, int i, int j) {
        int t = a[i]; a[i] = a[j]; a[j] = t;
        if (sib != null) {
            String s = sib[i]; sib[i] = sib[j]; sib[j] = s;
        }
    }
}
