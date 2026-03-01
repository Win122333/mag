package sel.catalogue.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sel.catalogue.Entity.Product;

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
