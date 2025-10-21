package ai.mdotm.assignment.pets.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.List;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class PagedResponse<T> {
    @Schema(description = "Current page number (0-based)", example = "0")
    private int page;
    @Schema(description = "Requested page size", example = "20")
    private int size;
    @Schema(description = "Total number of elements", example = "1")
    private long totalElements;
    @Schema(description = "Total number of pages", example = "1")
    private int totalPages;
    @Schema(description = "Actual content items")
    private List<T> items;
}
