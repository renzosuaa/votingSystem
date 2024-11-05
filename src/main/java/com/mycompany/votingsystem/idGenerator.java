package com.mycompany.votingsystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class idGenerator{
    private String schema,table;
    
    int idGenerator(String schema, String table) throws ClassNotFoundException, SQLException{
        
        this.schema = schema;
        this.table = table;
        
        
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/"+schema,"root","renzo072");
        //Make a SQL query to display the current largest ID number
        String query = "select ID from "+ schema +"."+table+" order by id desc limit 1";
        PreparedStatement ps = con.prepareStatement(query);
        
        ResultSet rs = ps.executeQuery();
        if (rs.next()){
            int id = (Integer)rs.getInt(1);
            if(id == 000){
                ps.close();
                con.close();
                return 1;
            }
            ps.close();
            con.close();
            return id+1;
        }
        return 0;
    }
}
