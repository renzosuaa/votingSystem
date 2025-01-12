package com.mycompany.votingsystem;

import java.text.DecimalFormat;

public class percentage {
    
    float percent;
    DecimalFormat df = new DecimalFormat("0.00");
    
    public float getPercentage(int value, int total){
        
        percent = ((float)value/(float)total)*100;
        return percent;
    }    
    
    public String getPercentString(int value, int total){
        percent = ((float)value/(float)total)*100;
        String strPercent = df.format(percent)+"%";
        return strPercent;
    }    
}
