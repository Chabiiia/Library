package com.tutorial.library;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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

    public Book(){

    }
    public Book(Long id,String title,String author){
        this.id = id;
        this.title= title;
        this.author=author;
    }

    public Long getId(){return id;}
    public void setId(Long id){this.id = id;}

    public String getTitle(){return title;}
    public void setTitle(String title){this.title = title;}

    public String getAuthor(){return author;}
    public void setAuthor(String author){this.author = author;}

}
