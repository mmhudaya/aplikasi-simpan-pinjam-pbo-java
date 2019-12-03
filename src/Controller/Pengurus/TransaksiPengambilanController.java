/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Pengurus;

import Constant.Constant;
import Controller.MainController;
import Core.Controller.CorePage;
import Main.Main;
import Model.Penarikan;
import Utils.CoreStringUtils;
import Utils.FormUtils;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author Muttabi Hudaya
 */
public class TransaksiPengambilanController extends CorePage implements Initializable {

    MainController mc;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
    
    @FXML
    private JFXButton submitBtn;
    
    @FXML
    private Label responseLabel;
    
    @FXML
    private JFXTextField tfNikAnggota, tfJumlahSimpanan;
    
    @FXML
    private void onMouseClickSubmitBtn(Event ev){
        this.responseLabel.setText("");
        Penarikan penarikan = new Penarikan();
        penarikan.setJumlahUang(Double.valueOf(this.tfJumlahSimpanan.getText()));
        penarikan.setNikAnggota(this.tfNikAnggota.getText());
        
        if(!penarikan.isDapatDilakukan(penarikan.getJumlahUang())){
            System.out.println(penarikan.getJumlahMaksimalPenarikan());
            FormUtils.showLabelMessage(this.responseLabel, "Saldo tidak cukup. **Maksimal: Rp. "+CoreStringUtils.getAmountFormat(penarikan.getJumlahMaksimalPenarikan()), true);
            return;
        }
        
        if(Main.pengurus.transaksi(penarikan)){
            FormUtils.showLabelMessage(this.responseLabel, "Pengambilan sukses diajukan.", false);
            this.resetForm();
        }else{
            FormUtils.showLabelMessage(this.responseLabel, "Terjadi kesalahan", true);
        }
    }
    
    public void setPageHeader() {
        mc.setPageHeader("Transaksi Pengambilan", "Form Transaksi Pengambilan");
    }

    @Override
    public void onShown() {
        this.mc = (MainController) super.getParentController();
        this.setPageHeader();
        this.setFormValidation();
    }
    
    private void resetForm(){
        this.tfNikAnggota.setText("");
        this.tfJumlahSimpanan.setText("");
    }
    
    private void setFormValidation(){
        FormUtils.addNumberHandler(this, this.tfNikAnggota, 16, 16);
        FormUtils.addNumberMinMaxHandler(this, tfJumlahSimpanan, Constant.MIN_PEMINJAMAN, Constant.MAX_PEMINJAMAN);
    }
    
    private void isValidForm(){
        boolean valid = true;
        //Text Field
        valid = valid && FormUtils.formTextFieldIsValid(tfNikAnggota);
        valid = valid && FormUtils.formTextFieldIsValid(tfJumlahSimpanan);
        
        
        this.submitBtn.setDisable(!valid);
    }
    
    @Override
    public void onChangeAnyFormValue() {
        //TODO
        this.isValidForm();
    }
}
