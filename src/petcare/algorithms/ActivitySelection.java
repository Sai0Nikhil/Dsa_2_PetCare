package petcare.algorithms;

import petcare.models.Appointment;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Greedy Activity Selection — selects the maximum number of non-overlapping
 * veterinary appointments. Sorts by finish time, then picks compatibles.
 */
public class ActivitySelection {

    public static List<Appointment> select(List<Appointment> apps) {
        List<Appointment> sorted = new ArrayList<>(apps);
        sorted.sort(Comparator.comparingInt(Appointment::getEndTime));
        List<Appointment> chosen = new ArrayList<>();
        int lastEnd = Integer.MIN_VALUE;
        for (Appointment a : sorted) {
            if (a.getStartTime() >= lastEnd) {
                chosen.add(a);
                lastEnd = a.getEndTime();
            }
        }
        return chosen;
    }
}
