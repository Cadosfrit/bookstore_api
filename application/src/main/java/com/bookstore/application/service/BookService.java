package com.bookstore.application.service;
import com.bookstore.application.entity.BookDetails;
import com.bookstore.application.repository.BookStoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.*;

@Service

public class BookService {
    @Autowired
    private BookStoreRepository bookRepository;

    public BookDetails createBook(BookDetails books) {
        return bookRepository.save(books);
    }
    public Page<BookDetails> getAllBooks(int page, int size, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return bookRepository.findAll(pageable);
    }
    public Optional<BookDetails> getBookById(String id) {
        return bookRepository.findById(id);
    }
    public Optional<BookDetails> updateBook(String id, BookDetails updatedBook) {
        return bookRepository.findById(id)
                .map(existing -> {
                    existing.setTitle(updatedBook.getTitle());
                    existing.setAuthor(updatedBook.getAuthor());
                    existing.setCategory(updatedBook.getCategory());
                    existing.setPrice(updatedBook.getPrice());
                    existing.setRating(updatedBook.getRating());
                    existing.setPublishedDate(updatedBook.getPublishedDate());
                    return bookRepository.save(existing);
                });
    }
    public boolean deleteBook(String id) {
        return bookRepository.findById(id).map(existing -> {
            bookRepository.deleteById(id);
            return true;
        }).orElse(false);
    }
    public Page<BookDetails> searchBooksByTitle(String title, int page, int size, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return bookRepository.findByTitleContainingIgnoreCase(title, pageable);
    }
    public Page<BookDetails> filterBooks(String author, String category, Double rating, int page, int size, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return bookRepository.findByAuthorContainingIgnoreCaseAndCategoryContainingIgnoreCaseAndRatingGreaterThanEqual(
                author != null ? author : "",
                category != null ? category : "",
                rating != null ? rating : 0.0,
                pageable
        );
    }
}
