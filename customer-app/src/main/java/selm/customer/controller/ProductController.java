package selm.customer.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import reactor.core.publisher.Mono;
import selm.customer.client.ProductsClient;
import selm.customer.entity.Product;

@Controller
@RequiredArgsConstructor
@RequestMapping("/customer/products/{productId:\\d+}")
public class ProductController {

    private final ProductsClient productsClient;

    @ModelAttribute(name = "product", binding = false)
    public Mono<Product> getProduct(@PathVariable("productId") Integer id) {
        return productsClient.findProduct(id);
    }
    @GetMapping
    public String getProductPage() {
        return "customer/products/product";
    }
}
