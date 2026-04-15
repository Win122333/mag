package selm.customer.repository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import selm.customer.entity.FavouriteProduct;

public interface FavouriteProductsRepository {
    Mono<FavouriteProduct> save(FavouriteProduct favouriteProduct);
    Mono<Void> deleteByProductId(Integer productId);
    Mono<FavouriteProduct> findFavouriteProductByProduct(Integer productId);

    Flux<FavouriteProduct> findAll();
}
