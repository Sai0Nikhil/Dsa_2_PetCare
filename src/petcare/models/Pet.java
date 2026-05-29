package petcare.models;

public class Pet {
    private int petId;
    private String name;
    private String species;
    private String breed;
    private int age;
    private String ownerName;

    public Pet(int petId, String name, String species, String breed, int age, String ownerName) {
        this.petId = petId;
        this.name = name;
        this.species = species;
        this.breed = breed;
        this.age = age;
        this.ownerName = ownerName;
    }

    public int getPetId() { return petId; }
    public String getName() { return name; }
    public String getSpecies() { return species; }
    public String getBreed() { return breed; }
    public int getAge() { return age; }
    public String getOwnerName() { return ownerName; }

    public void setName(String name) { this.name = name; }
    public void setAge(int age) { this.age = age; }

    @Override
    public String toString() {
        return String.format("Pet[ID=%d, Name=%s, Species=%s, Breed=%s, Age=%d, Owner=%s]",
                petId, name, species, breed, age, ownerName);
    }
}
