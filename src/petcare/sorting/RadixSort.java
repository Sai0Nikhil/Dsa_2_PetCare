package petcare.sorting;

import java.util.Arrays;

/**
 * LSD Radix sort + Counting sort — perfect for sorting pet identification
 * numbers, which fall within a small integer range.
 */
public class RadixSort {

    public static void countingSort(int[] a) {
        if (a.length == 0) return;
        int max = a[0], min = a[0];
        for (int v : a) { if (v > max) max = v; if (v < min) min = v; }
        int range = max - min + 1;
        int[] count = new int[range];
        for (int v : a) count[v - min]++;
        int idx = 0;
        for (int i = 0; i < range; i++) {
            while (count[i]-- > 0) a[idx++] = i + min;
        }
    }

    public static void radixSort(int[] a) {
        if (a.length == 0) return;
        int max = Arrays.stream(a).max().getAsInt();
        for (int exp = 1; max / exp > 0; exp *= 10) countingSortByDigit(a, exp);
    }

    private static void countingSortByDigit(int[] a, int exp) {
        int n = a.length;
        int[] output = new int[n];
        int[] count = new int[10];
        for (int v : a) count[(v / exp) % 10]++;
        for (int i = 1; i < 10; i++) count[i] += count[i - 1];
        for (int i = n - 1; i >= 0; i--) {
            int d = (a[i] / exp) % 10;
            output[--count[d]] = a[i];
        }
        System.arraycopy(output, 0, a, 0, n);
    }
}
