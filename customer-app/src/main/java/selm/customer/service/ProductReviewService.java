package selm.customer.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import selm.customer.entity.ProductReview;

public interface ProductReviewService {
    Mono<ProductReview> createProductReview(Integer productId, Integer rating, String review);
    Flux<ProductReview> findProductReviewsByProduct(Integer productId);
}
