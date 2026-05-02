package com.example.customerwallet.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int customerId;
    private double balance;
}