/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Core.Controller.CorePage;
import Main.Main;
import Model.Anggota;
import Model.Pengurus;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
/**
 * FXML Controller class
 *
 * @author Muttabi Hudaya
 */
public class LoginController extends CorePage implements Initializable {
    //Link
    @FXML
    private Hyperlink aboutAppsLink;
    @FXML
    private Hyperlink creditsLink;
    @FXML
    private Hyperlink faqLink;
    
    //Login Form
    @FXML
    private JFXTextField tfNik;
    @FXML
    private JFXPasswordField tpfPassword;
    @FXML
    private Label errorLabel;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    
    @FXML
    private void onBtnMasukClick(ActionEvent ev){
        Anggota anggota = new Pengurus();
        anggota.setNik(tfNik.getText());
        anggota.setPassword(tpfPassword.getText());
        
        if(anggota.login()){
            if(anggota.isIsAktif()){
                if(anggota.isPengurus()){
                    System.out.println("pengurus");
//                    Anggota anggotaAsPengurus = new Pengurus();
                    Pengurus pengurus = (Pengurus) anggota;
                    Main.setPengurus(pengurus);
                }else{
                    System.out.println("anggota");
                    Main.setAnggota(anggota);
                }

                Main.setUserLoggedIn(true);
                super.getParentController().changeScene("MainView");
            }else{
                this.errorLabel.setText("Mohon maaf, NIK anda belum diaktifasi.");
            }
        }else{
            //TODO Show handle salah password
            this.errorLabel.setText("Silahkan memasukan NIK dan password dengan benar.");
        }
    }
    
    @FXML
    private void onBtnRegisterClick(ActionEvent ev){
        super.getParentController().changePage("Register");
    }
    
    @FXML
    private void onClickLink(ActionEvent ev){
        if(ev.getSource() == this.aboutAppsLink){
            super.getParentController().changePage("AboutApps");
        }else if(ev.getSource() == this.creditsLink){
            super.getParentController().changePage("Credits");
        }else if(ev.getSource() == this.faqLink){
            super.getParentController().changePage("FAQ");
        }
    }

    @Override
    public void onShown() {
        //nothing
    }
    
    @Override
    public void onChangeAnyFormValue() {
        //TODO
    }
    
}
