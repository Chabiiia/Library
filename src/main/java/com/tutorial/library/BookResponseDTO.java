package com.tutorial.library;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class BookResponseDTO {
    @NotNull(message = "Id bos birakilamaz")
    private Long id;
    @NotBlank(message = "Kitap adi bos birakilamaz")
    private String title;
    @NotBlank(message = "yazar adi bos birakilamz")
    private String author;
    @Min(value = 1,message = "sayfa sayisi 1'den az olamaz")
    private int pages;

    public BookResponseDTO(){}

    public BookResponseDTO(Long id,String title,String author,int pages){
        this.id = id;
        this.title = title;
        this.author = author;
        this.pages = pages;
    }

    public Long getId(){return id;}
    public void setId(Long id){this.id = id;}

    public String getTitle(){return title;}
    public void setTitle(String title){this.title=title;}

    public String getAuthor(){return author;}
    public void setAuthor(String author){this.author = author;}

    public int getPages(){return pages;}
    public void setPages(int pages){this.pages=pages;}

}
