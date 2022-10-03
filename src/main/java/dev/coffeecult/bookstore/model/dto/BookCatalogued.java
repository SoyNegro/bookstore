package dev.coffeecult.bookstore.model.dto;

import dev.coffeecult.bookstore.model.Book;


import java.time.LocalDateTime;

public record BookCatalogued(String name, Double rating, int views, int chaptersCount, LocalDateTime lastUpdatedAt) {
    public BookCatalogued(Book book){
        this(book.title(),book.getRate(), book.views(), book.getChapterCount(),book.lastUpdatedAt());
    }
}
