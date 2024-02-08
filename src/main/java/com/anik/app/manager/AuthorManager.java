package com.anik.app.manager;

import com.anik.app.dto.author.AuthorDto;
import com.anik.app.dto.author.CreateAuthorDto;
import com.anik.app.dto.author.UpdateAuthorDto;
import com.anik.app.entity.author.Author;
import com.anik.app.mapper.AuthorMapper;
import com.anik.app.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class AuthorManager {
    private final AuthorService authorService;
    private final AuthorMapper authorMapper;

    public AuthorDto create(CreateAuthorDto createAuthorDto) {
        Author author = this.authorMapper.toEntity(createAuthorDto);
        if (author.getBooks() != null && !author.getBooks().isEmpty())
            author.getBooks().forEach(book -> book.setAuthor(author));
        return this.authorMapper.toDto(this.authorService.save(author));
    }

    public AuthorDto getById(UUID id) {
        return this.authorMapper.toDto(this.authorService.getById(id));
    }

    public Page<AuthorDto> getList(int pageNo, int pageSize) {
        Page<Author> authors = this.authorService.getList(pageNo, pageSize);
        return authors.map(this.authorMapper::toDto);
    }

    public AuthorDto update(UUID id, UpdateAuthorDto updateAuthorDto) {
        Author author = this.authorService.getById(id);
        this.authorMapper.dtoToEntity(updateAuthorDto, author);
        return this.authorMapper.toDto(this.authorService.save(author));
    }

    public void delete(UUID id) {
        this.authorService.delete(id);
    }
}
