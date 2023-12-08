package com.example.demowithtests.service.document;

import com.example.demowithtests.domain.Document;
import com.example.demowithtests.repository.DocumentRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class DocumentServiceBean implements DocumentService {

    private final DocumentRepository documentRepository;

    /**
     * @param document
     * @return
     */
    @Override
    public Document create(Document document) {
        return documentRepository.saveAndFlush(document);
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Document getById(Integer id) {
        return documentRepository.findById(id).orElseThrow();
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Document handlePassport(Integer id) {
        Document document = getById(id);
        if (document.getIsHandled()) {
            throw new RuntimeException();
        } else document.setIsHandled(Boolean.TRUE);
        return documentRepository.save(document);
    }

    /**
     * @param passportId
     * @param imageId
     * @return
     */
    @Override
    public Document addImage(Integer passportId, Integer imageId) {
        return null;
    }

    @Override
    public Document setIsDeletedTrue(Integer id) {
        var document = documentRepository.findById(id)
                .map(entity -> {
                    entity.setDeleteDate(LocalDateTime.now());
                    entity.setIsDeleted(Boolean.TRUE);
                    return documentRepository.saveAndFlush(entity);
                }).orElseThrow(() -> new EntityNotFoundException("Document not found with id = " + id));
        return document;
    }

}
