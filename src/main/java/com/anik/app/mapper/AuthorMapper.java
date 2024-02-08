package com.anik.app.mapper;

import com.anik.app.config.CommonMapperConfig;
import com.anik.app.dto.author.AuthorDto;
import com.anik.app.dto.author.CreateAuthorDto;
import com.anik.app.dto.author.UpdateAuthorDto;
import com.anik.app.entity.author.Author;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(config = CommonMapperConfig.class)
public interface AuthorMapper {
    Author toEntity(CreateAuthorDto authorDto);

    @Mapping(target = "books", ignore = true)
    AuthorDto toDto(Author author);

    void dtoToEntity(UpdateAuthorDto authorDto, @MappingTarget Author author);
}
