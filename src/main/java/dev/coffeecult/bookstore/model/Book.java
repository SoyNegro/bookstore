package dev.coffeecult.bookstore.model;

import dev.coffeecult.bookstore.model.enums.BookStatus;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Document
public record Book(@Id String id,
                   String title,
                   BookStatus status,
                   String bookSlug,
                   String summary,
                   Picture cover,
                   List<Chapter> chapters,
                   @DBRef
                   List<Classification> classifications,
                   List<Comment> comments,
                   List<Rating> ratings,
                   List<Review> reviews,
                   LocalDateTime createdAt,
                   LocalDateTime lastUpdatedAt,
                   int views) {
    public Book(String title, String bookSlug, String summary,Picture cover){
        this(ObjectId.get().toHexString(),
                title,
                BookStatus.ONGOING,
                bookSlug,
                summary,
                cover,
                new ArrayList<>(),
                new ArrayList<>(),
                new ArrayList<>(),
                new ArrayList<>(),
                new ArrayList<>(),
                LocalDateTime.now(),
                LocalDateTime.now(),
                0);
    }

    public Double getRate(){
        var optRate = ratings.stream().mapToDouble(Rating::rate).average();
        return optRate.orElse(0);
    }

    public int getChapterCount(){
        return chapters.size();
    }
}
