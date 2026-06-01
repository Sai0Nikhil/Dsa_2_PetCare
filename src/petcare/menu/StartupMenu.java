package petcare.menu;

import petcare.models.Pet;
import petcare.utils.AppData;
import petcare.utils.CsvLoader;
import petcare.utils.DisplayUtils;
import petcare.utils.InputUtils;

import java.util.List;

/**
 * Shown once at application start, before the main module menu.
 * Lets the user choose between loading the pre-defined CSV dataset
 * or using on-the-fly generated data (raw mode).
 */
public class StartupMenu {

    /** Path to the pet CSV file, relative to the project root (run directory). */
    private static final String CSV_PATH = "data/pets.csv";

    public void run() {
        DisplayUtils.header("PetCare - Smart Veterinary Clinic & Pet Health Management");
//        System.out.println("  Welcome! This system demonstrates all six DSA modules through");
//        System.out.println("  realistic veterinary-clinic management scenarios.");

        DisplayUtils.subHeader("DATA INITIALISATION");
        System.out.println("  Choose how pet data should be loaded for this session:\n");
        System.out.println("  1. Load from CSV   -- " + CSV_PATH + " (45 pre-defined pet records)");
        System.out.println("  2. Continue as Raw -- generate sample data on-the-fly per module");
        System.out.println();

        int choice = InputUtils.readIntInRange("Choice [1-2]: ", 1, 2);

        if (choice == 1) {
            List<Pet> pets = CsvLoader.loadPets(CSV_PATH);
            if (pets.isEmpty()) {
                DisplayUtils.warn("CSV was empty or could not be read -- falling back to raw mode.");
            } else {
                AppData.setPets(pets);
                DisplayUtils.ok("Loaded " + pets.size() + " pets from \"" + CSV_PATH + "\".");
                System.out.println();
                // Show a quick preview of the first 5 records
                DisplayUtils.subHeader("Preview (first 5 records)");
                int preview = Math.min(5, pets.size());
                for (int i = 0; i < preview; i++) {
                    System.out.printf("  %2d. %s%n", i + 1, pets.get(i));
                }
                System.out.println("  ... and " + (pets.size() - preview) + " more.");
            }
        } else {
            DisplayUtils.ok("Raw mode selected. Each module will generate its own sample data.");
        }

        InputUtils.pause();
    }
}
