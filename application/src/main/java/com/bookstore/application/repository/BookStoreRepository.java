package com.bookstore.application.repository;
import com.bookstore.application.entity.BookDetails;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;



public interface BookStoreRepository extends MongoRepository<BookDetails, String> {
    List<BookDetails> findByAuthorContainingIgnoreCase(String author);
    List<BookDetails> findByCategoryContainingIgnoreCase(String category);
    List<BookDetails> findByRatingGreaterThanEqual(double rating);
    Page<BookDetails> findByTitleContainingIgnoreCase(String title, Pageable pageable);
    Page<BookDetails> findByAuthorContainingIgnoreCaseAndCategoryContainingIgnoreCaseAndRatingGreaterThanEqual(
            String author,
            String category,
            Double rating,
            Pageable pageable
    );
}
