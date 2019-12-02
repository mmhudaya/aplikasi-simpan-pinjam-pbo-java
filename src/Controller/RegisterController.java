/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Core.Controller.CorePage;
import Model.Pendaftar;
import Utils.FormUtils;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author Muttabi Hudaya
 */
public class RegisterController extends CorePage implements Initializable {
    //Register Form
    @FXML
    private JFXTextField tfNama, tfNik, tfPekerjaan, tfEmail, tfNoTelp, tfTempatLahir, tfAlamat;
    @FXML
    private JFXPasswordField tpfPassword, tpfConfirmPassword;
    @FXML
    private JFXComboBox<String> cbJenisKelamin, cbGolonganDarah, cbAgama;
    @FXML
    private DatePicker dpTanggalLahir;
    @FXML
    private JFXButton btnRegister;
    @FXML
    private Label errorLabel;
    
    @FXML
    private void onClickBackToLogin(Event ae){
        System.out.println("backing");
        super.getParentController().changePage("Login");
    }
    
    @FXML
    private void onClickBtnRegister(Event ev){
        Pendaftar pendaftar = new Pendaftar();
        pendaftar.setNik(tfNik.getText());
        pendaftar.setNama(tfNama.getText());
        pendaftar.setPekerjaan(tfPekerjaan.getText());
        pendaftar.setEmail(tfEmail.getText());
        pendaftar.setNoTelp(tfNoTelp.getText());
        pendaftar.setTtl(tfTempatLahir.getText() + dpTanggalLahir.getValue().format(DateTimeFormatter.ISO_DATE));
        pendaftar.setPassword(tpfPassword.getText());
        pendaftar.setJenisKelamin(cbJenisKelamin.getValue());
        pendaftar.setGolonganDarah(cbGolonganDarah.getValue());
        pendaftar.setAgama(cbAgama.getValue());
        pendaftar.setPassword(tpfPassword.getText());
        
        if(pendaftar.registrasi()){
            //berhasil registrasi
            this.errorLabel.setStyle("-fx-text-fill: #AADFAF");
            this.errorLabel.setText("Terima Kasih telah mendaftar :)");
            try{
                Thread.sleep(2000);
                super.getParentController().changePage("Login");
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }else{
            //gagal registrasi
            this.errorLabel.setStyle("-fx-text-fill: ##E47777");
            this.errorLabel.setText("NIK telah terpakai");
        }
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @Override
    public void onShown() {
        this.initComboBox();
        FormUtils.addNameHandler(this, this.tfNama, 1, 50);
        FormUtils.addNameHandler(this, this.tfTempatLahir, 1, 50);
        FormUtils.addNameHandler(this, this.tfPekerjaan, 1, 50);
        FormUtils.addLengthHandler(this, this.tfAlamat, 1, 250);
        FormUtils.addEmailHandler(this, this.tfEmail);
        FormUtils.addNumberHandler(this, this.tfNoTelp, 5, 14);
        FormUtils.addNumberHandler(this, this.tfNik, 16, 16);
        FormUtils.addLengthHandler(this, this.tpfPassword, 6, 24);
        FormUtils.addConfirmPasswordHandler(this, this.tpfPassword, this.tpfConfirmPassword);
        FormUtils.addComboBoxNotNullHandler(this, this.cbAgama);
        FormUtils.addComboBoxNotNullHandler(this, this.cbGolonganDarah);
        FormUtils.addComboBoxNotNullHandler(this, this.cbJenisKelamin);
        FormUtils.addDatePickerNotNullHandler(this, this.dpTanggalLahir);
        
        //Set min max tgl lahir
        this.dpTanggalLahir.setDayCellFactory(d ->
            new DateCell() {
                @Override public void updateItem(LocalDate item, boolean empty) {
                    super.updateItem(item, empty);
                    setDisable(item.isAfter(LocalDate.now()) || item.isBefore(LocalDate.of(1900, 1, 1)));
        }});
    }
    
    private void initComboBox(){
        String[] listValueAgama = {"Islam", "Kristen", "Katolik", "Budha", "Hindu"};
        FormUtils.initComboBox(this.cbAgama, listValueAgama );
        
        String[] listValueJenisKelamin = {"Laki-Laki", "Perempuan"};
        FormUtils.initComboBox(this.cbJenisKelamin, listValueJenisKelamin );
        
        String[] listValueGolonganDarah = {"A", "B", "AB", "O"};
        FormUtils.initComboBox(this.cbGolonganDarah, listValueGolonganDarah );
    }
    
    private void isValidForm(){
        boolean valid = true;
        //Text Field
        valid = valid && FormUtils.formTextFieldIsValid(tfNik);
        valid = valid && FormUtils.formTextFieldIsValid(tfNama);
        valid = valid && FormUtils.formTextFieldIsValid(tfPekerjaan);
        valid = valid && FormUtils.formTextFieldIsValid(tfEmail);
        valid = valid && FormUtils.formTextFieldIsValid(tfNoTelp);
        valid = valid && FormUtils.formTextFieldIsValid(tfTempatLahir);
        valid = valid && FormUtils.formTextFieldIsValid(tfAlamat);
        valid = valid && FormUtils.formTextFieldIsValid(tpfConfirmPassword);
        valid = valid && FormUtils.formTextFieldIsValid(tpfPassword);
        
        //ComboBox
        valid = valid && FormUtils.formComboBoxIsValid(cbJenisKelamin);
        valid = valid && FormUtils.formComboBoxIsValid(cbGolonganDarah);
        valid = valid && FormUtils.formComboBoxIsValid(cbAgama);
        
        //DatePicker
        valid = valid && FormUtils.formDatePickerIsValid(dpTanggalLahir);
        
        this.btnRegister.setDisable(!valid);
    }
    
    @Override
    public void onChangeAnyFormValue() {
        //TODO
        this.isValidForm();
    }
}
