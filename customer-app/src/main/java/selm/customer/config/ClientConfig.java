package selm.customer.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.web.reactive.function.client.WebClient;
import selm.customer.client.*;

@Configuration
public class ClientConfig {
    @Bean
    public ProductsClient getWebClient(
            @Value("${selmag.services.catalogue.url:http://localhost:9998}") String url
    ) {
        return new WebClientProducts(WebClient.builder().baseUrl(url)
                .build());
    }
    @Bean
    public WebClientProductReviewsClient getProductsReviewClient (
            @Value("${selmag.services.feedback.url:http://localhost:9995}") String url
    ) {
        return new WebClientProductReviewsClient(WebClient.builder().baseUrl(url)
                .build());
    }
    @Bean
    public WebClientsFavouriteProductsClient getFavouriteProductsClient (
            @Value("${selmag.services.feedback.url:http://localhost:9995}") String url
    ) {
        return new WebClientsFavouriteProductsClient(WebClient.builder().baseUrl(url)
                .build());
    }
}
