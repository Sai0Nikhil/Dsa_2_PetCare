package petcare.menu;

import petcare.models.Pet;
import petcare.trees.AVLTree;
import petcare.trees.BST;
import petcare.utils.AppData;
import petcare.utils.DataGenerator;
import petcare.utils.DisplayUtils;
import petcare.utils.InputUtils;

import java.util.List;

public class TreesMenu {

    private final BST bst = new BST();
    private final AVLTree avl = new AVLTree();
    private boolean seeded = false;

    public void show() {
        while (true) {
            DisplayUtils.header("M1 - Trees & Balanced Search Structures");
            System.out.println("  1. Seed sample pets into BST and AVL");
            System.out.println("  2. Insert a pet record");
            System.out.println("  3. Search a pet by PetID");
            System.out.println("  4. Delete a pet by PetID");
            System.out.println("  5. Display BST traversals (in/pre/post)");
            System.out.println("  6. Display AVL in-order (sorted history)");
            System.out.println("  7. Compare BST vs AVL heights");
            System.out.println("  0. Back to main menu");
            int c = InputUtils.readIntInRange("Choice: ", 0, 7);
            switch (c) {
                case 1: seed(); break;
                case 2: insertPet(); break;
                case 3: searchPet(); break;
                case 4: deletePet(); break;
                case 5: showBSTTraversals(); break;
                case 6: showAVLInOrder(); break;
                case 7: compareHeights(); break;
                case 0: return;
                default: break;
            }
            InputUtils.pause();
        }
    }

    private void seed() {
        List<Pet> pets = AppData.getPetsOrGenerate(10);
        for (Pet p : pets) { bst.insert(p); avl.insert(p); }
        seeded = true;
        DisplayUtils.ok("Seeded " + pets.size() + " pets into BST and AVL"
                + (AppData.isCsvMode() ? " (from CSV)." : " (generated)."));
        DisplayUtils.printAll(pets);
    }

    private void insertPet() {
        int id   = InputUtils.readInt("PetID: ");
        String n = InputUtils.readString("Name: ");
        String s = InputUtils.readString("Species: ");
        String b = InputUtils.readString("Breed: ");
        int age  = InputUtils.readInt("Age: ");
        String o = InputUtils.readString("Owner: ");
        Pet p = new Pet(id, n, s, b, age, o);
        bst.insert(p); avl.insert(p);
        DisplayUtils.ok("Inserted into both BST and AVL.");
    }

    private void searchPet() {
        int id = InputUtils.readInt("PetID to search: ");
        Pet inBst = bst.search(id);
        Pet inAvl = avl.search(id);
        System.out.println("  BST result : " + (inBst != null ? inBst : "not found"));
        System.out.println("  AVL result : " + (inAvl != null ? inAvl : "not found"));
    }

    private void deletePet() {
        int id = InputUtils.readInt("PetID to delete: ");
        bst.delete(id);
        avl.delete(id);
        DisplayUtils.ok("Deletion attempted in both trees.");
    }

    private void showBSTTraversals() {
        if (!seeded) DisplayUtils.warn("Tip: option 1 seeds sample data.");
        DisplayUtils.subHeader("BST In-Order (sorted by PetID)");
        DisplayUtils.printAll(bst.inOrder());
        DisplayUtils.subHeader("BST Pre-Order");
        DisplayUtils.printAll(bst.preOrder());
        DisplayUtils.subHeader("BST Post-Order");
        DisplayUtils.printAll(bst.postOrder());
    }

    private void showAVLInOrder() {
        DisplayUtils.subHeader("AVL In-Order (balanced medical record DB)");
        DisplayUtils.printAll(avl.inOrder());
    }

    private void compareHeights() {
        System.out.println("  BST height : " + bst.height());
        System.out.println("  AVL height : " + avl.height());
        DisplayUtils.info("AVL keeps the tree balanced for O(log n) operations.");
    }
}
