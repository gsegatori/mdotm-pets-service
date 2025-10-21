package ai.mdotm.assignment.pets.web;

import ai.mdotm.assignment.pets.domain.Pet;
import ai.mdotm.assignment.pets.web.dto.PetRequest;
import ai.mdotm.assignment.pets.web.dto.PetResponse;

public class PetMapper {
    public static Pet toDomain(PetRequest req) {
        return Pet.builder()
                .name(req.getName())
                .species(req.getSpecies())
                .age(req.getAge())
                .ownerName(req.getOwnerName())
                .build();
    }
    public static PetResponse toResponse(Pet pet) {
        return PetResponse.builder()
                .id(pet.getId())
                .name(pet.getName())
                .species(pet.getSpecies())
                .age(pet.getAge())
                .ownerName(pet.getOwnerName())
                .createdAt(pet.getCreatedAt())
                .updatedAt(pet.getUpdatedAt())
                .build();
    }
}
