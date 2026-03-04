package org.api.http.dto;

public record ResponseProductDto(
        Integer id,
        String title,
        String description
) {
}