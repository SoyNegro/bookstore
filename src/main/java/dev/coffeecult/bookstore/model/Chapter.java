package dev.coffeecult.bookstore.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

public record Chapter(@Id String id,
                      String title,
                      String content,
                      ChapterType type,
                      LocalDateTime createdAt,
                      LocalDateTime updatedAt,
                      boolean isVisible,
                      boolean hasNSFWContent,
                      boolean hasStrongLanguage,
                      boolean isVip) {
    public Chapter(String title,
                   String content,
                   ChapterType type,
                   boolean hasNSFWContent,
                   boolean hasStrongLanguage,
                   boolean isVip) {
        this(ObjectId.get().toHexString(),
                title,
                content,
                type,
                LocalDateTime.now(),
                LocalDateTime.now(),
                true,
                hasNSFWContent,
                hasStrongLanguage,
                isVip);
    }
}
