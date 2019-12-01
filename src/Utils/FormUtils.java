/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import com.jfoenix.controls.JFXTextField;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.ObservableList;

/**
 *
 * @author Muttabi Hudaya
 */
public abstract class FormUtils {
    public static boolean isValidNameForm(String name){
        Pattern pattern = Pattern.compile("^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$");

        Matcher matcher = pattern.matcher(name);
        return matcher.matches();
    }
    
    public static boolean isValidNumber(String number, int length){
        Pattern pattern;
        if(length < 0){
            pattern = Pattern.compile("^[0-9]$");
        }else{
            pattern = Pattern.compile("^[0-9]{"+new String().valueOf(length)+"}$");   
        }

        Matcher matcher = pattern.matcher(number);
        return matcher.matches();
        
    }
    
    public static boolean isValidEmail(String text){
        Pattern pattern = Pattern.compile("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])");

        Matcher matcher = pattern.matcher(text);
        return matcher.matches();
        
    }
    
    public static void addNameHandler(JFXTextField textField){
        ObservableList<String> styleClass = textField.getStyleClass();
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if(!FormUtils.isValidNameForm(newValue)){
                if(!styleClass.contains("form-text-error")){
                    styleClass.add("form-text-error");
                }
            }else{
                if(styleClass.contains("form-text-error")){
                    styleClass.clear();
                    styleClass.add("form-text");
                }
            }
        });
    }
}
