package sel.catalogue.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sel.catalogue.Entity.Product;
import sel.catalogue.dto.NewProductDto;
import sel.catalogue.dto.ResponseProductDto;
import sel.catalogue.service.ProductService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/catalogue/products")
public class ProductRestController {
    private final ProductService productService;

    @GetMapping
    public ResponseEntity<List<Product>> getAll() {
        return ResponseEntity.ok(productService.getAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Product> getById(
            @PathVariable("id") Integer id
    ) {
        return ResponseEntity.ok(productService.getById(id).orElse(Product.builder()
                        .title("Товара нет")
                        .description("Тут нет товара")
                .build()));
    }
    @PostMapping
    public ResponseEntity<Product> save(
            @RequestBody NewProductDto dto
            ) {
        return ResponseEntity.ok(productService.save(Product.builder()
                        .title(dto.title())
                        .description(dto.description())
                .build()));
    }
    @DeleteMapping("/{id}")
    public void delete(
            @PathVariable("id") Integer id
    ) {
        productService.delete(id);
    }
}
