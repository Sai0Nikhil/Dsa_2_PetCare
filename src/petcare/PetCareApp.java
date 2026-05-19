package petcare;

import petcare.menu.MainMenu;

/**
 * Entry point for the PetCare command-line application.
 * Run from the project root with:
 *     mvn -q exec:java -Dexec.mainClass=petcare.PetCareApp
 * or simply run this class from STS / Eclipse / IntelliJ.
 */
public class PetCareApp {
    public static void main(String[] args) {
        new MainMenu().run();
    }
}
