package com.example.demowithtests.dto;

import java.time.Instant;
import java.util.Date;

public record DocumentDeleteDto(Integer id,
                                Date startDate,
                                String messege) {

    public  DocumentDeleteDto(Integer id,
                             Date startDate,
                             String messege
    ) {

        this.id = id;
        this.startDate = startDate != null ? startDate : Date.from(Instant.now());
        this.messege = "Document has been deleted with ID " + id;


    }
}
