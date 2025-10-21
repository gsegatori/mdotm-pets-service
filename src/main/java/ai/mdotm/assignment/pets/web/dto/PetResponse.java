package ai.mdotm.assignment.pets.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.time.Instant;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class PetResponse {
    @Schema(description = "Pet UUID", example = "7d9f6c3e-3b75-4d8d-9c9a-8d8a7c2f2c4b")
    private String id;
    private String name;
    private String species;
    private Integer age;
    private String ownerName;
    @Schema(description = "Creation timestamp (UTC)")
    private Instant createdAt;
    @Schema(description = "Last update timestamp (UTC)")
    private Instant updatedAt;
}
