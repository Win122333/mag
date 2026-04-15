package selm.feedback.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import selm.feedback.entity.FavouriteProduct;

public interface FavouriteProductsService {
    Mono<FavouriteProduct> addProductToFavourite(Integer productId);
    Mono<Void> removeProductFromFavourite(Integer productId);
    Mono<FavouriteProduct> findFavouriteProductByProduct(Integer productId);
    Flux<FavouriteProduct> findFavouriteProductProducts();
}
