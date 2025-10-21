package ai.mdotm.assignment.pets.infrastructure.jpa;

import jakarta.persistence.*;
import lombok.*;
import java.time.Instant;

@Entity
@Table(name = "pets")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class PetEntity {
    @Id @Column(length = 36, nullable = false, updatable = false)
    private String id;
    @Column(nullable = false) private String name;
    @Column(nullable = false) private String species;
    @Column(nullable = false) private Integer age;
    private String ownerName;
    @Column(nullable = false) private Instant createdAt;
    @Column(nullable = false) private Instant updatedAt;
}
