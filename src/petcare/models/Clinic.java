package petcare.models;

public class Clinic {
    private int clinicId;
    private String name;
    private String location;

    public Clinic(int clinicId, String name, String location) {
        this.clinicId = clinicId;
        this.name = name;
        this.location = location;
    }

    public int getClinicId() { return clinicId; }
    public String getName() { return name; }
    public String getLocation() { return location; }

    @Override
    public String toString() {
        return String.format("Clinic[ID=%d, Name=%s, Location=%s]", clinicId, name, location);
    }
}
