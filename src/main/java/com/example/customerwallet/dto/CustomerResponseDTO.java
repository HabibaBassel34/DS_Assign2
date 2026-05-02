package com.example.customerwallet.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomerResponseDTO {
    private int id;
    private String username;
    private double balance;
}