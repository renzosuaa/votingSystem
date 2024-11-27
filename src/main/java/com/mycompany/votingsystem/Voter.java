package com.mycompany.votingsystem;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Voter {
    private String name, voterID;

    public void addVoter(int ID, String Email, String Firstname, String Middlename, String Lastname, String Birthday, String Gender, String Password) {
        try {
            String query = "INSERT INTO Registration (VoterID, VoterEmail, VoterFirstname, VoterMiddlename, VoterLastname, VoterBirthday, VoterGender, VoterPassword) VALUES (?, ?, ?, ?, ?, ?, ?)";
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/login", "root", "Marc0924");

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
}
   
