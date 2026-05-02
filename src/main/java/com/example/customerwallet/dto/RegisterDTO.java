package com.example.customerwallet.dto;

import lombok.Data;

@Data
public class RegisterDTO {
    private String username;
    private String password;
    private double balance;
}