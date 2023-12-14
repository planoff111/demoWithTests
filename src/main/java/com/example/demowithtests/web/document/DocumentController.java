package com.example.demowithtests.web.document;

import com.example.demowithtests.domain.Document;
import com.example.demowithtests.dto.DocumentDeleteDto;
import com.example.demowithtests.dto.DocumentDto;
import com.example.demowithtests.service.document.DocumentService;
import com.example.demowithtests.util.mappers.DocumentMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
public class DocumentController implements DocumentResource {

    private final DocumentService documentService;
    private final DocumentMapper documentMapper;

    /**
     * @param document
     * @return
     */
    @Override
    @PostMapping("/documents")
    @ResponseStatus(HttpStatus.CREATED)
    public Document createDocument(@RequestBody Document document) {
        Document saved = documentService.create(document);
        return saved;
    }

    /**
     * @param id
     * @return
     */

    @GetMapping("/documents/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Document getDocumentById(@PathVariable Integer id) {
        return documentService.getById(id);
    }

    @GetMapping("/documents/history/{id}")
    @ResponseStatus(HttpStatus.OK)
    public DocumentDto getDocumentByIdHistory(@PathVariable Integer id) {
        Document document = documentService.getById(id);
        DocumentDto documentDto = documentMapper.toDocumentDto(document);
        return documentDto;
    }

    @DeleteMapping("/documents/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public DocumentDeleteDto deleteDocument(@PathVariable Integer id) {
        Document deleted = documentService.setIsDeletedTrue(id);
        return documentMapper.toDeleteDto(deleted);
    }
}
