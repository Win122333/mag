package sel.catalogue.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import sel.catalogue.Entity.Product;

public record RequestProductDto (
        @NotNull
        @Size(min = 3, max = 50)
        String title,
        String description
) {
}