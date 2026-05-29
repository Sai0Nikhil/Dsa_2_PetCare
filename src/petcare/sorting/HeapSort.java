package petcare.sorting;

public class HeapSort {

    public static void sort(int[] a) {
        int n = a.length;
        for (int i = n / 2 - 1; i >= 0; i--) heapify(a, n, i);
        for (int i = n - 1; i > 0; i--) {
            int t = a[0]; a[0] = a[i]; a[i] = t;
            heapify(a, i, 0);
        }
    }

    public static int[] sortIndicesDescending(int[] counts) {
        int n = counts.length;
        int[] idx = new int[n];
        for (int i = 0; i < n; i++) idx[i] = i;
        int[] data = counts.clone();
        for (int i = n / 2 - 1; i >= 0; i--) maxHeapify(data, idx, n, i);
        for (int i = n - 1; i > 0; i--) {
            swap(data, idx, 0, i);
            maxHeapify(data, idx, i, 0);
        }
        for (int i = 0, j = n - 1; i < j; i++, j--) swap(data, idx, i, j);
        return idx;
    }

    private static void heapify(int[] a, int n, int i) {
        int largest = i, l = 2 * i + 1, r = 2 * i + 2;
        if (l < n && a[l] > a[largest]) largest = l;
        if (r < n && a[r] > a[largest]) largest = r;
        if (largest != i) {
            int t = a[i]; a[i] = a[largest]; a[largest] = t;
            heapify(a, n, largest);
        }
    }

    private static void maxHeapify(int[] a, int[] idx, int n, int i) {
        int largest = i, l = 2 * i + 1, r = 2 * i + 2;
        if (l < n && a[l] > a[largest]) largest = l;
        if (r < n && a[r] > a[largest]) largest = r;
        if (largest != i) {
            swap(a, idx, i, largest);
            maxHeapify(a, idx, n, largest);
        }
    }

    private static void swap(int[] a, int[] idx, int i, int j) {
        int t = a[i]; a[i] = a[j]; a[j] = t;
        int s = idx[i]; idx[i] = idx[j]; idx[j] = s;
    }
}
