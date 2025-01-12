package com.mycompany.votingsystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Voter implements sqlInfo{
    private String name, voterID;

    public void addVoter(int ID, String Email, String Firstname, String Middlename, String Lastname, String Birthday, String Gender, String Password){
        try {
            String query = "INSERT INTO voters (VoterID, email, firstName, middleName, lastName, birthday, gender, password) VALUES (?, ?, ?, ?, ?, ?, ?,?)";
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(URL, USER, PASSWORD);

            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, ID);
            ps.setString(2, Email);
            ps.setString(3, Firstname);
            ps.setString(4, Middlename);
            ps.setString(5, Lastname);
            ps.setString(6, Birthday);
            ps.setString(7, Gender);
            ps.setString(8, Password);
            ps.executeUpdate();

        } catch (SQLIntegrityConstraintViolationException e) {
            JOptionPane.showMessageDialog(null, "This Registration Already Exists");
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Voter.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }
    
    public String getGender(int voterID){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(URL,USER,PASSWORD);
            PreparedStatement ps = con.prepareStatement("Select gender from dbvotingsystem.voters where voterID=" + "\""+ voterID + "\"");
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()){
                String gender = rs.getString(1);
                return gender; 
            }   
            
            } catch (ClassNotFoundException | SQLException ex) {    
                Logger.getLogger(Candidate.class.getName()).log(Level.SEVERE, null, ex);
            }
    return null;
    }
    
    public String getBirthday(int voterID){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(URL,USER,PASSWORD);
            PreparedStatement ps = con.prepareStatement("Select birthday from dbvotingsystem.voters where voterID=" + "\""+ voterID + "\"");
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()){
                String birthday = rs.getString(1);
                return birthday; 
            }   
            
            } catch (ClassNotFoundException | SQLException ex) {    
                Logger.getLogger(Candidate.class.getName()).log(Level.SEVERE, null, ex);
            }
    return null;
    }
    public boolean isAdmin(String adminEmail, String adminPassword){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(URL,USER,PASSWORD);
            PreparedStatement ps = con.prepareStatement("Select email, password from dbvotingsystem.voters where voterID=1000");
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()){
                if(adminEmail.equals(rs.getString(1))&&adminPassword.equals(rs.getString(2))){
                    return true;
                }
            }
          
            } catch (ClassNotFoundException | SQLException ex) {    
                Logger.getLogger(Voter.class.getName()).log(Level.SEVERE, null, ex);
            }
                return false;
    }
}
