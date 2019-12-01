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
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;

/**
 * FXML Controller class
 *
 * @author Muttabi Hudaya
 */
public class RegisterController extends CorePage implements Initializable {
    //Register Form
    @FXML
    private JFXTextField tfNama, tfNik, tfPekerjaan, tfEmail, tfNoTelp, tfTempatLahir;
    @FXML
    private JFXPasswordField tpfPassword, tpfConfirmPassword;
    @FXML
    private JFXComboBox<String> cbJenisKelamin, cbGolonganDarah, cbAgama;
    @FXML
    private DatePicker dpTanggalLahir;
    @FXML
    private JFXButton btnRegister;
    
    @FXML
    private void onClickBackToLogin(Event ae){
        System.out.println("backing");
        super.getParentController().changePage("Login");
    }
    
    private void onClickBtnRegister(ActionEvent ev){
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
        
        if(pendaftar.registrasi()){
            //berhasil registrasi
        }else{
            //gagal registrasi
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
        FormUtils.addNameHandler(this.tfNama);
    }
    
}
