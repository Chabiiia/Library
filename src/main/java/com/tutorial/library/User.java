package com.tutorial.library;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "library_users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Bu kisim bos birakilamaz")
    @Size(min = 3, message = "username en az 3 karakter olmalidir")
    private String username;
    @NotBlank(message = "Bu kisim bos birakilamaz")
    @Email(message = "Gecerli bir e-posta adresi giriniz")
    private String email;
    @NotBlank(message = "Bu kisim bos birakilamaz")
    @Size(min = 6, message = "sifre en az 6 karakter olmalidir")
    private String password;

    public User(){}

    public User(Long id,String username,String email,String password){
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public Long getId() {return id;}
    public void setId(Long id){this.id=id;}

    public String getUsername(){return username;}
    public void setUsername(String username){this.username = username;}

    public String getEmail(){return email;}
    public void setEmail(String email){this.email=email;}

    public String getPassword(){return password;}
    public void setPassword(String password){this.password = password;}

}
