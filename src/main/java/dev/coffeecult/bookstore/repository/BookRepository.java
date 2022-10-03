package dev.coffeecult.bookstore.repository;

import dev.coffeecult.bookstore.model.Book;
import dev.coffeecult.bookstore.model.Classification;
import dev.coffeecult.bookstore.model.enums.BookStatus;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;
import java.util.stream.Stream;

public interface BookRepository  extends MongoRepository<Book,String> {
Optional<Book> findBookByBookSlug(String bookSlug);
Optional<Book> findBookByTitle(String title);
Stream<Book> getAllByClassificationsContaining(Classification classification);
Stream<Book> getAllByStatus(BookStatus status);
}
