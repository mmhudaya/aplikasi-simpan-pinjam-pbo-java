/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Core.Database;

import Utils.Database;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 *
 * @author Muttabi Hudaya
 */
public abstract class CoreDatabaseTable<T extends IDatabaseModel> implements IDatabaseTable{
    protected String tableName;
    protected String[] columnNames;
    protected Database db;

    public CoreDatabaseTable(String tableName, String[] columnNames){
        this.db = new Database();
        this.tableName = tableName;
        this.columnNames = columnNames;
    }
    
    //Must return instanced it generic object
    public abstract T map(Object[] values);
    
    @Override
    public List<T> getAll() {
        String query = this.getSelectAllQuery();
        List<T> rows = new ArrayList<>();
        
        try{
            ResultSet rs = this.db.getStatement().executeQuery(query);
            while(rs.next()){
                rows.add(this.map(this.getRow(rs)));
            }
            
            return rows;
        }catch(Exception ex){
            ex.printStackTrace();
        }
        
        return null;
    }
    
    @Override
    public List<T> getAllByFilter(HashMap<String, Object> whereValue) {
        String query = this.getSelectAllQuery() +" "+this.getWhereClauseQuery(whereValue);
        List<T> rows = new ArrayList<>();
        
        try{
            PreparedStatement pst = this.db.getPrepareStatement(query);
            pst = this.getAllPreparementStatement(pst, whereValue);
            
            ResultSet rs = pst.executeQuery(query);
            while(rs.next()){
                rows.add(this.map(this.getRow(rs)));
            }
            
            return rows;
        }catch(Exception ex){
            ex.printStackTrace();
        }
        
        return null;
    }

    @Override
    public Object[] get(String key, String value) {
        String query = this.getSelectOneQuery(key);
        
        try{
            PreparedStatement pst = this.db.getPrepareStatement(query);
            pst = this.getPreparedStatementType(pst, 1, value);
            ResultSet rs = pst.executeQuery(query);
            if(rs.next()){
                return this.getRow(rs);
            }
            
        }catch(Exception ex){
            ex.printStackTrace();
        }
        
        return null;
    }
    
    @Override
    public void save(Object[] values) {
        String query = this.getInsertQuery(values);
        try{
            PreparedStatement pst = this.db.getPrepareStatement(query);
            pst = this.getAllPreparementStatement(pst, values);
            int rowInserted = pst.executeUpdate();
            
            if(rowInserted > 0){
                System.out.println("Success Insert");
            }else{
                System.out.println("Something Error occured");
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    @Override
    public void update(HashMap values, HashMap whereValues) {
        String query = this.getUpdateQuery(values, whereValues);
        
        
        try{
            PreparedStatement pst = this.db.getPrepareStatement(query);
            pst = this.getAllPreparementStatement(pst, whereValues);
            int rowUpdated = pst.executeUpdate();
            
            if(rowUpdated > 0){
                System.out.println("Success Update");
            }else{
                System.out.println("Something Error occured");
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        
    }
    
    //Utility
    private Object[] getRow(ResultSet rs) throws Exception{
        Object[] row = new Object[this.columnNames.length];
        for (int i = 0; i < this.columnNames.length; i++) {
            String tableAttribute = this.columnNames[i];
            row[i] = rs.getObject(i);
        }
        return row;
    }
    
    private PreparedStatement getPreparedStatementType(PreparedStatement pst, int index, Object value) throws Exception{
        if(value instanceof String){
            pst.setString(index, (String) value);
        }else if(value instanceof Float){
            pst.setFloat(index, (Float) value);
        }else if(value instanceof Boolean){
            pst.setBoolean(index, (Boolean) value);
        }else if(value instanceof Date){
            pst.setDate(index, (Date) value);
        }else if(value instanceof Integer){
            pst.setInt(index, (Integer) value);
        }else if(value instanceof Long){
            pst.setLong(index, (Long) value);
        }
        
        return pst;
    }
    
    
    private PreparedStatement getAllPreparementStatement(PreparedStatement pst, Object[] values) throws Exception{
        for (int i = 0; i < values.length; i++) {
            pst = this.getPreparedStatementType(pst, i, values[i]);
        }
        
        return pst;
    }
    
    private PreparedStatement getAllPreparementStatement(PreparedStatement pst, HashMap<String, Object> whereValue) throws Exception{
        Iterator it = whereValue.entrySet().iterator();
        int index = 0;
        
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            pst = this.getPreparedStatementType(pst, index, pair.getValue());
            it.remove(); // avoids a ConcurrentModificationException
            index++;
        }
        
        return pst;
    }
    
    private String getInsertQuery(Object[] values){
        String query = "INSERT INTO `"+this.tableName+"` VALUES (?";
        String repeated = new String(new char[values.length-1]).replace("\0", ",?");
        query += repeated + ")";
        
        return query;
    }
    
    private String getUpdateQuery(HashMap values, HashMap whereValue){
        String query = "UPDATE "+this.tableName+" SET "; //password=?, fullname=?, email=? WHERE username=?";
        
        //SET query
        Iterator it = values.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            query+= "`"+pair.getKey().toString()+"`=?, ";
            it.remove(); // avoids a ConcurrentModificationException
        }
        
        //Remove last comma
        query = query.substring(0, query.length() - ", ".length());
        
        //get where clause
        query += this.getWhereClauseQuery(whereValue);
        
        return query;
    }
    
    private String getWhereClauseQuery(HashMap whereValue){
        //WHERE clause query
        String query = "";
        Iterator it = whereValue.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            query+= "`"+pair.getKey().toString()+"`=? AND ";
            it.remove(); // avoids a ConcurrentModificationException
        }
        
        //Remove last AND operator
        query = query.substring(0, " AND ".length() - 1);
        
        return query;
    }
    
    private String getSelectOneQuery(String key){
        String query = "SELECT * FROM "+this.tableName+" WHERE `"+key+"`=?";
        return query;
    }

    private String getSelectAllQuery(){
        return "SELECT * FROM "+this.tableName;
    }

}
