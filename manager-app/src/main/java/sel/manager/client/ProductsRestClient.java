package sel.manager.client;

import sel.manager.dto.RequestProductDto;
import sel.manager.dto.ResponseProductDto;
import sel.manager.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductsRestClient {
    List<ResponseProductDto> findAllProducts();

    ResponseProductDto create(String title, String description);

    Optional<ResponseProductDto> findById(Integer id);

    ResponseProductDto update(Integer id, String title, String description);

    void delete(Integer id);
}
