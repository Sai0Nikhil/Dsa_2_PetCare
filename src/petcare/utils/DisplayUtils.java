package petcare.utils;

import java.util.Collection;

public class DisplayUtils {

    public static void header(String title) {
        StringBuilder bar = new StringBuilder();
        for (int i = 0; i < 60; i++) bar.append('=');
        System.out.println("\n" + bar);
        System.out.println("  " + title);
        System.out.println(bar);
    }

    public static void subHeader(String title) {
        StringBuilder bar = new StringBuilder();
        for (int i = 0; i < 60; i++) bar.append('-');
        System.out.println("\n" + bar);
        System.out.println("  " + title);
        System.out.println(bar);
    }

    public static <T> void printAll(Collection<T> items) {
        if (items == null || items.isEmpty()) {
            System.out.println("  (no records)");
            return;
        }
        int i = 1;
        for (T t : items) {
            System.out.printf("  %2d. %s%n", i++, t);
        }
    }

    public static void printArray(int[] arr) {
        StringBuilder sb = new StringBuilder("  [");
        for (int i = 0; i < arr.length; i++) {
            sb.append(arr[i]);
            if (i < arr.length - 1) sb.append(", ");
        }
        sb.append("]");
        System.out.println(sb);
    }

    public static void info(String msg) { System.out.println("  > " + msg); }
    public static void ok(String msg)   { System.out.println("  [OK] " + msg); }
    public static void warn(String msg) { System.out.println("  [!]  " + msg); }
}
