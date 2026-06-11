package com.tutorial.library;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    public List<Book> getAllBooks(){
        return bookRepository.findAll();
    }

    public BookDTO saveBook(BookDTO bookDTO){
        Book book = new Book();
        book.setTitle(bookDTO.getTitle());
        book.setAuthor(bookDTO.getAuthor());

        Book saveBook = bookRepository.save(book);

        BookDTO responseDTO = new BookDTO();
        responseDTO.setTitle(saveBook.getTitle());
        responseDTO.setAuthor(saveBook.getAuthor());

        return responseDTO;
    }

    public Book getBookById(Long id){
        return bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id + " ID'li kitap bulunamadi"));
    }

    public void deleteBookById(Long id){
        bookRepository.deleteById(id);
    }

    public Book updateBook(Long id,Book updatedBook){
        Book existingBook = bookRepository.findById(id).orElse(null);

        if (existingBook != null){
            existingBook.setTitle(updatedBook.getTitle());
            existingBook.setAuthor(updatedBook.getAuthor());
            return bookRepository.save(existingBook);
        }
        return null;

    }
}
