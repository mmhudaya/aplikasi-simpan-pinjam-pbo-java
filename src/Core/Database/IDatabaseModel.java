/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Core.Database;

/**
 *
 * @author Muttabi Hudaya
 */
public interface IDatabaseModel<T> {
    Object[] getValuesFromModelAttribute();
    T map(Object[] values);
}
