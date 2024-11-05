package com.mycompany.votingsystem;

import java.time.*;
import java.time.format.DateTimeFormatter;

public class setTimeElection {

    private String startDate, startTime, endDate, endTime;

    public boolean isWithinTime(String startDate, String startTime, String endDate, String endTime) {
        this.startDate = startDate;
        this.startTime = startTime;
        this.endDate = endDate;
        this.endTime = endTime;

        String SstartDateTime = (startDate + startTime).replaceAll("/", "").replaceAll(":", "");
        String SendDateTime = (endDate + endTime).replaceAll("/", "").replaceAll(":", "");
        
        LocalDateTime startDateTime = LocalDateTime.parse(SstartDateTime, DateTimeFormatter.ofPattern("yyyyMMddHHmm"));
        LocalDateTime endDateTime = LocalDateTime.parse( SendDateTime,DateTimeFormatter.ofPattern("yyyyMMddHHmm"));

        LocalDateTime currentDateTime = LocalDateTime.now();

        System.out.println(startDateTime +""+endDateTime);

        return (currentDateTime.isAfter(startDateTime) || currentDateTime.isEqual(startDateTime)) && currentDateTime.isBefore(endDateTime);
    }
    public boolean isStartBeforeEnd(String startDate, String startTime, String endDate, String endTime){
         this.startDate = startDate;
        this.startTime = startTime;
        this.endDate = endDate;
        this.endTime = endTime;

        String SstartDateTime = (startDate + startTime).replaceAll("/", "").replaceAll(":", "");
        String SendDateTime = (endDate + endTime).replaceAll("/", "").replaceAll(":", "");
        
        LocalDateTime startDateTime = LocalDateTime.parse(SstartDateTime, DateTimeFormatter.ofPattern("yyyyMMddHHmm"));
        LocalDateTime endDateTime = LocalDateTime.parse( SendDateTime,DateTimeFormatter.ofPattern("yyyyMMddHHmm"));

        return (startDateTime.isBefore(endDateTime));
    }
    
  }