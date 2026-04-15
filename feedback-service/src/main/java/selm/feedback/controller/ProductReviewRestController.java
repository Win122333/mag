package selm.feedback.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import selm.feedback.entity.ProductReview;
import selm.feedback.payload.NewProductReviewPayload;
import selm.feedback.service.ProductReviewService;

@RestController
@RequestMapping("/feedback-api/product-reviews")
@RequiredArgsConstructor
public class ProductReviewRestController {
    private final ProductReviewService productReviewService;

    @GetMapping("by-product-id/{productId:\\d+}")
    public Flux<ProductReview> findProductReviewsByProductId(
            @PathVariable("productId") Integer productId
    ) {
        return productReviewService.findProductReviewsByProduct(productId);
    }

    @PostMapping
    public Mono<ResponseEntity<ProductReview>> createProductReview(
            @Valid @RequestBody Mono<NewProductReviewPayload> payload,
            UriComponentsBuilder uriBuilder
    ) {
        return payload
                .flatMap(p -> productReviewService.createProductReview(p.productId(), p.rating(), p.review()))
                .map(productReview -> ResponseEntity.created(uriBuilder.replacePath("/feedback-api/product-reviews/{id}")
                                .build("id", productReview.getProductId()))
                        .body(productReview));
    }
}
