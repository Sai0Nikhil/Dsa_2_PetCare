package petcare.utils;

import petcare.models.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Generates demo data so all DSA modules can be tried without manual entry.
 */
public class DataGenerator {

    private static final String[] NAMES   = {"Bruno","Luna","Max","Bella","Charlie","Coco","Rocky","Daisy","Milo","Lucy"};
    private static final String[] SPECIES = {"Dog","Cat","Rabbit","Parrot","Hamster"};
    private static final String[] BREEDS  = {"Labrador","Persian","Beagle","Siamese","Pug","Mixed"};
    private static final String[] OWNERS  = {"Aarav","Diya","Ishaan","Meera","Rohan","Kavya","Vivaan","Anaya"};
    private static final String[] DIAG    = {"Fever","Fracture","Skin Allergy","Dental","Vaccination","Checkup"};
    private static final String[] MEDS    = {"Antibiotic","Painkiller","Vitamin","Antiseptic","Vaccine"};
    private static final String[] VACC    = {"Rabies","Distemper","Parvovirus","Hepatitis","Leptospirosis"};

    public static List<Pet> generatePets(int n) {
        List<Pet> pets = new ArrayList<>();
        Random r = new Random(42);
        for (int i = 1; i <= n; i++) {
            pets.add(new Pet(
                    100 + i,
                    pick(NAMES, r),
                    pick(SPECIES, r),
                    pick(BREEDS, r),
                    1 + r.nextInt(15),
                    pick(OWNERS, r)));
        }
        return pets;
    }

    public static List<Treatment> generateTreatments(int n) {
        List<Treatment> ts = new ArrayList<>();
        Random r = new Random(7);
        for (int i = 1; i <= n; i++) {
            ts.add(new Treatment(
                    1000 + i,
                    100 + 1 + r.nextInt(10),
                    pick(DIAG, r),
                    pick(MEDS, r),
                    200 + r.nextInt(1800),
                    1 + r.nextInt(30)));
        }
        return ts;
    }

    public static List<Appointment> generateAppointments(int n) {
        List<Appointment> as = new ArrayList<>();
        Random r = new Random(13);
        for (int i = 1; i <= n; i++) {
            int start = 8 + r.nextInt(10);
            int end = start + 1 + r.nextInt(3);
            as.add(new Appointment(
                    9000 + i,
                    100 + 1 + r.nextInt(10),
                    start, end,
                    pick(DIAG, r)));
        }
        return as;
    }

    public static List<Vaccination> generateVaccinations(int n) {
        List<Vaccination> vs = new ArrayList<>();
        Random r = new Random(21);
        for (int i = 1; i <= n; i++) {
            vs.add(new Vaccination(
                    5000 + i,
                    100 + 1 + r.nextInt(10),
                    pick(VACC, r),
                    1 + r.nextInt(60)));
        }
        return vs;
    }

    public static List<Clinic> generateClinics() {
        List<Clinic> cs = new ArrayList<>();
        cs.add(new Clinic(0, "MainVet",   "Chennai"));
        cs.add(new Clinic(1, "PawCare",   "Bangalore"));
        cs.add(new Clinic(2, "AnimalAid", "Hyderabad"));
        cs.add(new Clinic(3, "PetHaven",  "Mumbai"));
        cs.add(new Clinic(4, "VetPlus",   "Delhi"));
        cs.add(new Clinic(5, "HappyTails","Pune"));
        return cs;
    }

    private static String pick(String[] a, Random r) {
        return a[r.nextInt(a.length)];
    }
}
