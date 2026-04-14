package sel.manager.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.api.http.dto.RequestProductDto;
import org.api.http.dto.ResponseProductDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sel.manager.client.ProductsRestClient;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("catalogue/products")
public class ProductsController {

    private final ProductsRestClient restClient;

    @GetMapping("/list")
    public String getAll(Model model) {
        log.info("вызван get /list");

        List<ResponseProductDto> allProducts = restClient.findAllProducts();
        model.addAttribute("products", allProducts);
        return "list";
    }

    @GetMapping("/create")
    public String createProduct() {
        log.info("вызван get /create");
        return "create";
    }

    @PostMapping("/create")
    public String save(
            @ModelAttribute RequestProductDto dto
            ) {
        log.info("вызван post /create для {}", dto);
        restClient.create(dto.title(), dto.description());
        return "redirect:/";
    }
}
