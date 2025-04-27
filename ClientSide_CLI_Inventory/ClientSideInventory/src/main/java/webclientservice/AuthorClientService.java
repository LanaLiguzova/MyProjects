package webclientservice;

import model.Author;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class AuthorClientService {
    private final WebClient webClient;
    private final static String BASE_URL = "http://localhost:8080";

    public AuthorClientService() {
        this.webClient = WebClient.builder()
                .baseUrl(BASE_URL)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    public Mono<Author> getAuthorById(int id) {
        return webClient.get()
                .uri("/api/authors/{id}", id)
                .retrieve()
                .bodyToMono(Author.class);
    }

    public Flux<Author> getAllAuthors() {
        return webClient.get()
                .uri("/api/authors")
                .retrieve()
                .bodyToFlux(Author.class);
    }


}
