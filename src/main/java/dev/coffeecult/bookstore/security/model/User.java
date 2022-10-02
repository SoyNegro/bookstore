package dev.coffeecult.bookstore.security.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Document
public record User(@Id String id,
                   @NotNull
                   @Size(max = 30)
                   String username,
                   @NotNull
                   @Size(min = 8,max = 50)
                   String password,
                   @NotNull
                   @Size(max = 50)
                   @Email
                   String email,
                   Set<Role> roles) {
}
