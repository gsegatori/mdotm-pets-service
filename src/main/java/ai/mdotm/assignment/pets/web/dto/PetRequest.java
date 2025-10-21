package ai.mdotm.assignment.pets.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class PetRequest {
    @Schema(description = "Pet name", example = "Luna", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank private String name;
    @Schema(description = "Species (free text)", example = "Dog", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank private String species;
    @Schema(description = "Age >= 0", example = "3", minimum = "0", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull @Min(0) private Integer age;
    @Schema(description = "Owner's name", example = "Giorgio Segatori")
    private String ownerName;
}
