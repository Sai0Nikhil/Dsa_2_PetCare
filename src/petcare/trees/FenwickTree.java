package petcare.trees;

public class FenwickTree {

    private final int n;
    private final long[] bit;

    public FenwickTree(int size) {
        this.n = size;
        this.bit = new long[n + 1];
    }

    public FenwickTree(int[] data) {
        this(data.length);
        for (int i = 0; i < data.length; i++) update(i, data[i]);
    }

    public void update(int i, long delta) {
        for (++i; i <= n; i += i & -i) bit[i] += delta;
    }

    public long prefix(int i) {
        long s = 0;
        for (++i; i > 0; i -= i & -i) s += bit[i];
        return s;
    }

    public long range(int l, int r) {
        if (l == 0) return prefix(r);
        return prefix(r) - prefix(l - 1);
    }
}
