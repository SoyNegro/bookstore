package dev.coffeecult.bookstore.model;

import org.bson.types.Binary;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import java.util.Base64;

public record Picture(@Id String id, String title, Binary pic) {
    public Picture(String title,Binary pic){
        this(ObjectId.get().toHexString(), title,pic);
    }
    public String getPictureAsEncodedString(){
        return Base64.getEncoder().encodeToString(pic.getData());
    }
}
