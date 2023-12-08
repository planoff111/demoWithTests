package com.example.demowithtests.util.mappers;

import com.example.demowithtests.domain.Document;
import com.example.demowithtests.dto.DocumentDeleteDto;
import org.mapstruct.Mapper;

import java.util.Optional;

@Mapper(componentModel = "spring")
public interface DocumentMapper {
    DocumentDeleteDto toDeleteDto(Document document);
}
