package petcare.models;

public class Appointment {
    private int appointmentId;
    private int petId;
    private int startTime;
    private int endTime;
    private String purpose;

    public Appointment(int appointmentId, int petId, int startTime, int endTime, String purpose) {
        this.appointmentId = appointmentId;
        this.petId = petId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.purpose = purpose;
    }

    public int getAppointmentId() { return appointmentId; }
    public int getPetId() { return petId; }
    public int getStartTime() { return startTime; }
    public int getEndTime() { return endTime; }
    public String getPurpose() { return purpose; }

    @Override
    public String toString() {
        return String.format("Appointment[ID=%d, PetID=%d, Time=%d-%d, Purpose=%s]",
                appointmentId, petId, startTime, endTime, purpose);
    }
}
