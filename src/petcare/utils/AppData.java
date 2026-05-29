package petcare.utils;

import petcare.models.Pet;

import java.util.ArrayList;
import java.util.List;

/**
 * Session-scoped data store.
 *
 * When the user chooses "Load from CSV" at startup, all 45 pet records are
 * stored here and isCsvMode() returns true.  Module menus call
 * getPetsOrGenerate(n) so they automatically use the CSV data in CSV mode
 * or fall back to DataGenerator in raw mode.
 */
public class AppData {

    private static List<Pet> pets = new ArrayList<>();
    private static boolean   csvMode = false;

    /** Called by StartupMenu after a successful CSV load. */
    public static void setPets(List<Pet> list) {
        pets    = new ArrayList<>(list);
        csvMode = true;
    }

    /** Full pet list loaded from CSV (empty in raw mode). */
    public static List<Pet> getPets() { return pets; }

    /** True when the user chose "Load from CSV" at startup. */
    public static boolean isCsvMode() { return csvMode; }

    /**
     * Convenience method used by module menus.
     * Returns the CSV list in CSV mode, otherwise calls DataGenerator.generatePets(n).
     */
    public static List<Pet> getPetsOrGenerate(int n) {
        if (csvMode && !pets.isEmpty()) return pets;
        return DataGenerator.generatePets(n);
    }
}
