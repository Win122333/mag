package selm.customer.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import selm.customer.entity.FavouriteProduct;
import selm.customer.repository.FavouriteProductsRepository;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FavouriteProductsServiceImpl implements FavouriteProductsService {
    private final FavouriteProductsRepository favouriteProductsRepository;

    @Override
    public Mono<FavouriteProduct> addProductToFavourite(Integer productId) {
        return favouriteProductsRepository.save(new FavouriteProduct(UUID.randomUUID(), productId));
    }

    @Override
    public Mono<Void> removeProductFromFavourite(Integer productId) {
        return favouriteProductsRepository.deleteByProductId(productId);
    }

    @Override
    public Flux<FavouriteProduct> findFavouriteProductProducts() {
        return favouriteProductsRepository.findAll();
    }

    @Override
    public Mono<FavouriteProduct> findFavouriteProductByProduct(Integer productId) {
        return favouriteProductsRepository.findFavouriteProductByProduct(productId);
    }

}
