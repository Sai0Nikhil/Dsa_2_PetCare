package petcare.models;

/**
 * Vaccination record for a pet.
 */
public class Vaccination {
    private int vaccinationId;
    private int petId;
    private String vaccineName;
    private int dueDay; // day index for range queries

    public Vaccination(int vaccinationId, int petId, String vaccineName, int dueDay) {
        this.vaccinationId = vaccinationId;
        this.petId = petId;
        this.vaccineName = vaccineName;
        this.dueDay = dueDay;
    }

    public int getVaccinationId() { return vaccinationId; }
    public int getPetId() { return petId; }
    public String getVaccineName() { return vaccineName; }
    public int getDueDay() { return dueDay; }

    @Override
    public String toString() {
        return String.format("Vaccination[ID=%d, PetID=%d, Vaccine=%s, DueDay=%d]",
                vaccinationId, petId, vaccineName, dueDay);
    }
}
