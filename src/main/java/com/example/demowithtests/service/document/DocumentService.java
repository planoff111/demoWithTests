package com.example.demowithtests.service.document;

import com.example.demowithtests.domain.Document;

import java.util.Optional;

public interface DocumentService {

    Document create(Document document);

    Document getById(Integer id);

    Document handlePassport(Integer id);

    Document addImage(Integer passportId, Integer imageId);

    Document setIsDeletedTrue(Integer id);


}
