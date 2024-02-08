package com.anik.app.service;

import com.anik.app.entity.author.Author;
import com.anik.app.exception.ResourceNotFoundException;
import com.anik.app.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthorService {
    private final AuthorRepository authorRepository;

    public Page<Author> getList(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        return this.authorRepository.findAll(pageable);
    }

    public Author getById(UUID id) {
        return this.authorRepository.findById(id)
              .orElseThrow(() -> new ResourceNotFoundException("Author not found with id " + id));
    }

    public Author save(Author author) {
        return this.authorRepository.save(author);
    }

    public void delete(UUID id) {
        if (!this.authorRepository.existsById(id))
            throw new ResourceNotFoundException("Author not found with id: " + id);
        this.authorRepository.deleteById(id);
    }
}
