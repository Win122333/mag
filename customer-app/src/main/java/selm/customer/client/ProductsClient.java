package selm.customer.client;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import selm.customer.entity.Product;

public interface ProductsClient {
    Flux<Product> findAllProducts();
    Mono<Product> findProduct(Integer id);
}
