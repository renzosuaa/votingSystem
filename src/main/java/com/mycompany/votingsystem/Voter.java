/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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

public class Voter {
    private String name, voterID;
    private final String username = "root", password = "andre619";

    public void addVoter(int ID, String Email, String Firstname, String Middlename, String Lastname, String Birthday, String Gender, String Password) throws SQLException {
        try {
            String query = "INSERT INTO Registration (VoterID, VoterEmail, VoterFirstname, VoterMiddlename, VoterLastname, VoterBirthday, VoterGender, VoterPassword) VALUES (?, ?, ?, ?, ?, ?, ?)";
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbvotingsystem", username, password);

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
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbvotingsystem",username,password);
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
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbvotingsystem",username,password);
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
}
