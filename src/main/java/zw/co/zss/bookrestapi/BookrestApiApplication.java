package zw.co.zss.bookrestapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import zw.co.zss.bookrestapi.utils.Constants;

@SpringBootApplication
public class BookrestApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookrestApiApplication.class, args);
    }

    @Bean
    public WebClient getWebClient() {
        return WebClient.builder()
                .defaultHeaders(httpHeaders -> httpHeaders.setBearerAuth(Constants.BEARER_TOKEN_KEY))
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .baseUrl(Constants.TRANSACTION_END_POINT)
                .build();
    }
}
