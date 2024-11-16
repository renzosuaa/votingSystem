package com.mycompany.votingsystem;

import java.time.*;
import java.time.format.DateTimeFormatter;

public class TimeFuncElection {

        private LocalDateTime ldtStartDateTime,ldtEndDateTime,ldtCurrentDateTime;
        private DateTimeFormatter format = DateTimeFormatter.ofPattern(("MM/DD/yyyy/HH:mm")) ;

    public boolean isWithinTime(LocalDateTime ldtSDT,LocalDateTime ldtEDT) {
        this.ldtStartDateTime = ldtSDT;
        this.ldtEndDateTime = ldtEDT;

        ldtCurrentDateTime = LocalDateTime.now();

        return (ldtCurrentDateTime.isAfter(ldtStartDateTime) || ldtCurrentDateTime.isEqual(ldtStartDateTime)) && ldtCurrentDateTime.isBefore(ldtEndDateTime);
    }
    public boolean isStartBeforeEnd(LocalDateTime ldtSDT,LocalDateTime ldtEDT){
        this.ldtStartDateTime = ldtSDT;
        this.ldtEndDateTime = ldtEDT;

        return (ldtStartDateTime.isBefore(ldtEndDateTime));
    }
    
}