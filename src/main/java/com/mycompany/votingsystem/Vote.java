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


public class Vote implements sqlInfo {
    int voterID, candidateID, voteID;
    

    
    Vote(){};
    
    //use to access a vote informations using the voteID
    Vote(int voteID){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(URL,USER,PASSWORD);
            PreparedStatement ps = con.prepareStatement("Select voterID,candidateID from dbVotingSystem.vote where candidateName=" + "\"" + voteID + "\"");
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()){
                this.voteID = voteID;
                this.voterID = rs.getInt(1);
                this.candidateID = rs.getInt(2);
            }
        }catch(ClassNotFoundException | SQLException ex){
            Logger.getLogger(Candidate.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    
    // use to register or add vote to the database
    Vote(int voteID, int voterID, int candidateID){
        try {
            String query = "insert into dbvotingsystem.vote(voteID,voterID,candidateID) VALUES (?,?,?)";
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(URL,USER,PASSWORD);
                    PreparedStatement ps = con.prepareStatement(query);
                        ps.setInt(1,voteID);
                        ps.setInt(2,voterID);
                        ps.setInt(3,candidateID);
                        ps.executeUpdate();
        } catch (SQLIntegrityConstraintViolationException e){
            JOptionPane.showMessageDialog(null, "This Vote Already Exist");   
        } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(Candidate.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

}

