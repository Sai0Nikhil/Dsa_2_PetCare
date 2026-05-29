package petcare.trees;

import petcare.models.Pet;

import java.util.ArrayList;
import java.util.List;

public class BST {

    private static class Node {
        Pet pet;
        Node left, right;
        Node(Pet p) { this.pet = p; }
    }

    private Node root;

    public void insert(Pet p) { root = insert(root, p); }
    private Node insert(Node n, Pet p) {
        if (n == null) return new Node(p);
        if (p.getPetId() < n.pet.getPetId())      n.left  = insert(n.left, p);
        else if (p.getPetId() > n.pet.getPetId()) n.right = insert(n.right, p);
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

    public void delete(int petId) { root = delete(root, petId); }
    private Node delete(Node n, int petId) {
        if (n == null) return null;
        if (petId < n.pet.getPetId())      n.left  = delete(n.left, petId);
        else if (petId > n.pet.getPetId()) n.right = delete(n.right, petId);
        else {
            if (n.left == null)  return n.right;
            if (n.right == null) return n.left;
            Node succ = minNode(n.right);
            n.pet = succ.pet;
            n.right = delete(n.right, succ.pet.getPetId());
        }
        return n;
    }
    private Node minNode(Node n) {
        while (n.left != null) n = n.left;
        return n;
    }

    public List<Pet> inOrder()   { List<Pet> l = new ArrayList<>(); inOrder(root, l);   return l; }
    public List<Pet> preOrder()  { List<Pet> l = new ArrayList<>(); preOrder(root, l);  return l; }
    public List<Pet> postOrder() { List<Pet> l = new ArrayList<>(); postOrder(root, l); return l; }

    private void inOrder(Node n, List<Pet> out) {
        if (n == null) return;
        inOrder(n.left, out); out.add(n.pet); inOrder(n.right, out);
    }
    private void preOrder(Node n, List<Pet> out) {
        if (n == null) return;
        out.add(n.pet); preOrder(n.left, out); preOrder(n.right, out);
    }
    private void postOrder(Node n, List<Pet> out) {
        if (n == null) return;
        postOrder(n.left, out); postOrder(n.right, out); out.add(n.pet);
    }

    public int height() { return height(root); }
    private int height(Node n) {
        if (n == null) return 0;
        return 1 + Math.max(height(n.left), height(n.right));
    }
}
