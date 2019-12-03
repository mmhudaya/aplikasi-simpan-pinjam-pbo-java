/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Core.Database;

import Core.Interface.IDatabaseTable;
import Utils.Database;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Muttabi Hudaya
 */
public abstract class CoreDatabaseTable<T> implements IDatabaseTable{
    protected String tableName;
    protected String[] columnNames;
    protected Database db = new Database();

    public CoreDatabaseTable(String tableName, String[] columnNames){
        this.tableName = tableName;
        this.columnNames = columnNames;
    }
    
    //Must return instanced it generic object
    public abstract T map(Object[] values);
    protected abstract HashMap<String, Object> getValuesFromModelAttribute();
    
    public T selectOneByFilter(HashMap<String, Object> whereValues) {
        return this.map(this.getOneByFilter(whereValues));
    }
    
    public T selectOne(String key, Object value){
        return this.map(this.get(key, value));
    }
    
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
    
    
    public List<Object[]> getAllObject() {
        String query = this.getSelectAllQuery();
        List<Object[]> rows = new ArrayList<>();
        
        try{
            ResultSet rs = this.db.getStatement().executeQuery(query);
            while(rs.next()){
                rows.add(this.getRow(rs));
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
        
        System.out.println(query);
        try{
            PreparedStatement pst = this.db.getPrepareStatement(query);
            pst = this.getAllPreparementStatement(pst, whereValue);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                rows.add(this.map(this.getRow(rs)));
            }
            
            return rows;
        }catch(Exception ex){
            ex.printStackTrace();
        }
        
        return null;
    }
    
    public List<Object[]> getAllObjectByFilter(HashMap<String, Object> whereValue) {
        String query = this.getSelectAllQuery() +" "+this.getWhereClauseQuery(whereValue);
        List<Object[]> rows = new ArrayList<>();
        
        System.out.println(query);
        try{
            PreparedStatement pst = this.db.getPrepareStatement(query);
            pst = this.getAllPreparementStatement(pst, whereValue);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                rows.add(this.getRow(rs));
            }
            
            return rows;
        }catch(Exception ex){
            ex.printStackTrace();
        }
        
        return null;
    }

    @Override
    public Object[] get(String key, Object value) {
        String query = this.getSelectOneQuery(key);
        
        try{
            PreparedStatement pst = this.db.getPrepareStatement(query);
            pst = this.getPreparedStatementType(pst, 1, value);
            ResultSet rs = pst.executeQuery();
            if(rs.next()){
                return this.getRow(rs);
            }
            
        }catch(Exception ex){
            ex.printStackTrace();
        }
        
        return null;
    }
    
