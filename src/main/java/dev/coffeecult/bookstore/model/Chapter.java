package dev.coffeecult.bookstore.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public record Chapter(@Id String id,
                      String title,
                      String content,
                      ChapterType type,
                      List<Comment> comments,
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
                new ArrayList<>(),
                LocalDateTime.now(),
                LocalDateTime.now(),
                true,
                hasNSFWContent,
                hasStrongLanguage,
                isVip);
    }
}
