package com.anik.app.controller;

import com.anik.app.dto.author.AuthorDto;
import com.anik.app.dto.author.CreateAuthorDto;
import com.anik.app.dto.author.UpdateAuthorDto;
import com.anik.app.manager.AuthorManager;
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
@RequestMapping("/api/v1/authors")
@RequiredArgsConstructor
public class AuthorController {
    private final AuthorManager authorManager;

    @PostMapping
    public ResponseEntity<ApiResponse> createAuthor(@RequestBody CreateAuthorDto createAuthorDto) {
        AuthorDto authorDto = this.authorManager.create(createAuthorDto);
        return ResponseUtils.buildResponse(HttpStatus.CREATED, authorDto, "Author created");
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getAuthorById(@PathVariable UUID id) {
        AuthorDto authorDto = this.authorManager.getById(id);
        return ResponseUtils.buildResponse(HttpStatus.OK, authorDto, "Author found");
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllAuthors(@RequestParam(defaultValue = "0") int pageNo,
                                                     @RequestParam(defaultValue = "10") int pageSize) {
        Page<AuthorDto> authors = this.authorManager.getList(pageNo, pageSize);
        return ResponseUtils.buildPaginatedResponse(authors, "Author List");
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateAuthorById(@PathVariable UUID id,
                                                        @Valid @RequestBody UpdateAuthorDto updateAuthorDto) {
        AuthorDto authorDto = this.authorManager.update(id, updateAuthorDto);
        return ResponseUtils.buildResponse(HttpStatus.OK, authorDto, "Author updated");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteAuthorById(@PathVariable UUID id) {
        this.authorManager.delete(id);
        return ResponseEntity.noContent().build();
    }
}
