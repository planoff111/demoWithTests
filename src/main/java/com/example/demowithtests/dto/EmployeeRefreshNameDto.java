package com.example.demowithtests.dto;

import java.time.Instant;
import java.util.Date;

public record EmployeeRefreshNameDto (
        Integer id,
        Date startDate,
        String messege
) {
    public EmployeeRefreshNameDto(Integer id,
                             Date startDate,
                             String messege
    ) {

        this.id = id;
        this.startDate = startDate != null ? startDate : Date.from(Instant.now());
        this.messege = "User has been refreshed with ID " + id;


    }
}
