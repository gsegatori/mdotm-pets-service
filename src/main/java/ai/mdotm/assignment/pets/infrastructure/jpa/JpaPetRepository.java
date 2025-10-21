package ai.mdotm.assignment.pets.infrastructure.jpa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface JpaPetRepository extends JpaRepository<PetEntity, String> {
    @Query("""
        SELECT p FROM PetEntity p
        WHERE (:species IS NULL OR LOWER(p.species) = LOWER(:species))
          AND (:ownerPart IS NULL OR LOWER(p.ownerName) LIKE LOWER(CONCAT('%', :ownerPart, '%')))
    """)
    Page<PetEntity> search(@Param("species") String species,
                           @Param("ownerPart") String ownerPart,
                           Pageable pageable);
}
