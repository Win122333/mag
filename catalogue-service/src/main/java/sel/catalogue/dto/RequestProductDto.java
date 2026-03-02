package sel.catalogue.dto;

import sel.catalogue.Entity.Product;

public record RequestProductDto (
        Integer id,
        String title,
        String description
) {
}