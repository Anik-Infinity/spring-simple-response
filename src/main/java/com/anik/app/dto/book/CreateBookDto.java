package com.anik.app.dto.book;

import com.anik.app.dto.author.AuthorDto;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CreateBookDto {
    @NotNull
    private String title;
    @NotNull
    private String isbn;
    private UUID authorId;
}
