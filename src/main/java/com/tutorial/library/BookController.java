package com.tutorial.library;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService){
        this.bookService = bookService;
    }

    @GetMapping
    public List<Book> getBooks() {
        return bookService.getAllBooks();
    }

    @PostMapping
    public String addBook(@Valid @RequestBody BookDTO bookDTO){
        bookService.saveBook(bookDTO);
        return "kaydedildi";
    }

    @GetMapping("/{id}")
    public Book getById(@PathVariable Long id){
        return bookService.getBookById(id);
    }

    @DeleteMapping("/{id}")
    public String deleteByID(@PathVariable Long id){
        bookService.deleteBookById(id);
        return id + "id li book silindi";
    }

    @PutMapping("/{id}")
    public Book updateBook(@PathVariable Long id,@RequestBody Book book){
        return bookService.updateBook(id,book);
    }
}
