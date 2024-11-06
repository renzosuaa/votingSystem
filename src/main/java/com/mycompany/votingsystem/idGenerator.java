package com.mycompany.votingsystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class idGenerator{
    private String schema,table;
    
    int idGenerator(String schema, String table, String IDType) {
        this.schema = schema;
        this.table = table;
        
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/"+schema,"root","renzo072");
            //Make a SQL query to display the current largest ID number
            String query = "select "+ IDType+" from "+ schema +"."+table+" order by "+ IDType +" desc limit 1";
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                int id = (Integer)rs.getInt(1);
                ps.close();
                con.close();
                if (IDType.equals("voteID")){
                    return id-1;
                }
                return id+1;
            }
        } catch (ClassNotFoundException | SQLException e){
            Logger.getLogger(Candidate.class.getName()).log(Level.SEVERE, null, e);
        }
        switch (IDType){
            case "voterID": 
                return 1000;
            case "candidateID":
                return 1;
            case "voteID":
                return 99999;

        }
        return 0;
    }
}
