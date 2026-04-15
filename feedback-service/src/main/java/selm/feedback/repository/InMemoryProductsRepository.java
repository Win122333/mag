package selm.feedback.repository;

import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import selm.feedback.entity.FavouriteProduct;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@Repository
public class InMemoryProductsRepository implements FavouriteProductsRepository {
    private final List<FavouriteProduct> favouriteProducts = Collections.synchronizedList(new LinkedList<>());
    @Override
    public Mono<FavouriteProduct> save(FavouriteProduct favouriteProduct) {
        favouriteProducts.add(favouriteProduct);
        return Mono.just(favouriteProduct);
    }

    @Override
    public Mono<Void> deleteByProductId(Integer productId) {
        favouriteProducts.removeIf(x -> x.getProductId().equals(productId));
        return Mono.empty();
    }

    @Override
    public Mono<FavouriteProduct> findFavouriteProductByProduct(Integer productId) {
        return Flux.fromIterable(favouriteProducts)
                .filter(favouriteProduct -> favouriteProduct.getProductId().equals(productId))
                .singleOrEmpty();
    }

    @Override
    public Flux<FavouriteProduct> findAll() {
        return Flux.fromIterable(favouriteProducts);
    }
}
