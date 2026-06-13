package com.tutorial.library.book;

import com.tutorial.library.exception.ResourceNotFoundException;
import com.tutorial.library.user.User;
import com.tutorial.library.user.UserRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BookService {
    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    public BookService(BookRepository bookRepository,UserRepository userRepository){
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
    }

    public BookResponseDTO borrowBooks(Long bookId,Long userId){
        Book book = bookRepository.findById(bookId)
                .orElseThrow(()-> new ResourceNotFoundException(bookId + "id'li kitap bulunamadi"));

        if (book.getUser() != null){
            throw new RuntimeException("this book is already taken by" + userId);
        }
        User user = userRepository.findById(userId)
                .orElseThrow(()-> new ResourceNotFoundException(bookId + "id'li kullanici bulunamadi"));

        book.setUser(user);
        Book updatedBook = bookRepository.save(book);
        return toResponseDTO(updatedBook);
    }

    public BookResponseDTO returnBooks(Long bookId){
        Book book = bookRepository.findById(bookId)
                .orElseThrow(()-> new ResourceNotFoundException(bookId + "id'li kitap bulunamadi"));

        if (book.getUser() == null){
            throw new RuntimeException("bu kitapi kimse zaten almamis");
        }

        book.setUser(null);
        Book updatedBook = bookRepository.save(book);
        return toResponseDTO(updatedBook);
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
        book.setPages(bookDTO.getPages());

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
        existingBook.setPages(bookDTO.getPages());

        Book updatedBook = bookRepository.save(existingBook);
        return toResponseDTO(updatedBook);
    }

    private BookResponseDTO toResponseDTO(Book book){
        return new BookResponseDTO(book.getId(), book.getTitle(), book.getAuthor(), book.getPages());
    }
}