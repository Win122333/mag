package sel.manager.controller;

import org.api.http.dto.RequestProductDto;
import org.api.http.dto.ResponseProductDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.ConcurrentModel;
import org.springframework.ui.Model;
import sel.manager.client.ProductsRestClient;

@ExtendWith(MockitoExtension.class)
class ProductsControllerTest {
    @Mock
    ProductsRestClient productsRestClient;
    @InjectMocks
    ProductsController productsController;

    @Test
    @DisplayName("createProduct создаст новый товар и перенаправит на страницу товара")
    void createProduct_requestIsValid_ReturnsRedirectionToProductPage() {
        String title = "Молоко";
        String description = "Молоко ультрапастиризованное";
        var req = new RequestProductDto(title, description);
        Model model = new ConcurrentModel();

        Mockito.doReturn(new ResponseProductDto(1, title, description))
                        .when(productsRestClient)
                                .create(title, description);
        productsController.save(req);

        var res = productsController.save(req);

        Assertions.assertEquals("redirect:/", res);
    }
}