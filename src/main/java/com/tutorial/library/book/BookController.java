package com.tutorial.library.book;

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
    public List<BookResponseDTO> getBooks() {
        return bookService.getAllBooks();
    }

    @PostMapping
    public BookResponseDTO addBook(@Valid @RequestBody BookDTO bookDTO){
        return bookService.saveBook(bookDTO);
    }

    @GetMapping("/{id}")
    public BookResponseDTO getById(@PathVariable Long id){
        return bookService.getBookById(id);
    }

    @DeleteMapping("/{id}")
    public String deleteByID(@PathVariable Long id){
        bookService.deleteBookById(id);
        return id + " id li book silindi";
    }

    @PutMapping("/{id}")
    public BookResponseDTO updateBook(@PathVariable Long id, @Valid @RequestBody BookDTO bookDTO){
        return bookService.updateBook(id, bookDTO);
    }

    @PutMapping("/{bookId}/borrow/{userId}")
    public BookResponseDTO borrowBook(@PathVariable Long bookId,@PathVariable Long userId){
        return bookService.borrowBooks(bookId,userId);
    }

    @PutMapping("/{bookId}/return")
    public BookResponseDTO returnBook(@PathVariable Long bookId){
        return bookService.returnBooks(bookId);
    }
}