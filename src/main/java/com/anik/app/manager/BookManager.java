package com.anik.app.manager;

import com.anik.app.dto.book.BookDto;
import com.anik.app.dto.book.CreateBookDto;
import com.anik.app.dto.book.UpdateBookDto;
import com.anik.app.entity.author.Author;
import com.anik.app.entity.book.Book;
import com.anik.app.mapper.BookMapper;
import com.anik.app.service.AuthorService;
import com.anik.app.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class BookManager {
    private final BookService bookService;
    private final AuthorService authorService;
    private final BookMapper bookMapper;

    public BookDto create(CreateBookDto createBookDto) {
        Book book = this.bookMapper.toEntity(createBookDto);
        if (createBookDto.getAuthorId() != null) {
            Author author = this.authorService.getById(createBookDto.getAuthorId());
            book.setAuthor(author);
        }
        return this.bookMapper.toDto(this.bookService.save(book));
    }

    public BookDto getById(UUID id) {
        return this.bookMapper.toDto(this.bookService.getById(id));
    }

    public Page<BookDto> getList(int pageNo, int pageSize, UUID authorId) {
        Author author = (authorId != null) ? this.authorService.getById(authorId) : null;
        Page<Book> books = this.bookService.getList(pageNo, pageSize, author);
        return books.map(this.bookMapper::toDto);
    }

    public BookDto update(UUID id, UpdateBookDto updateBookDto) {
        Book book = this.bookService.getById(id);
        this.bookMapper.dtoToEntity(updateBookDto, book);
        return this.bookMapper.toDto(this.bookService.save(book));
    }

    public void delete(UUID id) {
        this.bookService.delete(id);
    }
}
