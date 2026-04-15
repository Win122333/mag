package selm.customer.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import reactor.core.publisher.Mono;
import selm.customer.client.FavouriteProductsClient;
import selm.customer.client.ProductsClient;
import selm.customer.entity.FavouriteProduct;

@Controller
@RequiredArgsConstructor
@RequestMapping("/customer/products")
public class ProductsController {
    private final ProductsClient productsClient;
    private final FavouriteProductsClient favouriteProductsClient;
    @GetMapping("/list")
    public Mono<String> getProductsListPage(
            Model model
    ) {
        return productsClient.findAllProducts()
                .collectList()
                .doOnNext(products -> model.addAttribute("products", products))
                .thenReturn("customer/products/list");
    }
    @GetMapping("favourites")
    public Mono<String> getFavouriteProductsPage(Model model) {
        return favouriteProductsClient.findFavouriteProductProducts()
                .map(FavouriteProduct::productId)
                .collectList()
                .flatMap(favouriteProducts -> productsClient.findAllProducts()
                        .filter(product -> favouriteProducts.contains(product.id()))
                        .collectList()
                        .doOnNext(products -> model.addAttribute("products", products)))
                .thenReturn("customer/products/favourites");
    }
}
