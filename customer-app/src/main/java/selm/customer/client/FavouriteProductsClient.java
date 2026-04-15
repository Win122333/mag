package selm.customer.client;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import selm.customer.entity.FavouriteProduct;

public interface FavouriteProductsClient {
    Flux<FavouriteProduct> findFavouriteProductProducts();
    Mono<FavouriteProduct> findFavouriteProductByProduct(Integer productId);
    Mono<FavouriteProduct> addProductToFavourite(Integer productId);
    Mono<Void> removeProductFromFavourite(Integer productId);
}
