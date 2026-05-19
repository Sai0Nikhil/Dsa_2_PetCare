package petcare.trees;

import petcare.models.Pet;
import java.util.ArrayList;
import java.util.List;

/**
 * Self-balancing AVL tree for pet medical records keyed on PetID.
 */
public class AVLTree {

    private static class Node {
        Pet pet;
        Node left, right;
        int height = 1;
        Node(Pet p) { this.pet = p; }
    }

    private Node root;

    private int h(Node n) { return n == null ? 0 : n.height; }
    private int bf(Node n) { return n == null ? 0 : h(n.left) - h(n.right); }
    private void update(Node n) { n.height = 1 + Math.max(h(n.left), h(n.right)); }

    private Node rotateRight(Node y) {
        Node x = y.left, t2 = x.right;
        x.right = y; y.left = t2;
        update(y); update(x);
        return x;
    }
    private Node rotateLeft(Node x) {
        Node y = x.right, t2 = y.left;
        y.left = x; x.right = t2;
        update(x); update(y);
        return y;
    }

    public void insert(Pet p) { root = insert(root, p); }
    private Node insert(Node n, Pet p) {
        if (n == null) return new Node(p);
        if (p.getPetId() < n.pet.getPetId())      n.left  = insert(n.left, p);
        else if (p.getPetId() > n.pet.getPetId()) n.right = insert(n.right, p);
        else return n;

        update(n);
        int b = bf(n);

        if (b > 1 && p.getPetId() < n.left.pet.getPetId())   return rotateRight(n);
        if (b < -1 && p.getPetId() > n.right.pet.getPetId()) return rotateLeft(n);
        if (b > 1 && p.getPetId() > n.left.pet.getPetId()) {
            n.left = rotateLeft(n.left);
            return rotateRight(n);
        }
        if (b < -1 && p.getPetId() < n.right.pet.getPetId()) {
            n.right = rotateRight(n.right);
            return rotateLeft(n);
        }
        return n;
    }

    public void delete(int petId) { root = delete(root, petId); }
    private Node delete(Node n, int petId) {
        if (n == null) return null;
        if (petId < n.pet.getPetId())      n.left  = delete(n.left, petId);
        else if (petId > n.pet.getPetId()) n.right = delete(n.right, petId);
        else {
            if (n.left == null || n.right == null) {
                n = (n.left != null) ? n.left : n.right;
                if (n == null) return null;
            } else {
                Node s = n.right;
                while (s.left != null) s = s.left;
                n.pet = s.pet;
                n.right = delete(n.right, s.pet.getPetId());
            }
        }
        update(n);
        int b = bf(n);
        if (b > 1  && bf(n.left)  >= 0) return rotateRight(n);
        if (b > 1  && bf(n.left)  <  0) { n.left = rotateLeft(n.left); return rotateRight(n); }
        if (b < -1 && bf(n.right) <= 0) return rotateLeft(n);
        if (b < -1 && bf(n.right) >  0) { n.right = rotateRight(n.right); return rotateLeft(n); }
        return n;
    }

    public Pet search(int petId) {
        Node cur = root;
        while (cur != null) {
            if (petId == cur.pet.getPetId()) return cur.pet;
            cur = (petId < cur.pet.getPetId()) ? cur.left : cur.right;
        }
        return null;
    }

    public List<Pet> inOrder() { List<Pet> l = new ArrayList<>(); inOrder(root, l); return l; }
    private void inOrder(Node n, List<Pet> out) {
        if (n == null) return;
        inOrder(n.left, out); out.add(n.pet); inOrder(n.right, out);
    }

    public int height() { return h(root); }
}
