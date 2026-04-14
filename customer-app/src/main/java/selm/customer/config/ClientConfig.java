package selm.customer.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class ClientConfig {
    @Bean
    public WebClient getWebClient(
            @Value("${selmag.services.catalogue.url:http://localhost:9998}") String url
    ) {
        return WebClient.builder()
                .baseUrl(url)
                .build();
    }
}
