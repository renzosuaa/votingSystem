package com.mycompany.votingsystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class TimeFuncElection {

   private LocalDateTime ldtStartDateTime,ldtEndDateTime,ldtCurrentDateTime;
    private DateTimeFormatter format = DateTimeFormatter.ofPattern(("MM/DD/yyyy/HH:mm")) ;
   // for Database
    static final String URL = "jdbc:mysql://localhost:3306/dbvotingsystem";
    static final String USER = "root"; 
    static final String PASSWORD = "andre619";  
    
        //Returns a boolean value if the current time is within the inputted start time and end time
    public boolean isWithinTime() {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(URL,USER,PASSWORD);
            PreparedStatement ps = con.prepareStatement("Select startDateTime,endDateTime from dbvotingsystem.electiondate");
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                String strStartDateTime = rs.getString(1);
                ldtStartDateTime = LocalDateTime.parse(strStartDateTime,DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm"));
                String strEndDateTime = rs.getString(2);
                ldtEndDateTime = LocalDateTime.parse(strEndDateTime,DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm"));
                con.close();
                ldtCurrentDateTime = LocalDateTime.now();
                return (ldtCurrentDateTime.isAfter(ldtStartDateTime) || ldtCurrentDateTime.isEqual(ldtStartDateTime)) && ldtCurrentDateTime.isBefore(ldtEndDateTime);
            }   
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Candidate.class.getName()).log(Level.SEVERE, null, ex);   
        } 
    return false;
    }
    
    public void setTime(String StartDateTime, String EndDateTime){
        try{
            ldtStartDateTime = LocalDateTime.parse(StartDateTime,DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm"));
            ldtEndDateTime = LocalDateTime.parse(EndDateTime,DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm"));
            //checks whether the election dates are correct (End date is farther than Start date; is not same)
            if(isStartBeforeEnd(ldtStartDateTime,ldtEndDateTime)){
                try{
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection con = DriverManager.getConnection(URL,USER,PASSWORD);
                        PreparedStatement psClear = con.prepareStatement("Truncate Table dbvotingsystem.electiondate");
                        psClear.execute();
                        PreparedStatement ps = con.prepareStatement("Insert into electiondate (startDateTime, endDateTime) values (?,?)");
                        ps.setString(1,StartDateTime);
                        ps.setString(2,EndDateTime);
                        ps.execute();
                        JOptionPane.showMessageDialog(null, "Election dates set, and is now running.", "Date and Time Confirmed", JOptionPane.INFORMATION_MESSAGE);
                    
                } catch (ClassNotFoundException | SQLException ex) {
                    Logger.getLogger(Candidate.class.getName()).log(Level.SEVERE, null, ex);   
                }
            }
            else{
                JOptionPane.showMessageDialog(null, "Date entered is invalid. Please enter valid date and time.","Error",JOptionPane.ERROR_MESSAGE);
            }          
        }
        catch(DateTimeException er){
            JOptionPane.showMessageDialog(null, "Date entered is invalid. Please follow the correct format: \nDate: MM/dd/yyyy \nTime: HH:mm","Error",JOptionPane.ERROR_MESSAGE);
        }        
    }
    public void forceStop(){
        try{
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection con = DriverManager.getConnection(URL,USER,PASSWORD);
                        PreparedStatement psClear = con.prepareStatement("Truncate Table dbvotingsystem.electiondate");
                        psClear.execute();
                        JOptionPane.showMessageDialog(null, "The Elections have benn forcefully stopped.", "Force Stop", JOptionPane.INFORMATION_MESSAGE);
                } catch (ClassNotFoundException | SQLException ex) {
                    Logger.getLogger(Candidate.class.getName()).log(Level.SEVERE, null, ex);   
                }
    }
    
    
    //returns a bool that ensures that the start end time entered does not happen before the time start; used for ensuring that time set is feasible
    public boolean isStartBeforeEnd(LocalDateTime ldtSDT,LocalDateTime ldtEDT){
        this.ldtStartDateTime = ldtSDT;
        this.ldtEndDateTime = ldtEDT;
        return (ldtStartDateTime.isBefore(ldtEndDateTime));
    }   
}