package sel.manager.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sel.manager.Entity.Product;
import sel.manager.dto.UpdateProductDto;
import sel.manager.service.ProductService;
@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("catalogue/products/{productId:\\d+}")
public class ProductController {

    private final ProductService productService;

    @ModelAttribute("product")
    public Product init(@PathVariable("productId") Integer id) {
        return Product.builder()
                .id(id)
                .title("Молоко")
                .description("Описание")
                .build();
    }
    @GetMapping
    public String getProduct() {
        log.info("вызван get /{productId:\\d+}");
        return "product";
    }
    @GetMapping("/edit")
    public String editProduct() {
        log.info("вызван get /{productId:\\d+}/edit");
        return "edit";
    }
    @PostMapping("update")
    public String updateProduct(
            @ModelAttribute UpdateProductDto dto
            ) {
        log.info("вызван update c данными: {}", dto);
        return "redirect:/";
    }
}
