package org.example.dto;
import jakarta.validation.constraints.Min;

public record UpdatePriceRequest(@Min(0) int price) {}