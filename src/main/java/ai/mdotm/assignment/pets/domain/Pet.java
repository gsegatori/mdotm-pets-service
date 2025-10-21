package ai.mdotm.assignment.pets.domain;

import lombok.*;
import java.time.Instant;
import java.util.UUID;

@Getter @Setter @Builder
@NoArgsConstructor @AllArgsConstructor
public class Pet {
    private String id;
    private String name;
    private String species;
    private Integer age;
    private String ownerName;
    private Instant createdAt;
    private Instant updatedAt;
    public static String newId() { return UUID.randomUUID().toString(); }
}
