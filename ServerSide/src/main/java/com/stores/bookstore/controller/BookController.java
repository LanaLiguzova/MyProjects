package com.stores.bookstore.controller;

import com.stores.bookstore.model.Book;
import com.stores.bookstore.service.BookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/books")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();

    }

    @GetMapping("/{id}")
    public Book getBookById(@PathVariable int id) {
        return bookService.getBook(id).orElseThrow(()-> new RuntimeException("Cannot find"));
    }

    @PostMapping
    public Book saveBook(@RequestBody Book book) {
        return bookService.saveBook(book);
    }

    @PostMapping("/list")
    public void saveAllBooks(@RequestBody List<Book> bookList) {
        bookService.saveAll(bookList);
    }

}
