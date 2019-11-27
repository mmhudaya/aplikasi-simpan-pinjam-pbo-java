/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Core.Database;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Muttabi Hudaya
 */
public interface IDatabaseTable {
    void save(Object[] values);
    void update(HashMap<String, Object> values, HashMap<String, Object> whereValue);
    List getAll();
    List getAllByFilter(HashMap<String, Object> whereValue);
    Object[] get(String key, String value);
}
