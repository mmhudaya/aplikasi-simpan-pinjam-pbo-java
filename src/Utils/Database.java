/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

/**
 *
 * @author Muttabi Hudaya
 */
public class Database {
    final String DB_NAME = "simpanpinjam";
    final String DB_URL = "jdbc:mysql://localhost/"+DB_NAME;
    final String DB_USER = "root";
    final String DB_PASS = "";
    
    private Connection conn;
    
    private void connect(){
        try{
            conn = (Connection) DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
        }catch(Exception ex){
            System.out.println("Can't connect to database");
            ex.printStackTrace();
        }
    }
    
    public PreparedStatement getPrepareStatement(String query){
        try{
            this.connect();
            return conn.prepareStatement(query);
        }catch(Exception ex){
            System.out.println("Can't get statement");
            ex.printStackTrace(); 
        }
        
        return null;
    }
    
    public Statement getStatement(){
        try{
            this.connect();
            return conn.createStatement();
        }catch(Exception ex){
            System.out.println("Can't get statement");
            ex.printStackTrace(); 
        }
        
        return null;
    }
}
