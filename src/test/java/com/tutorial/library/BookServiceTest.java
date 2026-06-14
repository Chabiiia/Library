package com.tutorial.library;

import com.tutorial.library.book.Book;
import com.tutorial.library.book.BookRepository;
import com.tutorial.library.book.BookService;
import com.tutorial.library.book.BookResponseDTO;
import com.tutorial.library.user.User;
import com.tutorial.library.user.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {
    @Mock
    BookRepository bookRepository;
    @Mock
    UserRepository userRepository;
    @InjectMocks
    BookService bookService;
    private Book fakeBook;

    @BeforeEach
    void setUp(){
        fakeBook = new Book();
        fakeBook.setAuthor("Kasgarli Mahmut");
        fakeBook.setPages(95);
        fakeBook.setTitle("Divani Lugatit Leage of Legends");
        fakeBook.setId(1L);
    }

    @Test
    void getBookById_Sorgulandiginda_KitapResponseDonmeli(){
        when(bookRepository.findById(1L)).thenReturn(Optional.of(fakeBook));

        BookResponseDTO response = bookService.getBookById(1L);

        assertNotNull(response);
        assertEquals(1L,response.getId());
        assertEquals("Divani Lugatit Leage of Legends",response.getTitle());

        verify(bookRepository,times(1)).findById(1L);
    }

    @Test
    void borrowBook_KitapBosOlupKullaniciAldiginda_BasariliResponseDonmeli(){
        User fakeUser = new User();
        fakeUser.setId(1L);
        fakeUser.setUsername("Napoleon");

        when(bookRepository.findById(1L)).thenReturn(Optional.of(fakeBook));
        when(userRepository.findById(1L)).thenReturn(Optional.of(fakeUser));
        when(bookRepository.save(any(Book.class))).thenReturn(fakeBook);

        BookResponseDTO response = bookService.borrowBooks(1L,1L);

        assertNotNull(response);
        assertEquals(1L,response.getId());
        assertEquals("Divani Lugatit Leage of Legends",response.getTitle());

        verify(bookRepository,times(1)).save(any(Book.class));
    }


}
