package ai.mdotm.assignment.pets.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.Optional;

public interface PetRepository {
    Pet save(Pet pet);
    Optional<Pet> findById(String id);
    void deleteById(String id);
    Page<Pet> findAll(String speciesFilter, String ownerNameContains, Pageable pageable);
    boolean existsById(String id);
}
