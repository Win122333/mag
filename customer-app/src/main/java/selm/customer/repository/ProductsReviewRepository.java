package selm.customer.repository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import selm.customer.entity.ProductReview;

public interface ProductsReviewRepository {
    Flux<ProductReview> findReviewsByProductId(Integer productId);

    Mono<ProductReview> saveReview(ProductReview productReview);
}
