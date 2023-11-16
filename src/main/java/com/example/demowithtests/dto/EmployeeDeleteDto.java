package com.example.demowithtests.dto;

import com.example.demowithtests.domain.Gender;

import java.time.Instant;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public record EmployeeDeleteDto(
        Integer id,
        Date startDate,
        String messege
) {
    public EmployeeDeleteDto(Integer id,
                       Date startDate,
                       String messege
                       ) {

        this.id = id;
        this.startDate = startDate != null ? startDate : Date.from(Instant.now());
        this.messege = "User has been deleted with ID " + id;


    }
}
