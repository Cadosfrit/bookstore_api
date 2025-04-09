package com.bookstore.application.repository;
import com.bookstore.application.entity.Users;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;


public interface UserRepository extends MongoRepository<Users, String> {
    Optional<Users> findByEmail(String email);
}

