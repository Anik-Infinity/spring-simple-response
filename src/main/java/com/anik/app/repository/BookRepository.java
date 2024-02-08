package com.anik.app.repository;

import com.anik.app.entity.author.Author;
import com.anik.app.entity.book.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BookRepository extends JpaRepository<Book, UUID> {
    Page<Book> findAllByAuthor(Author author, Pageable pageable);
}
