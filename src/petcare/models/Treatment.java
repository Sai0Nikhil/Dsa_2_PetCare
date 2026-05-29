package petcare.models;

public class Treatment {
    private int treatmentId;
    private int petId;
    private String diagnosis;
    private String medication;
    private double cost;
    private int dayIndex;

    public Treatment(int treatmentId, int petId, String diagnosis, String medication, double cost, int dayIndex) {
        this.treatmentId = treatmentId;
        this.petId = petId;
        this.diagnosis = diagnosis;
        this.medication = medication;
        this.cost = cost;
        this.dayIndex = dayIndex;
    }

    public int getTreatmentId() { return treatmentId; }
    public int getPetId() { return petId; }
    public String getDiagnosis() { return diagnosis; }
    public String getMedication() { return medication; }
    public double getCost() { return cost; }
    public int getDayIndex() { return dayIndex; }

    @Override
    public String toString() {
        return String.format("Treatment[ID=%d, PetID=%d, Diagnosis=%s, Med=%s, Cost=%.2f, Day=%d]",
                treatmentId, petId, diagnosis, medication, cost, dayIndex);
    }
}
