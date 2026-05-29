package petcare.algorithms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LIS {

    public static class Result {
        public final int length;
        public final List<Integer> sequence;
        public Result(int length, List<Integer> sequence) {
            this.length = length; this.sequence = sequence;
        }
    }

    public static Result compute(int[] values) {
        int n = values.length;
        int[] tails = new int[n];
        int[] tailIdx = new int[n];
        int[] prev = new int[n];
        int len = 0;
        for (int i = 0; i < n; i++) {
            int lo = 0, hi = len;
            while (lo < hi) {
                int m = (lo + hi) >>> 1;
                if (tails[m] < values[i]) lo = m + 1;
                else hi = m;
            }
            tails[lo] = values[i];
            tailIdx[lo] = i;
            prev[i] = (lo > 0) ? tailIdx[lo - 1] : -1;
            if (lo == len) len++;
        }
        List<Integer> seq = new ArrayList<>();
        for (int i = (len > 0 ? tailIdx[len - 1] : -1); i >= 0; i = prev[i]) seq.add(values[i]);
        Collections.reverse(seq);
        return new Result(len, seq);
    }
}
