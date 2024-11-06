
package com.mycompany.votingsystem;

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


public class Candidate {
    private String name,candidateID,partylist,position;
    private LinkedList listCandidateNames;
    
    
    public String getCandidateName(int candidateID){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbvotingsystem","root","renzo072");
            PreparedStatement ps = con.prepareStatement("Select candidateName from dbvotingsystem.candidates where candidateID=" + "\""+ candidateID + "\"");
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()){
                name = rs.getString(1);
            }   
            return name; 
            
            } catch (ClassNotFoundException | SQLException ex) {    
                Logger.getLogger(Candidate.class.getName()).log(Level.SEVERE, null, ex);
            }
    return null;
    }
    
    public String getCandidateParty(int candidateID){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbvotingsystem","root","renzo072");
            PreparedStatement ps = con.prepareStatement("Select candidateParty from dbvotingsystem.candidates where candidateID=" + "\""+ candidateID + "\"");
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                partylist = rs.getString(1);
            }   
            return partylist;  
            
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Candidate.class.getName()).log(Level.SEVERE, null, ex);   
        }
        
    return null;
    }
    
    public String getCandidatePosition(int candidateID){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbvotingsystem","root","renzo072");
            PreparedStatement ps = con.prepareStatement("Select candidatePosition from dbvotingsystem.candidates where candidateID=" + "\""+ candidateID + "\"");               
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                name = rs.getString(1);
            }   
            return position;  
            } catch (ClassNotFoundException | SQLException ex) { 
                Logger.getLogger(Candidate.class.getName()).log(Level.SEVERE, null, ex);
            }
    return null;
    }
    
    public LinkedList <String> positionParticipantsIDLinkedList(String position){
        try {      
            Class.forName("com.mysql.cj.jdbc.Driver");
            LinkedList listNames = new LinkedList();
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbvotingsystem","root","renzo072");
            PreparedStatement ps = con.prepareStatement("Select candidateID from dbvotingsystem.candidates where candidatePosition= " + "\"" +position + "\"");
            ResultSet rs = ps.executeQuery();                    
            while (rs.next()){
                listNames.addFirst(rs.getString(1));
            }  
                return listNames;
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(Candidate.class.getName()).log(Level.SEVERE, null, ex);
            }
    return null;    
    }
    
    public String positionParticipantsString(String position){
        try {
            String names = "";
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbvotingsystem","root","renzo072");
            PreparedStatement ps = con.prepareStatement("Select candidateID from dbvotingsystem.candidates where candidatePosition=" + "\"" +position + "\"");
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                int ID = rs.getInt(1);
                names += ID +" - " +getCandidateName(ID)+"\t \t" +getCandidateParty(ID) + "\n";
            }   
            return names;  
        } catch (ClassNotFoundException | SQLException ex) {
        Logger.getLogger(Candidate.class.getName()).log(Level.SEVERE, null, ex);
        }
    return null;    
    }
    
    public void addCandidate(String name, String party, String position) {
        try {
            String query = "insert into dbvotingsystem.candidates(candidateID,candidateName,candidateParty,candidatePosition) VALUES (?,?,?,?)";
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbvotingsystem","root","renzo072");
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
    
    public void deleteCandidate(int ID){
        try {           
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbvotingsystem","root","renzo072");
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
