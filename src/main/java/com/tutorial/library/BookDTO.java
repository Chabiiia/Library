package com.tutorial.library;

import jakarta.validation.constraints.NotBlank;

public class BookDTO {
    @NotBlank(message = "Kitap adi bos birakilamaz")
    private String title;

    @NotBlank(message = "yazar adi bos birakilamz")
    private String author;

    public String getTitle(){return title;}
    public void setTitle(String title){this.title = title;}
    public String getAuthor(){return author;}
    public void setAuthor(String author){this.author = author;}
}
