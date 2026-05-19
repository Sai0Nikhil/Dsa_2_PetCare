package petcare.menu;

import petcare.sorting.HeapSort;
import petcare.sorting.MergeSort;
import petcare.sorting.QuickSort;
import petcare.sorting.RadixSort;
import petcare.models.Pet;
import petcare.models.Treatment;
import petcare.utils.DataGenerator;
import petcare.utils.DisplayUtils;
import petcare.utils.InputUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Module M5 - Advanced Sorting & Ranking.
 */
public class SortingMenu {

    public void show() {
        while (true) {
            DisplayUtils.header("M5 - Advanced Sorting & Data Ranking");
            System.out.println("  1. Merge Sort: sort medical records by cost");
            System.out.println("  2. Quick Sort: rank treatments by frequency");
            System.out.println("  3. Heap Sort: top-K most common pet diseases");
            System.out.println("  4. Radix/Counting Sort: sort pet IDs");
            System.out.println("  0. Back to main menu");
            int c = InputUtils.readIntInRange("Choice: ", 0, 4);
            switch (c) {
                case 1: mergeDemo(); break;
                case 2: quickDemo(); break;
                case 3: heapDemo(); break;
                case 4: radixDemo(); break;
                case 0: return;
                default: break;
            }
            InputUtils.pause();
        }
    }

    private void mergeDemo() {
        List<Treatment> ts = DataGenerator.generateTreatments(10);
        DisplayUtils.subHeader("Before sort");
        DisplayUtils.printAll(ts);
        MergeSort.sortTreatmentsByCost(ts);
        DisplayUtils.subHeader("After Merge Sort (by cost ascending)");
        DisplayUtils.printAll(ts);
    }

    private void quickDemo() {
        List<Treatment> ts = DataGenerator.generateTreatments(20);
        Map<String, Integer> freq = new HashMap<>();
        for (Treatment t : ts) freq.merge(t.getDiagnosis(), 1, Integer::sum);
        String[] ranked = QuickSort.rankByFrequency(freq);
        DisplayUtils.subHeader("Treatments ranked by frequency (descending)");
        for (String s : ranked) System.out.printf("  %-15s : %d%n", s, freq.get(s));
    }

    private void heapDemo() {
        List<Treatment> ts = DataGenerator.generateTreatments(40);
        Map<String, Integer> freq = new HashMap<>();
        for (Treatment t : ts) freq.merge(t.getDiagnosis(), 1, Integer::sum);
        String[] labels = freq.keySet().toArray(new String[0]);
        int[] counts = new int[labels.length];
        for (int i = 0; i < labels.length; i++) counts[i] = freq.get(labels[i]);
        int[] order = HeapSort.sortIndicesDescending(counts);
        int k = Math.min(3, order.length);
        DisplayUtils.subHeader("Top-" + k + " most common diseases (Heap Sort)");
        for (int i = 0; i < k; i++) {
            int idx = order[i];
            System.out.printf("  %d. %-15s (%d cases)%n", i + 1, labels[idx], counts[idx]);
        }
    }

    private void radixDemo() {
        List<Pet> pets = DataGenerator.generatePets(15);
        List<Integer> idList = new ArrayList<>();
        for (Pet p : pets) idList.add(p.getPetId());
        idList.add(207); idList.add(150); idList.add(101); idList.add(199);
        int[] ids = idList.stream().mapToInt(Integer::intValue).toArray();
        DisplayUtils.subHeader("Pet IDs before sort");
        DisplayUtils.printArray(ids);
        int[] copyR = ids.clone();
        RadixSort.radixSort(copyR);
        DisplayUtils.subHeader("After Radix Sort");
        DisplayUtils.printArray(copyR);
        int[] copyC = ids.clone();
        RadixSort.countingSort(copyC);
        DisplayUtils.subHeader("After Counting Sort");
        DisplayUtils.printArray(copyC);
    }
}
