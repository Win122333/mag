package sel.manager.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.api.http.dto.RequestProductDto;
import org.api.http.dto.ResponseProductDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sel.manager.client.ProductsRestClient;

import java.util.NoSuchElementException;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/catalogue/products/{productId:\\d+}")
public class ProductController {

    private final ProductsRestClient productsRestClient;

    @ModelAttribute("product")
    public ResponseProductDto init(
            @PathVariable("productId") Integer id
    ) throws NoSuchElementException {
        log.info("get by product id {}", id);
        return productsRestClient.findById(id).orElseThrow(NoSuchElementException::new);
    }
    @GetMapping
    public String getProduct(
            @PathVariable("productId") Integer id
    ) {
        log.info("вызван get /id {}", id);
        return "product";
    }
    @GetMapping("/edit")
    public String editProduct() {
        log.info("вызван get /{productId:\\d+}/edit");
        return "edit";
    }
    @PostMapping("/update")
    public String updateProduct(
            @PathVariable("productId") Integer id,
            @ModelAttribute RequestProductDto dto
            ) {
        log.info("вызван update c данными: {}", dto);
        productsRestClient.update(id, dto.title(), dto.description());
        return "redirect:/";
    }
    @PostMapping("/delete")
    public String deleteProduct(
            @PathVariable("productId") Integer id
    ) {
        log.info("удалили product с id = {}", id);
        productsRestClient.delete(id);
        return "redirect:/";
    }

    @ExceptionHandler(NoSuchElementException.class)
    public String NoSuchElementExceptionHandle(
            NoSuchElementException e,
            Model model) {
        model.addAttribute("e", e.getMessage());
        return "error/404";
    }
    //TODO добавить обработку ошибок из первого урока
}
