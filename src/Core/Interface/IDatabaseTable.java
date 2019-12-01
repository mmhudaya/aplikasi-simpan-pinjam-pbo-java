/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Core.Interface;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Muttabi Hudaya
 */
public interface IDatabaseTable {
    boolean save(HashMap<String, Object> values);
    boolean update(HashMap<String, Object> values, HashMap<String, Object> whereValues);
    boolean delete(HashMap<String, Object> whereValues);
    List getAll();
    List getAllByFilter(HashMap<String, Object> whereValue);
    Object[] get(String key, Object value);
    
    void customCUDQuery(String query);
    List<Object[]> customReadQuery(String query, int columnLength);
    Object[] customReadOneQuery(String query, int columnLength);
}
