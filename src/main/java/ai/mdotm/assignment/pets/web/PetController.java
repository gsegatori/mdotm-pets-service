package ai.mdotm.assignment.pets.web;

import ai.mdotm.assignment.pets.application.PetService;
import ai.mdotm.assignment.pets.domain.Pet;
import ai.mdotm.assignment.pets.web.dto.PagedResponse;
import ai.mdotm.assignment.pets.web.dto.PetRequest;
import ai.mdotm.assignment.pets.web.dto.PetResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import static ai.mdotm.assignment.pets.web.PetMapper.*;

@Tag(name = "Pets", description = "CRUD operations for Pet resources")
@RestController
@RequestMapping("/pets")
@RequiredArgsConstructor
public class PetController {
    private final PetService service;

    @Operation(summary = "Create a new Pet",
        responses = {
            @ApiResponse(responseCode = "201", description = "Created",
                content = @Content(schema = @Schema(implementation = PetResponse.class))),
            @ApiResponse(responseCode = "400", description = "Validation error", content = @Content)
        })
    @PostMapping
    public ResponseEntity<PetResponse> create(@Valid @RequestBody PetRequest request) {
        var created = service.create(toDomain(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(toResponse(created));
    }

    @Operation(summary = "Get a Pet by id",
        responses = {
            @ApiResponse(responseCode = "200", description = "OK",
                content = @Content(schema = @Schema(implementation = PetResponse.class))),
            @ApiResponse(responseCode = "404", description = "Pet not found", content = @Content)
        })
    @GetMapping("/{id}")
    public PetResponse getById(@Parameter(description = "Pet id (UUID)") @PathVariable String id) {
        return toResponse(service.get(id));
    }

    @Operation(summary = "Update an existing Pet",
        responses = {
            @ApiResponse(responseCode = "200", description = "Updated",
                content = @Content(schema = @Schema(implementation = PetResponse.class))),
            @ApiResponse(responseCode = "400", description = "Validation error", content = @Content),
            @ApiResponse(responseCode = "404", description = "Pet not found", content = @Content)
        })
    @PutMapping("/{id}")
    public PetResponse update(@PathVariable String id, @Valid @RequestBody PetRequest request) {
        return toResponse(service.update(id, toDomain(request)));
    }

    @Operation(summary = "Delete a Pet",
        responses = { @ApiResponse(responseCode = "204", description = "Deleted"),
                      @ApiResponse(responseCode = "404", description = "Pet not found", content = @Content)})
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "List pets with paging, sorting, and filters",
        responses = { @ApiResponse(responseCode = "200", description = "OK",
                        content = @Content(schema = @Schema(implementation = PagedResponse.class))) })
    @GetMapping
    public PagedResponse<PetResponse> list(
            @Parameter(description = "Exact species filter (case-insensitive)") @RequestParam(required = false) String species,
            @Parameter(description = "Substring to match in ownerName (case-insensitive)") @RequestParam(required = false, name = "owner") String ownerContains,
            @Parameter(description = "Page number (0-based)") @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "Page size") @RequestParam(defaultValue = "20") int size,
            @Parameter(description = "Sort by field, e.g. 'name,asc' or 'createdAt,desc'") @RequestParam(defaultValue = "createdAt,desc") String sort
    ) {
        String[] s = sort.split(",", 2);
        Sort springSort = Sort.by(Sort.Direction.fromString(s.length > 1 ? s[1] : "asc"), s[0]);
        Pageable pageable = PageRequest.of(page, size, springSort);
        Page<Pet> result = service.list(species, ownerContains, pageable);
        return PagedResponse.<PetResponse>builder()
                .page(result.getNumber())
                .size(result.getSize())
                .totalElements(result.getTotalElements())
                .totalPages(result.getTotalPages())
                .items(result.getContent().stream().map(PetMapper::toResponse).toList())
                .build();
    }
}
