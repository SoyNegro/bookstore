package dev.coffeecult.bookstore.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public record Classification(@Id String id, String name, String description, ClassificationType type) {
    public Classification(String name,String description, ClassificationType type){
        this(ObjectId.get().toHexString(), name,description,type);
    }
}
