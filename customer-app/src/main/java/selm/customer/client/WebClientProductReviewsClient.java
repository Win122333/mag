package selm.customer.client;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ProblemDetail;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import selm.customer.entity.ProductReview;
import selm.customer.exception.ClientBadRequestException;
import selm.customer.payload.NewProductPayload;
import selm.customer.payload.NewProductReviewPayload;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public class WebClientProductReviewsClient implements ProductReviewsClient {
    private final WebClient webClient;
    @Override
    public Flux<ProductReview> findProductReviewsByProductId(Integer productId) {
        return webClient
                .get()
                .uri("feedback-api/product-reviews/by-product-id/{productId}", Map.of("productId", productId))
                .retrieve()
                .bodyToFlux(ProductReview.class);
    }

    @Override
    public Mono<ProductReview> createProductReview(Integer productId, Integer rating, String review) {
        return webClient
                .post()
                .uri("feedback-api/product-reviews")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(new NewProductReviewPayload(productId, rating, review))
                .retrieve()
                .bodyToMono(ProductReview.class)
                .onErrorMap(WebClientResponseException.BadRequest.class, ex -> {
                    ProblemDetail problemDetail = ex.getResponseBodyAs(ProblemDetail.class);
                    List<String> errors = null;
                    if (problemDetail != null && problemDetail.getProperties() != null) {
                        errors = (List<String>) problemDetail.getProperties().get("errors");
                    }
                    return new ClientBadRequestException(ex, errors);
                });
    }
}
