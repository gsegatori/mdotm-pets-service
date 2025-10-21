package ai.mdotm.assignment.pets.infrastructure.jpa;

import ai.mdotm.assignment.pets.domain.Pet;
import ai.mdotm.assignment.pets.domain.PetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class PetRepositoryJpaAdapter implements PetRepository {

    private final JpaPetRepository jpa;

    private static Pet toDomain(PetEntity e) {
        if (e == null) return null;
        return Pet.builder()
                .id(e.getId())
                .name(e.getName())
                .species(e.getSpecies())
                .age(e.getAge())
                .ownerName(e.getOwnerName())
                .createdAt(e.getCreatedAt())
                .updatedAt(e.getUpdatedAt())
                .build();
    }

    private static PetEntity toEntity(Pet d) {
        if (d == null) return null;
        return PetEntity.builder()
                .id(d.getId())
                .name(d.getName())
                .species(d.getSpecies())
                .age(d.getAge())
                .ownerName(d.getOwnerName())
                .createdAt(d.getCreatedAt())
                .updatedAt(d.getUpdatedAt())
                .build();
    }

    @Override public Pet save(Pet pet) { return toDomain(jpa.save(toEntity(pet))); }
    @Override public Optional<Pet> findById(String id) { return jpa.findById(id).map(PetRepositoryJpaAdapter::toDomain); }
    @Override public void deleteById(String id) { jpa.deleteById(id); }
    @Override public Page<Pet> findAll(String speciesFilter, String ownerNameContains, Pageable pageable) {
        String species = (speciesFilter == null || speciesFilter.isBlank()) ? null : speciesFilter;
        String ownerPart = (ownerNameContains == null || ownerNameContains.isBlank()) ? null : ownerNameContains;
        return jpa.search(species, ownerPart, pageable).map(PetRepositoryJpaAdapter::toDomain);
    }
    @Override public boolean existsById(String id) { return jpa.existsById(id); }
}
