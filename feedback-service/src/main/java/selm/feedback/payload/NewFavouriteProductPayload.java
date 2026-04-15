package selm.feedback.payload;

import jakarta.validation.constraints.NotNull;

public record NewFavouriteProductPayload(
        @NotNull(message = "{feedback.products.reviews.create.errors.rating_is_null}")
        Integer productId
) {
}
