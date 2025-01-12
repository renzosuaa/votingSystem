package com.mycompany.votingsystem;

import com.mycompany.votingsystem.idGenerator;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Candidate  implements sqlInfo{
    String name,partylist,position;
    int candidateID;
    
    Candidate(){
        
    } 
        // use to access candidate's info using their ID
    Candidate(int ID){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(URL,USER,PASSWORD);
            PreparedStatement ps = con.prepareStatement("Select candidateName,candidateParty, candidatePosition from dbvotingsystem.candidates where candidateID=" + "\""+ ID + "\"");
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()){
                this.name = rs.getString(1);
                this.partylist = rs.getString(2);
                this.position = rs.getString(3);
                this.candidateID = ID;
            }    
        } catch (ClassNotFoundException | SQLException ex) {    
                Logger.getLogger(Candidate.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
            // use to access candidate's info using their Name
        Candidate(String candidateName){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(URL,USER,PASSWORD);
            PreparedStatement ps = con.prepareStatement("Select candidateID,candidateParty,candidatePosition from dbVotingSystem.candidates where candidateName=" + "\"" + candidateName + "\"");
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()){
                this.candidateID = rs.getInt(1);
                this.partylist = rs.getString(2);
                this.position = rs.getString(3);
            }
        }catch(ClassNotFoundException | SQLException ex){
            Logger.getLogger(Candidate.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
         //add candidate to the database
    void addCandidate(String name, String party, String position) {
        try {
            String query = "insert into dbvotingsystem.candidates(candidateID,candidateName,candidateParty,candidatePosition) VALUES (?,?,?,?)";
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(URL,USER,PASSWORD);
            idGenerator id= new idGenerator();
                    PreparedStatement ps = con.prepareStatement(query);
                        ps.setInt(1,id.idGenerator("dbvotingsystem", "candidates","candidateID"));
                        ps.setString(2, name);
                        ps.setString(3, party);
                        ps.setString(4, position);
                        ps.executeUpdate();
        } catch (SQLIntegrityConstraintViolationException e){
            JOptionPane.showMessageDialog(null, "This Candidate Already Exist");   
        } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(Candidate.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    //returns the ID of all the Candidates in a ceratin position -- in linkedlist
    public LinkedList <Integer> listOfAllCandidates(String position){
        try {      
            Class.forName("com.mysql.cj.jdbc.Driver");
            LinkedList listNames = new LinkedList();
            Connection con = DriverManager.getConnection(URL,USER,PASSWORD);
            PreparedStatement ps = con.prepareStatement("Select candidateID from dbvotingsystem.candidates where candidatePosition= " + "\"" +position + "\"");
            ResultSet rs = ps.executeQuery();                    
            while (rs.next()){
                listNames.addFirst(rs.getInt(1));
            }  
                return listNames;
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(Candidate.class.getName()).log(Level.SEVERE, null, ex);
            }
    return null;    
    }
    
    //returns the Name, Party, and ID of all Candidates on a certain position -- in String
    public String listOfAllCandidatesInString(String position){
        try {
            String names = "";
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(URL,USER,PASSWORD);
            PreparedStatement ps = con.prepareStatement("Select candidateID from dbvotingsystem.candidates where candidatePosition=" + "\"" +position + "\"");
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                int ID = rs.getInt(1);
                Candidate candidate = new Candidate(ID);
                names += ID +" - " +candidate.name+"\t \t" +candidate.partylist + "\n";
            }   
            return names;  
        } catch (ClassNotFoundException | SQLException ex) {
        Logger.getLogger(Candidate.class.getName()).log(Level.SEVERE, null, ex);
        }
    return null;    
    }
    
    //delete a candidate to the database
    public void deleteCandidate(int ID){
        try {           
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(URL,USER,PASSWORD);
            PreparedStatement ps = con.prepareStatement("Select candidateName from dbvotingsystem.candidates where candidateID=" + ID);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()){
                PreparedStatement ps2 = con.prepareStatement("delete from dbvotingsystem.candidates where candidateID=" + ID); 
                ps2.executeUpdate();
                JOptionPane.showMessageDialog(null, "Deleted Successfully");
            } else {
                JOptionPane.showMessageDialog(null, "This Candidate Does Not Exist");
            }
        } catch (SQLIntegrityConstraintViolationException e){
            //delete the votes with the ID of this
            JOptionPane.showMessageDialog(null, "Can't Be Deleted for being a foreign key on other table (will be updated Later)");
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Candidate.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }  
}