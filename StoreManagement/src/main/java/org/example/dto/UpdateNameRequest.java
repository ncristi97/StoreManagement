package org.example.dto;
import jakarta.validation.constraints.NotBlank;

public record UpdateNameRequest(@NotBlank String name) {}
