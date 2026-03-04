package sel.manager.client;

import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import sel.manager.dto.RequestProductDto;
import sel.manager.dto.ResponseProductDto;

import java.util.List;
import java.util.Optional;
@Component
@RequiredArgsConstructor
public class ProductRestClientImpl implements ProductsRestClient {

    private static final ParameterizedTypeReference<List<ResponseProductDto>> PRODUCTS_TYPE_REFERENCE =
            new ParameterizedTypeReference<>() {
    };

    private final RestClient client;
    @Override
    public List<ResponseProductDto> findAllProducts() {
        return client
                .get()
                .uri("/catalogue-api/products")
                .retrieve()
                .body(PRODUCTS_TYPE_REFERENCE);
    }

    @Override
    public ResponseProductDto create(String title, String description) {
        return client
                .post()
                .uri("/catalogue-api/products")
                .contentType(MediaType.APPLICATION_JSON)
                .body(new RequestProductDto(title, description))
                .retrieve()
                .body(ResponseProductDto.class);
    }

    @Override
    public Optional<ResponseProductDto> findById(Integer id) {
        return Optional.ofNullable(client
                .get()
                .uri("/catalogue-api/products/{id}", id)
                .retrieve()
                .body(ResponseProductDto.class));
    }

    @Override
    public ResponseProductDto update(Integer id, String title, String description) {
        return client
                .put()
                .uri("/catalogue-api/products/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .body(RequestProductDto.builder()
                        .title(title)
                        .description(description)
                        .build())
                .retrieve()
                .body(ResponseProductDto.class);
    }

    @Override
    public void delete(Integer id) {
        client.delete()
                .uri("/catalogue-api/products/{id}", id)
                .retrieve()
                .toBodilessEntity();
    }
}
