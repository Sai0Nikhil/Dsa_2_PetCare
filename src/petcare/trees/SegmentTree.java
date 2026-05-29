package petcare.trees;

public class SegmentTree {

    private final int n;
    private final long[] tree;

    public SegmentTree(int[] data) {
        this.n = data.length;
        this.tree = new long[4 * Math.max(1, n)];
        if (n > 0) build(1, 0, n - 1, data);
    }

    private void build(int node, int l, int r, int[] data) {
        if (l == r) { tree[node] = data[l]; return; }
        int m = (l + r) >>> 1;
        build(node * 2,     l,     m, data);
        build(node * 2 + 1, m + 1, r, data);
        tree[node] = tree[node * 2] + tree[node * 2 + 1];
    }

    public void update(int idx, int value) { update(1, 0, n - 1, idx, value); }
    private void update(int node, int l, int r, int idx, int value) {
        if (l == r) { tree[node] = value; return; }
        int m = (l + r) >>> 1;
        if (idx <= m) update(node * 2,     l,     m, idx, value);
        else          update(node * 2 + 1, m + 1, r, idx, value);
        tree[node] = tree[node * 2] + tree[node * 2 + 1];
    }

    public long rangeSum(int ql, int qr) { return query(1, 0, n - 1, ql, qr); }
    private long query(int node, int l, int r, int ql, int qr) {
        if (qr < l || r < ql) return 0;
        if (ql <= l && r <= qr) return tree[node];
        int m = (l + r) >>> 1;
        return query(node * 2, l, m, ql, qr) + query(node * 2 + 1, m + 1, r, ql, qr);
    }
}
