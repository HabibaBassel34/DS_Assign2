package com.example.customerwallet.dto;

import lombok.Data;

@Data
public class BookingRequest {
    private int customerId;
    private int serviceId;
}