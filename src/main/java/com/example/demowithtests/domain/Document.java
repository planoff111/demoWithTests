package com.example.demowithtests.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "documents")
public final class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false)
    private String number;

    @Builder.Default
    private String uuid = UUID.randomUUID().toString();

    private LocalDateTime expireDate = LocalDateTime.now().plusYears(5);

    @Builder.Default
    private Boolean isHandled = Boolean.FALSE;

    private Boolean isDeleted = Boolean.FALSE;
    @Column(name = "date_add_document")
    private LocalDateTime dateAddDocument = LocalDateTime.now();
    @Column(name = "date_delete_document")
    private LocalDateTime deleteDate;


    /*@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "image_id", referencedColumnName = "id")
    private Image image;*/
}
