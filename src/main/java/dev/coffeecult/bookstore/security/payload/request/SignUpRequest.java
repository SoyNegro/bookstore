package dev.coffeecult.bookstore.security.payload.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

public record SignUpRequest(
        @NotNull
        @Size(max = 30)
        String username,
        @NotNull
        @Size(max = 50)
        @Email
        String email,
        @NotNull
        @Size(min = 8,max = 50)
        String password,
        Set<String> roles
) {
}
