package petcare.sorting;

import petcare.models.Treatment;
import java.util.Comparator;
import java.util.List;

/**
 * Generic merge sort — used to sort pet medical records (e.g., by Pet ID
 * or treatment cost). Time complexity O(n log n), stable.
 */
public class MergeSort {

    public static void sortTreatmentsByCost(List<Treatment> data) {
        mergeSort(data, Comparator.comparingDouble(Treatment::getCost));
    }

    public static void sortTreatmentsById(List<Treatment> data) {
        mergeSort(data, Comparator.comparingInt(Treatment::getTreatmentId));
    }

    public static <T> void mergeSort(List<T> a, Comparator<T> c) {
        if (a.size() < 2) return;
        @SuppressWarnings("unchecked")
        T[] arr = (T[]) a.toArray();
        T[] aux = arr.clone();
        sort(arr, aux, 0, arr.length - 1, c);
        for (int i = 0; i < arr.length; i++) a.set(i, arr[i]);
    }

    private static <T> void sort(T[] a, T[] aux, int l, int r, Comparator<T> c) {
        if (l >= r) return;
        int m = (l + r) >>> 1;
        sort(a, aux, l, m, c);
        sort(a, aux, m + 1, r, c);
        merge(a, aux, l, m, r, c);
    }

    private static <T> void merge(T[] a, T[] aux, int l, int m, int r, Comparator<T> c) {
        for (int i = l; i <= r; i++) aux[i] = a[i];
        int i = l, j = m + 1;
        for (int k = l; k <= r; k++) {
            if (i > m)            a[k] = aux[j++];
            else if (j > r)       a[k] = aux[i++];
            else if (c.compare(aux[i], aux[j]) <= 0) a[k] = aux[i++];
            else                  a[k] = aux[j++];
        }
    }
}
