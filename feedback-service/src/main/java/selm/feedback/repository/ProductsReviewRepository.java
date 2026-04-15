package selm.feedback.repository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import selm.feedback.entity.ProductReview;

public interface ProductsReviewRepository {
    Flux<ProductReview> findReviewsByProductId(Integer productId);

    Mono<ProductReview> saveReview(ProductReview productReview);
}
