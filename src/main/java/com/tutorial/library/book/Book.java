package com.tutorial.library.book;

import com.tutorial.library.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Bos birakilamaz")
    @Size(min = 2,message = "kitap adi en az 2 karakter olmalidir")
    private String title;
    @NotBlank(message = "Bu kisim bos birakilamaz")
    @Size(min = 2,message = "en az 2 karakter")
    private String author;
    @Min(value = 1,message = "sayfa sayisi 1'den az olamaz")
    private int pages;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Book(){}
    public Book(Long id,String title,String author,int pages){
        this.id = id;
        this.title= title;
        this.author=author;
        this.pages = pages;
    }

    public Long getId(){return id;}
    public void setId(Long id){this.id = id;}

    public String getTitle(){return title;}
    public void setTitle(String title){this.title = title;}

    public String getAuthor(){return author;}
    public void setAuthor(String author){this.author = author;}

    public int getPages(){return pages;}
    public void setPages(int pages){this.pages=pages;}

    public User getUser(){return user;}
    public void setUser(User user){this.user = user;}
}
