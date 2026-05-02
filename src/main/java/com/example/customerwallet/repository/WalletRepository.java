package com.example.customerwallet.repository;

import com.example.customerwallet.model.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WalletRepository extends JpaRepository<Wallet, Integer> {
    Optional<Wallet> findByCustomerId(int customerId);
}