package com.anik.app.service;

import com.anik.app.entity.author.Author;
import com.anik.app.entity.book.Book;
import com.anik.app.exception.ResourceNotFoundException;
import com.anik.app.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;

    public Page<Book> getList(int pageNo, int pageSize, Author author) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        return (author != null) ?
              this.bookRepository.findAllByAuthor(author, pageable) :
              this.bookRepository.findAll(pageable);
    }

    public Book getById(UUID id) {
        return this.bookRepository.findById(id)
              .orElseThrow(() -> new ResourceNotFoundException("Book not found with id " + id));
    }

    public Book save(Book book) {
        return this.bookRepository.save(book);
    }

    public void delete(UUID id) {
        if (!this.bookRepository.existsById(id))
            throw new ResourceNotFoundException("Book not found with id: " + id);
        this.bookRepository.deleteById(id);
    }
}
