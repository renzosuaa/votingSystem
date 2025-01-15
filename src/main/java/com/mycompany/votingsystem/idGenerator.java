package com.mycompany.votingsystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

//Generates ID numbers for voters, candidates and votes by incrementing the highest exisiting ID on the table if not a negative number
public class idGenerator implements sqlInfo{
            
    int idGenerator(String schema, String table, String IDType) {
        
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con=DriverManager.getConnection(URL,USER,PASSWORD);
            String query = "select "+ IDType+" from "+ schema +"."+table+" order by "+ IDType +" desc limit 1";
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                int id = (Integer)rs.getInt(1);
                ps.close();
                con.close();
                if (id>0){
                    return id+1;
                }                
            }
            
           switch (IDType){
            case "voterID": 
                return 1000;
            case "candidateID":
                return 1;
            case "voteID":
                return 100000;
            }   
        } catch (ClassNotFoundException | SQLException e){
            Logger.getLogger(Candidate.class.getName()).log(Level.SEVERE, null, e);
        }
       
        return 0;
    }
}
