package dev.coffeecult.bookstore.model;


import org.springframework.data.annotation.Id;
import org.bson.types.ObjectId;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public record Comment(@Id String id,
                      String author,
                      String content,
                      List<Comment> replies,
                      LocalDateTime createdAt,
                      LocalDateTime lastUpdatedAt,
                      boolean isVisible,
                      boolean hasNSFWContent,
                      boolean hasStrongLanguage,
                      boolean hasSpoiler) {
    public Comment(String author,
                   String content,
                   boolean hasNSFWContent,
                   boolean hasStrongLanguage,
                   boolean hasSpoiler) {
        this(
                ObjectId.get().toHexString(),
                author,
                content,
                new ArrayList<>(),
                LocalDateTime.now(),
                LocalDateTime.now(),
                true,
                hasNSFWContent,
                hasStrongLanguage,
                hasSpoiler
        );
    }
}
