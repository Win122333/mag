package selm.customer.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import selm.customer.client.ProductsClient;
import selm.customer.entity.Product;
import selm.customer.service.FavouriteProductsService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/customer/products/{productId:\\d+}")
public class ProductController {

    private final ProductsClient productsClient;

    private final FavouriteProductsService favouriteProductsService;

    @ModelAttribute(name = "product", binding = false)
    public Mono<Product> getProduct(@PathVariable("productId") Integer id) {
        return productsClient.findProduct(id);
    }
    @GetMapping
    public Mono<String> getProductPage(
            @PathVariable("productId") Integer id,
            Model model
    ) {
        model.addAttribute("inFavourite", false);
        return favouriteProductsService.findFavouriteProductByProduct(id)
                .doOnNext(product -> model.addAttribute("inFavourite", true))
                .thenReturn("customer/products/product");
    }

    @PostMapping("add-to-favourites")
    public Mono<String> addProductToFavourite(
            @ModelAttribute("product") Mono<Product> productMono
    ) {
        return productMono
                .map(Product::id)
                .flatMap(productId -> favouriteProductsService.addProductToFavourite(productId)
                        .thenReturn("redirect:/customer/products/%d".formatted(productId)));
    }
    @PostMapping("delete-from-favourites")
    public Mono<String> deleteProductFromFavourite(
            @ModelAttribute("product") Mono<Product> productMono
    ) {
        return productMono
                .map(Product::id)
                .flatMap(productId -> favouriteProductsService.removeProductFromFavourite(productId)
                        .thenReturn("redirect:/customer/products/%d".formatted(productId)));
    }
}
