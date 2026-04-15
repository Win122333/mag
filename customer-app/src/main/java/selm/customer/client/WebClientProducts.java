package selm.customer.client;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientRequestException;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import selm.customer.entity.Product;
@Service
@RequiredArgsConstructor
public class WebClientProducts implements ProductsClient {
    private final WebClient webClient;
    @Override
    public Flux<Product> findAllProducts() {
        return  webClient.
                get()
                .uri("/catalogue-api/products")
                .retrieve()
                .bodyToFlux(Product.class);
    }

    @Override
    public Mono<Product> findProduct(Integer id) {
        return webClient
                .get()
                .uri("catalogue-api/products/{id}", id)
                .retrieve()
                .bodyToMono(Product.class)
                .onErrorComplete(WebClientResponseException.NotFound.class);
    }
}
