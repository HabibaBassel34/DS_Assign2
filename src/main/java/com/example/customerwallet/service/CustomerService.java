package com.example.customerwallet.service;

import com.example.customerwallet.dto.*;
import com.example.customerwallet.model.*;
import com.example.customerwallet.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepo;

    @Autowired
    private WalletRepository walletRepo;

    private final RestTemplate restTemplate = new RestTemplate();

    public Customer register(RegisterDTO dto) {

        if (customerRepo.findByUsername(dto.getUsername()).isPresent()) {
            throw new RuntimeException("Username already exists");
        }

        Customer c = new Customer();
        c.setUsername(dto.getUsername());
        c.setPassword(dto.getPassword());

        Customer saved = customerRepo.save(c);

        Wallet w = new Wallet();
        w.setCustomerId(saved.getId());
        w.setBalance(dto.getBalance());

        walletRepo.save(w);

        return saved;
    }

    public Customer login(LoginDTO dto) {
        Customer c = customerRepo.findByUsername(dto.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!c.getPassword().equals(dto.getPassword())) {
            throw new RuntimeException("Wrong password");
        }

        return c;
    }

    public Wallet addBalance(int customerId, double amount) {
        Wallet w = walletRepo.findByCustomerId(customerId)
                .orElseThrow();

        w.setBalance(w.getBalance() + amount);
        return walletRepo.save(w);
    }

    public double getBalance(int customerId) {
        return walletRepo.findByCustomerId(customerId)
                .orElseThrow()
                .getBalance();
    }

    // CALL PROVIDER SERVICE
    public Object browseServices() {
        return restTemplate.getForObject(
                "http://localhost:8081/services",
                Object.class);
    }

    // CALL BOOKING SERVICE
    public Object bookService(BookingRequest req) {
        return restTemplate.postForObject(
                "http://localhost:8082/book",
                req,
                Object.class);
    }

    public Object getHistory(int customerId) {
        return restTemplate.getForObject(
                "http://localhost:8082/history/" + customerId,
                Object.class);
    }

    public CustomerResponseDTO getCustomer(int id) {

        Customer c = customerRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        Wallet w = walletRepo.findByCustomerId(id)
                .orElseThrow();

        return new CustomerResponseDTO(
                c.getId(),
                c.getUsername(),
                w.getBalance());
    }
}