package ai.mdotm.assignment.pets.application;

public class PetNotFoundException extends RuntimeException {
    public PetNotFoundException(String id) { super("Pet not found: " + id); }
}
