package com.anik.app.controller;

import com.anik.app.dto.book.BookDto;
import com.anik.app.dto.book.CreateBookDto;
import com.anik.app.dto.book.UpdateBookDto;
import com.anik.app.manager.BookManager;
import com.anik.app.response.contract.ApiResponse;
import com.anik.app.response.success.ResponseUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/books")
@RequiredArgsConstructor
public class BookController {
    private final BookManager bookManager;

    @PostMapping
    public ResponseEntity<ApiResponse> createBook(@RequestBody CreateBookDto createBookDto) {
        BookDto bookDto = this.bookManager.create(createBookDto);
        return ResponseUtils.buildResponse(HttpStatus.CREATED, bookDto, "Book created");
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getBookById(@PathVariable UUID id) {
        BookDto bookDto = this.bookManager.getById(id);
        return ResponseUtils.buildResponse(HttpStatus.OK, bookDto, "Book found");
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllBooks(@RequestParam(defaultValue = "0") int pageNo,
                                                   @RequestParam(defaultValue = "10") int pageSize,
                                                   @RequestParam(required = false) String authorId) {
        UUID authorId1 = authorId != null ? UUID.fromString(authorId) : null;
        Page<BookDto> books = this.bookManager.getList(pageNo, pageSize, authorId1);
        return ResponseUtils.buildPaginatedResponse(books, "Book List");
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateBookById(@PathVariable UUID id,
                                                      @Valid @RequestBody UpdateBookDto updateBookDto) {
        BookDto bookDto = this.bookManager.update(id, updateBookDto);
        return ResponseUtils.buildResponse(HttpStatus.OK, bookDto, "Book updated");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteBookById(@PathVariable UUID id) {
        this.bookManager.delete(id);
        return ResponseEntity.noContent().build();
    }
}
