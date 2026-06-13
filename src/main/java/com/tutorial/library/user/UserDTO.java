package com.tutorial.library.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserDTO {
    @NotBlank(message = "Bu kisim bos birakilamaz")
    @Size(min = 3, message = "username en az 3 karakter olmalidir")
    private String username;
    @NotBlank(message = "Bu kisim bos birakilamaz")
    @Email(message = "Gecerli bir e-posta adresi giriniz")
    private String email;
    @NotBlank(message = "Bu kisim bos birakilamaz")
    @Size(min = 6, message = "sifre en az 6 karakter olmalidir")
    private String password;

    public String getUsername(){return username;}
    public void setUsername(String username){this.username = username;}

    public String getEmail(){return email;}
    public void setEmail(String email){this.email=email;}

    public String getPassword(){return password;}
    public void setPassword(String password){this.password = password;}
}
