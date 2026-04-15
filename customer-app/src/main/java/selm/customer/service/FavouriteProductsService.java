package selm.customer.service;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import selm.customer.entity.FavouriteProduct;

public interface FavouriteProductsService {
    Mono<FavouriteProduct> addProductToFavourite(Integer productId);
    Mono<Void> removeProductFromFavourite(Integer productId);
    Mono<FavouriteProduct> findFavouriteProductByProduct(Integer productId);
}
