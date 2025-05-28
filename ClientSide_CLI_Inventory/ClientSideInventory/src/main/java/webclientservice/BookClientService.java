package webclientservice;

import model.Book;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public class BookClientService {
    private final WebClient webClient;
    private final static String BASE_URL = "http://localhost:8080";

    public BookClientService() {
        this.webClient = WebClient.builder()
                .baseUrl(BASE_URL)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    public Flux<Book> getAllBooks() {
        return webClient.get()
                .uri("/api/books")
                .retrieve()
                .bodyToFlux(Book.class);
    }

    public Mono<Book> saveBook(Book book) {
        return webClient.post()
                .uri("/api/books")
                .bodyValue(book)
                .retrieve()
                .bodyToMono(Book.class);
    }

    public Mono<Void> saveAllBooks(List<Book> bookList) {
        return webClient.post()
                .uri("/api/books/list")
                .bodyValue(bookList)
                .retrieve()
                //.toBodilessEntity();
                .bodyToMono(Void.class);

    }






}