    public Object[] getOneByFilter(HashMap<String, Object> whereValues) {
        String query = this.getSelectOneWithFilterQuery(whereValues);
        
        try{
            PreparedStatement pst = this.db.getPrepareStatement(query);
            pst = this.getAllPreparementStatement(pst, whereValues);
            ResultSet rs = pst.executeQuery();
            if(rs.next()){
                return this.getRow(rs);
            }
            
        }catch(Exception ex){
            ex.printStackTrace();
        }
        
        return null;
    }
    
    
    @Override
    public boolean save(HashMap<String, Object> values) {
        String query = this.getInsertQuery(values);
        try{
            PreparedStatement pst = this.db.getPrepareStatement(query);
            pst = this.getAllInsertPreparementStatement(pst, values);
            int rowInserted = pst.executeUpdate();
            
            if(rowInserted > 0){
                System.out.println("Success Insert");
                return true;
            }else{
                System.out.println("Something Error occured");
                return false;
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(HashMap values, HashMap whereValues) {
        String query = this.getUpdateQuery(values, whereValues);
        
        try{
            PreparedStatement pst = this.db.getPrepareStatement(query);
            pst = this.getAllUpdatePreparementStatement(pst, values, whereValues);
            int rowUpdated = pst.executeUpdate();
            
            if(rowUpdated > 0){
                System.out.println("Success Update");
                return true;
            }else{
                System.out.println("Something Error occured");
                return false;
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return false;
        
    }
    
    

    @Override
    public boolean delete(HashMap<String, Object> whereValues) {
        String query = this.getDeleteQuery(whereValues);
        
        try{
            PreparedStatement pst = this.db.getPrepareStatement(query);
            pst = this.getAllPreparementStatement(pst, whereValues);
            int rowUpdated = pst.executeUpdate();
            
            if(rowUpdated > 0){
                System.out.println("Success Update");
                return true;
            }else{
                System.out.println("Something Error occured");
                return false;
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return false;
        
    }

    @Override
    public boolean customCUDQuery(String query) {
        try{
            int rowUpdated = this.db.getStatement().executeUpdate(query);
            
            if(rowUpdated > 0){
                System.out.println("Success custom query execution");
                return true;
            }else{
                System.out.println("Something Error occured");
                return false;
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Object[]> customReadQuery(String query, int columnLength) {
        List<Object[]> rows = new ArrayList<>();
        try{
            ResultSet rs = this.db.getStatement().executeQuery(query);
            
            while(rs.next()){
                Object[] row = new Object[columnLength];
                for(int i=0; i< columnLength; i++){
                    row[i] = rs.getObject(i+1);
                }
                rows.add(row);
            }
            
            return rows;
        }catch(Exception ex){
            ex.printStackTrace();
        }
        
        return null;
    }
    
    @Override
    public Object[] customReadOneQuery(String query, int columnLength) {
        try{
            System.out.println(query);
            ResultSet rs = this.db.getStatement().executeQuery(query);
            Object[] row = new Object[columnLength];
            if(rs.next()){
                for(int i=0; i< columnLength; i++){
                    row[i] = rs.getObject(i+1);
                }
            }
            
            return row;
        }catch(Exception ex){
            ex.printStackTrace();
        }
        
        return null;
    }
    
    
    
    //Utility
    private Object[] getRow(ResultSet rs) throws Exception{
        Object[] row = new Object[this.columnNames.length];
        for (int i = 0; i < this.columnNames.length; i++) {
            row[i] = rs.getObject(i+1);
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
        }else if(value instanceof Timestamp){
            pst.setTimestamp(index,(Timestamp) value);
        }else if(value instanceof Double){
            pst.setDouble(index,(Double) value);
        }else if(value instanceof java.util.Date){
            java.util.Date utilDate = (java.util.Date) value;
            pst.setDate(index, (Date) new java.sql.Date(utilDate.getTime()));
        }else{
            System.out.println("here");
            System.out.println(index);
            System.out.println(value.getClass());
        }
        
        return pst;
    }
    
    
    private PreparedStatement getAllInsertPreparementStatement(PreparedStatement pst, HashMap<String, Object> values) throws Exception{
        Iterator it = values.entrySet().iterator();
        int index = 1;
        
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            if(pair.getValue() != null){
                pst = this.getPreparedStatementType(pst, index, pair.getValue());
                index++;
            }
            it.remove(); // avoids a ConcurrentModificationException
        }
        
        return pst;
    }
    
    private PreparedStatement getAllUpdatePreparementStatement(PreparedStatement pst, HashMap<String, Object> values, HashMap<String, Object> whereValues) throws Exception{
        Iterator it = values.entrySet().iterator();
        int index = 1;
        
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            pst = this.getPreparedStatementType(pst, index, pair.getValue());
            it.remove(); // avoids a ConcurrentModificationException
            index++;
        }
        
        it = whereValues.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            pst = this.getPreparedStatementType(pst, index, pair.getValue());
            it.remove(); // avoids a ConcurrentModificationException
            index++;
        }
        
        return pst;
    }
    
    private PreparedStatement getAllPreparementStatement(PreparedStatement pst, HashMap<String, Object> whereValue) throws Exception{
        Iterator it = whereValue.entrySet().iterator();
        int index = 1;
        
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            pst = this.getPreparedStatementType(pst, index, pair.getValue());
            it.remove(); // avoids a ConcurrentModificationException
            index++;
        }
        
        return pst;
    }
    
    private String getInsertQuery(HashMap<String, Object> values){
        String query = "INSERT INTO `"+this.tableName+"` ";
        String queryValues = "";
        String columnQuery = "";
        
        Iterator it = values.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            if(pair.getValue() != null){
                columnQuery += "`"+pair.getKey() + "`,";
                queryValues += "?,";
            }
//            it.remove(); // avoids a ConcurrentModificationException/
        }
        
        if (queryValues != null && queryValues.length() > 0) {
            queryValues = queryValues.substring(0, queryValues.length() - 1);
        }
        if (columnQuery != null && columnQuery.length() > 0) {
            columnQuery = columnQuery.substring(0, columnQuery.length() - 1);
        }
        
        query+= "(" + columnQuery + ") VALUES ("+ queryValues +")"; 
        
        
        System.out.println(query);
        return query;
    }
    
    private String getUpdateQuery(HashMap values, HashMap whereValue){
        String query = "UPDATE "+this.tableName+" SET "; //password=?, fullname=?, email=? WHERE username=?";
        
        //SET query
        Iterator it = values.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            query+= "`"+pair.getKey().toString()+"`=?, ";
        }
        
        //Remove last comma
        query = query.substring(0, query.length() - ", ".length());
        
        //get where clause
        query += this.getWhereClauseQuery(whereValue);
        
        System.out.println(query);
        return query;
    }
    
    
    
    private String getDeleteQuery(HashMap whereValue){
        String query = "DELETE FROM "+this.tableName; //password=?, fullname=?, email=? WHERE username=?";
        
        //get where clause
        query += this.getWhereClauseQuery(whereValue);
        
        System.out.println(query);
        return query;
    }
    
    private String getWhereClauseQuery(HashMap whereValue){
        //WHERE clause query
        String query = " WHERE ";
        Iterator it = whereValue.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            query+= "`"+pair.getKey()+"`=? AND ";
        }
        
        //Remove last AND operator
        query = query.substring(0, query.length() - " AND ".length());
        return query;
    }
    
    
    
    private String getSelectOneWithFilterQuery(HashMap whereValue){
        String query = "SELECT * FROM `"+this.tableName+"`";
        String whereClause = this.getWhereClauseQuery(whereValue);
        query += whereClause + " LIMIT 1";
        
        System.out.println(query);
        return query;
    }
    
    private String getSelectOneQuery(String key){
        String query = "SELECT * FROM `"+this.tableName+"` WHERE `"+key+"`=? LIMIT 1";
        return query;
    }

    private String getSelectAllQuery(){
        return "SELECT * FROM `"+this.tableName+"`";
    }

}
