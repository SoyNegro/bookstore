package dev.coffeecult.bookstore.security.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

public record Role(@Id String id, BaseRoles name) {
    public Role(BaseRoles name){
        this(ObjectId.get().toHexString(), name);
    }
}
