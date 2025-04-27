package webclientservice;

import model.Category;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class CategoryClientService {
    private final WebClient webClient;
    private final static String BASE_URL = "http://localhost:8080";

    public CategoryClientService() {
        this.webClient = WebClient.builder()
                .baseUrl(BASE_URL)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    public Mono<Category> getCategoryById(int id) {
        return webClient.get()
                .uri("/api/categories/{id}", id)
                .retrieve()
                .bodyToMono(Category.class);
    }

    public Flux<Category> getAllCategories() {
        return webClient.get()
                .uri("/api/categories")
                .retrieve()
                .bodyToFlux(Category.class);
    }



}
