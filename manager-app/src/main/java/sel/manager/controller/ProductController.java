package sel.manager.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import sel.manager.Entity.Product;
import sel.manager.service.ProductService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("catalogue/products")
public class ProductController {

    private final ProductService productService;

    @GetMapping("/list")
    public String getIndex(Model model) {
        model.addAttribute("products", new ArrayList());
        return "list";
    }
    @GetMapping("/create")
    public String createView() {
        return "create";
    }
    @PostMapping("/create/save")
    public ResponseEntity<String> save(@RequestBody Product product) {
        System.out.println(product);

        return ResponseEntity.ok("saved");
    }
}
