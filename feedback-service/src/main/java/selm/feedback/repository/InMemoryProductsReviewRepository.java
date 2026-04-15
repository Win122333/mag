package selm.feedback.repository;

import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import selm.feedback.entity.ProductReview;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@Repository
public class InMemoryProductsReviewRepository implements ProductsReviewRepository {
    private final List<ProductReview> reviews = Collections.synchronizedList(new LinkedList<>());
    @Override
    public Flux<ProductReview> findReviewsByProductId(Integer productId) {
        return Flux.fromIterable(reviews)
                .filter(product -> product.getProductId().equals(productId));
    }

    @Override
    public Mono<ProductReview> saveReview(ProductReview productReview) {
        reviews.add(productReview);
        return Mono.just(productReview);
    }
}
