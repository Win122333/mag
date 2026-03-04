package sel.catalogue.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.api.http.dto.RequestProductDto;
import org.api.http.dto.ResponseProductDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import sel.catalogue.Entity.Product;
import sel.catalogue.dto.mapper.Mapper;
import sel.catalogue.service.ProductService;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/catalogue-api/products")
public class ProductRestController {
    private final ProductService productService;
    private final Mapper mapper;

    @GetMapping
    public ResponseEntity<List<Product>> getAll() {
        return ResponseEntity.ok(productService.getAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<ResponseProductDto> getById(
            @PathVariable("id") Integer id
    ) {
        return ResponseEntity.ok(mapper.mapFromProduct(productService.getById(id).orElse(Product.builder()
                        .title("Товара нет")
                        .description("Тут нет товара")
                .build())));
    }
    @PostMapping
    public ResponseEntity<?> save(
            @RequestBody @Valid RequestProductDto dto,
            UriComponentsBuilder uri,
            BindingResult result
            ) throws BindException {
        if (result.hasErrors()) {
            throw new BindException(result);
        }
        Product product = productService.save(mapper.mapToProduct(dto));
        return ResponseEntity
                .created(uri.replacePath("/catalogue-api/products/{productId}")
                        .build(Map.of("productId", product.getId())))
                .body(mapper.mapFromProduct(product));

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable("id") Integer id
    ) {
        productService.delete(id);
        return ResponseEntity.noContent()
                .build();

    }
    @PutMapping("/{id}")
    public ResponseEntity<ResponseProductDto> update(
            @PathVariable("id") Integer id,
            @RequestBody RequestProductDto dto,
            BindingResult result
    ) throws BindException {
        if (result.hasErrors()) {
            throw new BindException(result);
        }
        log.info("called with dto = {}", dto);
        return ResponseEntity.ok(
                mapper.mapFromProduct(productService.update(id, mapper.mapToProduct(dto))));
    }
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ProblemDetail> NoSuchElementExceptionHandler(
            NoSuchElementException e
    ) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ProblemDetail.forStatus(HttpStatus.NOT_FOUND));
    }
}
