package dev.coffeecult.bookstore.security.model;

import org.springframework.data.annotation.Id;

public record Role(@Id String id, BaseRoles name) {
}
