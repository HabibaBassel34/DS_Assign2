package com.example.customerwallet.controller;

import com.example.customerwallet.dto.*;
import com.example.customerwallet.model.*;
import com.example.customerwallet.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService service;

    @PostMapping("/register")
    public Customer register(@RequestBody RegisterDTO dto) {
        return service.register(dto);
    }

    @PostMapping("/login")
    public Customer login(@RequestBody LoginDTO dto) {
        return service.login(dto);
    }

    @PostMapping("/wallet/add")
    public Wallet add(@RequestParam int customerId, @RequestParam double amount) {
        return service.addBalance(customerId, amount);
    }

    @GetMapping("/wallet/{customerId}")
    public double getBalance(@PathVariable int customerId) {
        return service.getBalance(customerId);
    }

    @GetMapping("/services")
    public Object browse() {
        return service.browseServices();
    }

    @PostMapping("/book")
    public Object book(@RequestBody BookingRequest req) {
        return service.bookService(req);
    }

    @GetMapping("/history/{customerId}")
    public Object history(@PathVariable int customerId) {
        return service.getHistory(customerId);
    }

    @GetMapping("/{id}")
    public CustomerResponseDTO getCustomer(@PathVariable int id) {
        return service.getCustomer(id);
    }
}
