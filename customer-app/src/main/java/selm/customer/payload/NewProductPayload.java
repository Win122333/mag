package selm.customer.payload;

public record NewProductPayload (
        Integer productId,
        Integer rating,
        String review
) {
}
