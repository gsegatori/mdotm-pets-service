package ai.mdotm.assignment.pets.application;

import ai.mdotm.assignment.pets.domain.Pet;
import ai.mdotm.assignment.pets.domain.PetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.Instant;

@Service
@RequiredArgsConstructor
public class PetService {
    private final PetRepository repository;
    @Transactional
    public Pet create(Pet toCreate) {
        toCreate.setId(Pet.newId());
        var now = Instant.now();
        toCreate.setCreatedAt(now);
        toCreate.setUpdatedAt(now);
        return repository.save(toCreate);
    }
    @Transactional(readOnly = true)
    public Pet get(String id) { return repository.findById(id).orElseThrow(() -> new PetNotFoundException(id)); }
    @Transactional
    public Pet update(String id, Pet updates) {
        var existing = repository.findById(id).orElseThrow(() -> new PetNotFoundException(id));
        existing.setName(updates.getName());
        existing.setSpecies(updates.getSpecies());
        existing.setAge(updates.getAge());
        existing.setOwnerName(updates.getOwnerName());
        existing.setUpdatedAt(Instant.now());
        return repository.save(existing);
    }
    @Transactional
    public void delete(String id) {
        if (!repository.existsById(id)) throw new PetNotFoundException(id);
        repository.deleteById(id);
    }
    @Transactional(readOnly = true)
    public Page<Pet> list(String species, String ownerContains, Pageable pageable) {
        return repository.findAll(species, ownerContains, pageable);
    }
}
