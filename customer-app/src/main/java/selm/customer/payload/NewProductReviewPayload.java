package selm.customer.payload;

public record NewProductReviewPayload (
        Integer rating,
        String review
) {
}
