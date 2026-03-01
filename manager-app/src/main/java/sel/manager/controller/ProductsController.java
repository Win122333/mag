package sel.manager.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sel.manager.Entity.Product;
import sel.manager.dto.NewProductDto;
import sel.manager.service.ProductService;

import java.util.ArrayList;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("catalogue/products")
public class ProductsController {

    private final ProductService productService;

    @GetMapping("/list")
    public String getIndex(Model model) {
        log.info("вызван get /list");
        model.addAttribute("products", new ArrayList());
        return "list";
    }
    @GetMapping("/create")
    public String createView() {
        log.info("вызван get /create");
        return "create";
    }
    @PostMapping("/create")
    public String save(
            @ModelAttribute NewProductDto dto
            ) {
        log.info("вызван post /create для {}", dto);
        return "redirect:/";
    }
}
