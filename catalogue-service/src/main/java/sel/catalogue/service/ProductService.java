package sel.catalogue.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sel.catalogue.Entity.Product;
import sel.catalogue.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public Optional<Product> getById(Integer id) {
        return productRepository.findById(id);
    }

    public List<Product> getAll() {
        return productRepository.findAll();
    }

    public Product save(Product product) {
        return productRepository.save(product);
    }
    public void delete(Integer id) {
        productRepository.delete(productRepository.findById(id).orElseThrow());
    }
}
