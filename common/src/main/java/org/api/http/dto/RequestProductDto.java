package org.api.http.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record RequestProductDto(
        @NotNull
        @Size(min = 3, max = 50)
        String title,
        String description
) {
}