package petcare.utils;

import petcare.models.Pet;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Reads a CSV file of pet records and returns them as a List<Pet>.
 *
 * Expected CSV format (first row is a header, skipped automatically):
 *   petId,name,species,breed,age,ownerName
 *
 * Example:
 *   101,Bruno,Dog,Labrador,3,Aarav
 */
public class CsvLoader {

    /**
     * Loads pets from the given CSV file path.
     * Skips malformed rows silently and prints a warning on file-not-found.
     *
     * @param filePath path to the CSV file, relative to the working directory
     * @return list of Pet objects; empty if the file could not be read
     */
    public static List<Pet> loadPets(String filePath) {
        List<Pet> result = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            br.readLine(); // skip header line
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;

                String[] parts = line.split(",", -1);
                if (parts.length < 6) continue;

                try {
                    int    petId   = Integer.parseInt(parts[0].trim());
                    String name    = parts[1].trim();
                    String species = parts[2].trim();
                    String breed   = parts[3].trim();
                    int    age     = Integer.parseInt(parts[4].trim());
                    String owner   = parts[5].trim();
                    result.add(new Pet(petId, name, species, breed, age, owner));
                } catch (NumberFormatException ignored) {
                    // skip rows with unparseable numbers
                }
            }
        } catch (IOException e) {
            System.out.println("  [!] Could not read CSV file \"" + filePath + "\": " + e.getMessage());
        }

        return result;
    }
}
