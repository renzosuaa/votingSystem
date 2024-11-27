package com.mycompany.votingsystem;

import java.time.*;
import java.time.format.DateTimeFormatter;

public class TimeFuncElection {

        private LocalDateTime ldtStartDateTime,ldtEndDateTime,ldtCurrentDateTime;
        private DateTimeFormatter format = DateTimeFormatter.ofPattern(("MM/DD/yyyy/HH:mm")) ;

        //Returns a boolean value if the current time is within the inputted start time and end time
    public boolean isWithinTime(LocalDateTime ldtSDT,LocalDateTime ldtEDT) {
        this.ldtStartDateTime = ldtSDT;
        this.ldtEndDateTime = ldtEDT;

        ldtCurrentDateTime = LocalDateTime.now();

        return (ldtCurrentDateTime.isAfter(ldtStartDateTime) || ldtCurrentDateTime.isEqual(ldtStartDateTime)) && ldtCurrentDateTime.isBefore(ldtEndDateTime);
    }
    
    //returns a bool that ensures that the start end time entered does not happen before the time start; used for ensuring that time set is feasible
    public boolean isStartBeforeEnd(LocalDateTime ldtSDT,LocalDateTime ldtEDT){
        this.ldtStartDateTime = ldtSDT;
        this.ldtEndDateTime = ldtEDT;

        return (ldtStartDateTime.isBefore(ldtEndDateTime));
    }
    
    
    
}