package com.tutorial.library.book;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class BookDTO {
    @NotBlank(message = "Kitap adi bos birakilamaz")
    private String title;

    @NotBlank(message = "yazar adi bos birakilamz")
    private String author;

    @Min(value = 1,message = "sayfa sayisi 1'den az olamaz")
    private int pages;

    public String getTitle(){return title;}
    public void setTitle(String title){this.title = title;}

    public String getAuthor(){return author;}
    public void setAuthor(String author){this.author = author;}

    public int getPages(){return pages;}
    public void setPages(int pages){this.pages=pages;}
}
