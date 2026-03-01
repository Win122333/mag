package sel.manager.service;

import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sel.manager.Entity.Product;
import sel.manager.repository.ProductRepository;

import java.util.Optional;
//import sel.manager.repository.ProductRepository;

@Service
@RequiredArgsConstructor
public class ProductService {

//    private final ProductRepository productRepository;

    public Optional<Product> getById(Integer id) {
        return Optional.ofNullable(null);
    }
}
