/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import Core.Controller.CorePage;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

/**
 *
 * @author Muttabi Hudaya
 */
public abstract class FormUtils {
    public static boolean isValidLength(String name, int minLength, int maxLength){
        return name != null && name.length() >= minLength && name.length() <= maxLength;
    }
    
    public static boolean isValidNameForm(String name, int minLength, int maxLength){
        Pattern pattern = Pattern.compile("^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$");

        Matcher matcher = pattern.matcher(name);
        return matcher.matches() && name != null && name.length() >= minLength && name.length() <= maxLength;
    }
    
    public static boolean isValidNumber(String number, int minLength, int maxLength){
        Pattern pattern;
        if(minLength < 0){
            pattern = Pattern.compile("^[0-9]$");
        }else{
            pattern = Pattern.compile("^[0-9]{"+new String().valueOf(minLength)+","+new String().valueOf(maxLength)+"}$");   
        }

        Matcher matcher = pattern.matcher(number);
        return matcher.matches();
        
    }
    
    public static boolean isValidEmail(String text){
        Pattern pattern = Pattern.compile("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])");

        Matcher matcher = pattern.matcher(text);
        return matcher.matches();
    }
    
    public static void addLengthHandler(CorePage currentPage, TextField password, int minLength, int maxLength){
        ObservableList<String> styleClass = password.getStyleClass();
        password.textProperty().addListener((observable, oldValue, newValue) -> {
            if(!FormUtils.isValidLength(newValue, minLength, maxLength)){
                if(!styleClass.contains("form-text-error")){
                    password.setAccessibleHelp("error");
                    currentPage.onChangeAnyFormValue();
                    if(styleClass.indexOf("form-text-valid") != -1) styleClass.remove(styleClass.indexOf("form-text-valid"));
                    styleClass.add("form-text-error");
                }
            }else{
                password.setAccessibleHelp("valid");
                currentPage.onChangeAnyFormValue();
                styleClass.clear();
                styleClass.addAll("form-text", "form-text-valid");
            }
        });
    }
    
    public static void addConfirmPasswordHandler(CorePage currentPage, TextField password, TextField confirmPassword){
        ObservableList<String> styleClass = confirmPassword.getStyleClass();
        confirmPassword.textProperty().addListener((observable, oldValue, newValue) -> {
            if(!password.getText().equals(confirmPassword.getText())){
                if(!styleClass.contains("form-text-error")){
                    confirmPassword.setAccessibleHelp("error");
                    currentPage.onChangeAnyFormValue();
                    if(styleClass.indexOf("form-text-valid") != -1) styleClass.remove(styleClass.indexOf("form-text-valid"));
                    styleClass.add("form-text-error");
                }
            }else{
                styleClass.clear();
                confirmPassword.setAccessibleHelp("valid");
                currentPage.onChangeAnyFormValue();
                styleClass.addAll("form-text", "form-text-valid");
            }
        });
    }
    
    public static void addEmailHandler(CorePage currentPage, TextField textField){
        ObservableList<String> styleClass = textField.getStyleClass();
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if(!FormUtils.isValidEmail(newValue)){
                currentPage.onChangeAnyFormValue();
                textField.setAccessibleHelp("error");
                if(!styleClass.contains("form-text-error")){
                    if(styleClass.indexOf("form-text-valid") != -1) styleClass.remove(styleClass.indexOf("form-text-valid"));
                    styleClass.add("form-text-error");
                }
            }else{
                currentPage.onChangeAnyFormValue();
                textField.setAccessibleHelp("valid");
                styleClass.clear();
                styleClass.addAll("form-text", "form-text-valid");
            }
        });
    }
    
    public static void addNumberHandler(CorePage currentPage, TextField textField, int minLength, int maxLength){
        ObservableList<String> styleClass = textField.getStyleClass();
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if(!FormUtils.isValidNumber(newValue, minLength, maxLength)){
                currentPage.onChangeAnyFormValue();
                textField.setAccessibleHelp("error");
                if(!styleClass.contains("form-text-error")){
                    if(styleClass.indexOf("form-text-valid") != -1) styleClass.remove(styleClass.indexOf("form-text-valid"));
                    styleClass.add("form-text-error");
                }
            }else{
                currentPage.onChangeAnyFormValue();
                textField.setAccessibleHelp("valid");
                styleClass.clear();
                styleClass.addAll("form-text", "form-text-valid");
            }
        });
    }
    
    
    public static void addNameHandler(CorePage currentPage, TextField textField, int minLength, int maxLength){
        ObservableList<String> styleClass = textField.getStyleClass();
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if(!FormUtils.isValidNameForm(newValue, minLength, maxLength)){
                currentPage.onChangeAnyFormValue();
                textField.setAccessibleHelp("error");
                if(!styleClass.contains("form-text-error")){
                    if(styleClass.indexOf("form-text-valid") != -1) styleClass.remove(styleClass.indexOf("form-text-valid"));
                    styleClass.add("form-text-error");
                }
            }else{
                currentPage.onChangeAnyFormValue();
                styleClass.clear();
                textField.setAccessibleHelp("valid");
                styleClass.addAll("form-text", "form-text-valid");
            }
        });
    }
    
    
    public static void addComboBoxNotNullHandler(CorePage currentPage, ComboBox cb){
        ObservableList<String> styleClass = cb.getStyleClass();
        cb.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue == null){
                currentPage.onChangeAnyFormValue();
                cb.setAccessibleHelp("error");
                if(!styleClass.contains("box-combo-error")){
                    if(styleClass.indexOf("box-combo-valid") != -1) styleClass.remove(styleClass.indexOf("box-combo-valid"));
                    styleClass.add("box-combo-error");
                }
            }else{
                currentPage.onChangeAnyFormValue();
                styleClass.clear();
                cb.setAccessibleHelp("valid");
                styleClass.addAll("box-combo", "box-combo-valid");
            }
        });
    }
    
    
    public static void addDatePickerNotNullHandler(CorePage currentPage, DatePicker dp){
        ObservableList<String> styleClass = dp.getStyleClass();
        dp.valueProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue == null){
                currentPage.onChangeAnyFormValue();
                dp.setAccessibleHelp("error");
                if(!styleClass.contains("custom-date-picker-error")){
                    if(styleClass.indexOf("custom-date-picker-valid") != -1) styleClass.remove(styleClass.indexOf("custom-date-picker-valid"));
                    styleClass.add("custom-date-picker-error");
                }
            }else{
                currentPage.onChangeAnyFormValue();
                dp.setAccessibleHelp("valid");
                if(styleClass.indexOf("custom-date-picker-error") != -1) styleClass.remove(styleClass.indexOf("custom-date-picker-error"));
                styleClass.addAll("custom-date-picker", "custom-date-picker-valid");
            }
        });
    }
    
    public static boolean formTextFieldIsValid(TextField tf){
        return tf.getAccessibleHelp() != null && tf.getAccessibleHelp().equals("valid");
    }
    public static boolean formComboBoxIsValid(ComboBox cb){
        return cb.getAccessibleHelp() != null && cb.getAccessibleHelp().equals("valid");
    }
    public static boolean formDatePickerIsValid(DatePicker dp){
        return dp.getAccessibleHelp() != null && dp.getAccessibleHelp().equals("valid");
    }
    
    public static void initComboBox(ComboBox cb, Object[] values){
        cb.getItems().clear();
        cb.getItems().addAll(values);
    }
}
