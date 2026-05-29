package petcare.trees;

import java.util.ArrayList;
import java.util.List;

public class BTree {

    private final int t;
    private Node root;

    private static class Node {
        int n = 0;
        boolean leaf;
        int[] keys;
        Node[] children;
        Node(int t, boolean leaf) {
            this.leaf = leaf;
            this.keys = new int[2 * t - 1];
            this.children = new Node[2 * t];
        }
    }

    public BTree(int t) {
        this.t = Math.max(2, t);
        this.root = new Node(this.t, true);
    }

    public boolean search(int k) { return search(root, k); }
    private boolean search(Node x, int k) {
        int i = 0;
        while (i < x.n && k > x.keys[i]) i++;
        if (i < x.n && k == x.keys[i]) return true;
        if (x.leaf) return false;
        return search(x.children[i], k);
    }

    public void insert(int k) {
        Node r = root;
        if (r.n == 2 * t - 1) {
            Node s = new Node(t, false);
            s.children[0] = r;
            splitChild(s, 0);
            insertNonFull(s, k);
            root = s;
        } else {
            insertNonFull(r, k);
        }
    }

    private void splitChild(Node x, int i) {
        Node y = x.children[i];
        Node z = new Node(t, y.leaf);
        z.n = t - 1;
        for (int j = 0; j < t - 1; j++) z.keys[j] = y.keys[j + t];
        if (!y.leaf) {
            for (int j = 0; j < t; j++) z.children[j] = y.children[j + t];
        }
        y.n = t - 1;
        for (int j = x.n; j >= i + 1; j--) x.children[j + 1] = x.children[j];
        x.children[i + 1] = z;
        for (int j = x.n - 1; j >= i; j--) x.keys[j + 1] = x.keys[j];
        x.keys[i] = y.keys[t - 1];
        x.n++;
    }

    private void insertNonFull(Node x, int k) {
        int i = x.n - 1;
        if (x.leaf) {
            while (i >= 0 && k < x.keys[i]) {
                x.keys[i + 1] = x.keys[i];
                i--;
            }
            x.keys[i + 1] = k;
            x.n++;
        } else {
            while (i >= 0 && k < x.keys[i]) i--;
            i++;
            if (x.children[i].n == 2 * t - 1) {
                splitChild(x, i);
                if (k > x.keys[i]) i++;
            }
            insertNonFull(x.children[i], k);
        }
    }

    public List<Integer> levelOrder() {
        List<Integer> out = new ArrayList<>();
        traverse(root, out);
        return out;
    }
    private void traverse(Node x, List<Integer> out) {
        int i;
        for (i = 0; i < x.n; i++) {
            if (!x.leaf) traverse(x.children[i], out);
            out.add(x.keys[i]);
        }
        if (!x.leaf) traverse(x.children[i], out);
    }
}
