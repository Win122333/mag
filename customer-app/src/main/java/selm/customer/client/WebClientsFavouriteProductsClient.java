package selm.customer.client;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ProblemDetail;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import selm.customer.entity.FavouriteProduct;
import selm.customer.exception.ClientBadRequestException;
import selm.customer.payload.NewFavouriteProductPayload;

import java.util.List;
import java.util.Map;


@RequiredArgsConstructor
public class WebClientsFavouriteProductsClient implements FavouriteProductsClient {
    private final WebClient webClient;
    @Override
    public Flux<FavouriteProduct> findFavouriteProductProducts() {
        return webClient
                .get()
                .uri("feedback-api/favourite-products")
                .retrieve()
                .bodyToFlux(FavouriteProduct.class);
    }

    @Override
    public Mono<FavouriteProduct> findFavouriteProductByProduct(Integer productId) {
        return webClient
                .get()
                .uri("feedback-api/favourite-products/by-product-id/{productId}", Map.of("productId", productId))
                .retrieve()
                .bodyToMono(FavouriteProduct.class)
                .onErrorComplete(WebClientResponseException.NotFound.class);
    }

    @Override
    public Mono<FavouriteProduct> addProductToFavourite(Integer productId) {
        return webClient
                .post()
                .uri("feedback-api/favourite-products")
                .bodyValue(new NewFavouriteProductPayload(productId))
                .retrieve()
                .bodyToMono(FavouriteProduct.class)
                .onErrorMap(WebClientResponseException.BadRequest.class, ex -> {
                    ProblemDetail problemDetail = ex.getResponseBodyAs(ProblemDetail.class);
                    List<String> errors = null;
                    if (problemDetail != null && problemDetail.getProperties() != null) {
                        errors = (List<String>) problemDetail.getProperties().get("errors");
                    }
                    return new ClientBadRequestException(ex, errors);
                });
    }

    @Override
    public Mono<Void> removeProductFromFavourite(Integer productId) {
        return webClient
                .delete()
                .uri("feedback-api/favourite-products/by-product-id/{productId}", Map.of("productId", productId))
                .retrieve()
                .toBodilessEntity()
                .then();
    }
}
