package com.example.demowithtests.dto;

import java.time.Instant;
import java.util.Date;

public record IsRussiaDto(Integer id,
                          Date startDate,
                          String messege) {



    public IsRussiaDto(Integer id,
            Date startDate,
            String messege
    ) {

        this.id = id;
        this.startDate = startDate != null ? startDate : Date.from(Instant.now());
        this.messege = "You are russians you was deleted " + id;


    }

}