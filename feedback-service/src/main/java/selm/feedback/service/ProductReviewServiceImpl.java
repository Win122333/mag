package selm.feedback.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import selm.feedback.entity.ProductReview;
import selm.feedback.repository.ProductsReviewRepository;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductReviewServiceImpl implements ProductReviewService {
    private final ProductsReviewRepository productReviewRepository;
    @Override
    public Mono<ProductReview> createProductReview(Integer productId, Integer rating, String review) {
        ProductReview productReview = new ProductReview(UUID.randomUUID(), productId, rating, review);
        return productReviewRepository.saveReview(productReview);
    }

    @Override
    public Flux<ProductReview> findProductReviewsByProduct(Integer productId) {
        return productReviewRepository.findReviewsByProductId(productId);
    }
}
