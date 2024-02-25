package com.example.ecommercebackend.api.model;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
public class LoginBody {
    @NotNull @NotBlank
    private String username;
    @NotNull @NotBlank
    private String password;
}
