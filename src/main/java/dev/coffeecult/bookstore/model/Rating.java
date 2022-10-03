package dev.coffeecult.bookstore.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

public record Rating(@Id String id, String username, int rate) {
    public Rating(String username, int rate){
        this(ObjectId.get().toHexString(), username,rate);
    }
}
