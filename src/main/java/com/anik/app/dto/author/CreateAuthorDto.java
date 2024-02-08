package com.anik.app.dto.author;

import com.anik.app.dto.book.CreateBookDto;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CreateAuthorDto {
    @NotNull
    private String name;
    @Null
    private Set<CreateBookDto> books;
}
