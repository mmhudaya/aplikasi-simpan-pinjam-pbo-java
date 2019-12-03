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
import Model.Simpanan;
import Utils.FormUtils;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
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
public class TransaksiSimpanController extends CorePage implements Initializable {

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
    private JFXComboBox cbTipeSimpanan;
    
    @FXML
    private void onMouseClickSubmitBtn(Event ev){
        Simpanan simpanan = new Simpanan();
        simpanan.setTipeSimpanan(Constant.TIPE_SIMPANAN.get(this.cbTipeSimpanan.getValue()));
        simpanan.setJumlahUang(Double.valueOf(this.tfJumlahSimpanan.getText()));
        simpanan.setNikAnggota(this.tfNikAnggota.getText());
        
        if(Main.pengurus.transaksi(simpanan)){
            FormUtils.showLabelMessage(this.responseLabel, "Penyimpanan saldo sukses dilakukan.", false);
            this.resetForm();
        }else{
            FormUtils.showLabelMessage(this.responseLabel, "Terjadi kesalahan", true);
        }
    }
    
    public void setPageHeader() {
        mc.setPageHeader("Transaksi Simpanan", "Form Transaksi Simpanan");
    }

    @Override
    public void onShown() {
        this.mc = (MainController) super.getParentController();
        this.setPageHeader();
        this.setFormValidation();
        this.initComboBox();
    }
    
    private void resetForm(){
        this.tfNikAnggota.setText("");
        this.tfNikAnggota.setText("");
        this.cbTipeSimpanan.getSelectionModel().clearSelection();
    }
    
    private void initComboBox(){
        String[] listTenor = {"Wajib", "Pokok", "Sukarela"};
        FormUtils.initComboBox(this.cbTipeSimpanan, listTenor );
    }
    
    private void setFormValidation(){
        FormUtils.addNumberHandler(this, this.tfNikAnggota, 16, 16);
        FormUtils.addNumberMinMaxHandler(this, tfJumlahSimpanan, Constant.MIN_PEMINJAMAN, Constant.MAX_PEMINJAMAN);
        FormUtils.addComboBoxNotNullHandler(this, this.cbTipeSimpanan);
    }
    
    private void isValidForm(){
        boolean valid = true;
        //Text Field
        valid = valid && FormUtils.formTextFieldIsValid(tfNikAnggota);
        valid = valid && FormUtils.formTextFieldIsValid(tfJumlahSimpanan);
        
        //ComboBox
        valid = valid && FormUtils.formComboBoxIsValid(cbTipeSimpanan);
        
        this.submitBtn.setDisable(!valid);
    }
    
    @Override
    public void onChangeAnyFormValue() {
        //TODO
        this.isValidForm();
    }
}
