package selm.feedback.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import selm.feedback.entity.FavouriteProduct;
import selm.feedback.payload.NewFavouriteProductPayload;
import selm.feedback.service.FavouriteProductsService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/feedback-api/favourite-products")
public class FavouriteProductsRestController {
    private final FavouriteProductsService favouriteProductsService;

    @GetMapping
    public Flux<FavouriteProduct> findFavouriteProducts() {
        return favouriteProductsService.findFavouriteProductProducts();
    }

    @GetMapping("by-product-id/{productId:\\d+}")
    public Mono<FavouriteProduct> findFavouriteProductByProductId (
            @PathVariable("productId") Integer productId
    ) {
        return favouriteProductsService.findFavouriteProductByProduct(productId);
    }

    @PostMapping
    public Mono<ResponseEntity<FavouriteProduct>> addProductToFavourite (
            @Valid @RequestBody Mono<NewFavouriteProductPayload> payload,
            UriComponentsBuilder uriBuilder
    ) {
        return payload.
                flatMap(p -> favouriteProductsService.addProductToFavourite(p.productId()))
                .map(favouriteProduct -> ResponseEntity.created(uriBuilder.replacePath("/feedback-api/favourite-products/{id}")
                                .build("id", favouriteProduct.getProductId()))
                        .body(favouriteProduct));
    }

    @DeleteMapping("/by-product-id/{productId:\\\\d+}")
    public Mono<ResponseEntity<Void>> removeProductFromFavourite (
            @PathVariable("productId") Integer productId
    ) {
        return favouriteProductsService.removeProductFromFavourite(productId)
                .then(Mono.just(ResponseEntity.noContent().build()));
    }
}
