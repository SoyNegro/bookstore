package dev.coffeecult.bookstore.security.payload.request;

import javax.validation.constraints.NotBlank;

public record LoginRequest(
        @NotBlank
        String username,
        @NotBlank
        String password
) {
}
