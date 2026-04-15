package selm.feedback.repository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import selm.feedback.entity.FavouriteProduct;

public interface FavouriteProductsRepository {
    Mono<FavouriteProduct> save(FavouriteProduct favouriteProduct);
    Mono<Void> deleteByProductId(Integer productId);
    Mono<FavouriteProduct> findFavouriteProductByProduct(Integer productId);

    Flux<FavouriteProduct> findAll();
}
