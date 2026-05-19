package petcare.menu;

import petcare.utils.DisplayUtils;
import petcare.utils.InputUtils;

/**
 * Top-level CLI router that dispatches to the six module sub-menus.
 */
public class MainMenu {

    public void run() {
        DisplayUtils.header("PetCare - Smart Veterinary Clinic & Pet Health Management");
        System.out.println("  Welcome! This system demonstrates the six DSA modules through");
        System.out.println("  realistic clinic-management scenarios.");

        while (true) {
            DisplayUtils.subHeader("MAIN MENU");
            System.out.println("  1. M1 - Trees & Balanced Search Structures");
            System.out.println("  2. M2 - Multiway Trees & Range Queries");
            System.out.println("  3. M3 - Graph Algorithms (Healthcare Network)");
            System.out.println("  4. M4 - Shortest Path Optimisation");
            System.out.println("  5. M5 - Advanced Sorting & Data Ranking");
            System.out.println("  6. M6 - Greedy Algorithms & Dynamic Programming");
            System.out.println("  0. Exit");
            int c = InputUtils.readIntInRange("Choice: ", 0, 6);
            switch (c) {
                case 1: new TreesMenu().show(); break;
                case 2: new MultiwayMenu().show(); break;
                case 3: new GraphMenu().show(); break;
                case 4: new ShortestPathMenu().show(); break;
                case 5: new SortingMenu().show(); break;
                case 6: new GreedyDPMenu().show(); break;
                case 0:
                    DisplayUtils.ok("Goodbye! Thank you for using PetCare.");
                    InputUtils.closeScanner();
                    return;
                default: break;
            }
        }
    }
}
