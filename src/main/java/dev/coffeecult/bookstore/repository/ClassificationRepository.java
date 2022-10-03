package dev.coffeecult.bookstore.repository;

import dev.coffeecult.bookstore.model.Classification;
import dev.coffeecult.bookstore.model.enums.ClassificationType;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;
import java.util.stream.Stream;

public interface ClassificationRepository extends MongoRepository<Classification,String> {
    Stream<Classification> getAllByType(ClassificationType type);
    Optional<Classification> findClassificationByName(String name);
}
