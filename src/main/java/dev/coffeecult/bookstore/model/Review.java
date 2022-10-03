package dev.coffeecult.bookstore.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

public record Review(@Id String id,
                     String title,
                     String reviewedAtChapter,
                     String content,
                     LocalDateTime createdAt,
                     LocalDateTime lastUpdatedAt,
                     boolean isVisible,
                     boolean hasNSFWContent,
                     boolean hasStrongLanguage,
                     boolean hasSpoiler) {
    public Review(String title,
                  String reviewedAtChapter,
                  String content,
                  boolean hasNSFWContent,
                  boolean hasStrongLanguage,
                  boolean hasSpoiler){
        this(ObjectId.get().toHexString(),
                title,
                reviewedAtChapter,
                content,
                LocalDateTime.now(),
                LocalDateTime.now(),
                true,
                hasNSFWContent,
                hasStrongLanguage,
                hasSpoiler
                );
    }
}
