package com.anik.app.mapper;

import com.anik.app.config.CommonMapperConfig;
import com.anik.app.dto.book.BookDto;
import com.anik.app.dto.book.CreateBookDto;
import com.anik.app.dto.book.UpdateBookDto;
import com.anik.app.entity.book.Book;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(config = CommonMapperConfig.class, uses = AuthorMapper.class)
public interface BookMapper {
    Book toEntity(CreateBookDto bookDto);

    BookDto toDto(Book book);

    void dtoToEntity(UpdateBookDto bookDto, @MappingTarget Book book);
}
