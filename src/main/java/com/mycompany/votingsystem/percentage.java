package com.mycompany.votingsystem;

import java.text.DecimalFormat;

//Generates a percentage string from int values
public class percentage {
    
    private float percent;
    private final DecimalFormat df = new DecimalFormat("0.00");
    
    public float getPercentage(int value, int total){
        percent = ((float)value/(float)total)*100;
        return percent;
    }    
    
    public String getPercentString(int value, int total){
        percent = ((float)value/(float)total)*100;
        String strPercent = df.format(percent)+"%";
        return strPercent;
    }    
    public String addPercent(int value, float total){
        percent = ((float)value/total)*100;
        String strPercent = df.format(percent)+"%";
        return strPercent;
    }    
}
