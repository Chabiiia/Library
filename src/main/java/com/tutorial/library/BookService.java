package com.tutorial.library;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    public List<BookResponseDTO> getAllBooks(){
        return bookRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    public BookResponseDTO saveBook(BookDTO bookDTO){
        Book book = new Book();
        book.setTitle(bookDTO.getTitle());
        book.setAuthor(bookDTO.getAuthor());

        Book savedBook = bookRepository.save(book);

        return toResponseDTO(savedBook);
    }

    public BookResponseDTO getBookById(Long id){
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id + " ID'li kitap bulunamadi"));
        return toResponseDTO(book);
    }

    public void deleteBookById(Long id){
        bookRepository.deleteById(id);
    }

    public BookResponseDTO updateBook(Long id, BookDTO bookDTO){
        Book existingBook = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id + " ID'li kitap bulunamadi"));

        existingBook.setTitle(bookDTO.getTitle());
        existingBook.setAuthor(bookDTO.getAuthor());

        Book updatedBook = bookRepository.save(existingBook);
        return toResponseDTO(updatedBook);
    }

    private BookResponseDTO toResponseDTO(Book book){
        return new BookResponseDTO(book.getId(), book.getTitle(), book.getAuthor());
    }
}