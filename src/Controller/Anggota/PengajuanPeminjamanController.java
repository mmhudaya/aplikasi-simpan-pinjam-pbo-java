/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Anggota;

import Constant.Constant;
import Controller.MainController;
import Core.Controller.CorePage;
import Main.Main;
import Model.Pinjaman;
import Utils.FormUtils;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
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
public class PengajuanPeminjamanController extends CorePage implements Initializable {

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
    private JFXTextField tfJumlahPeminjaman, tfSukuBunga;
    @FXML
    private JFXComboBox cbTipeBunga, cbTenor;
    
    @FXML
    private void onMouseClickSubmitBtn(Event ev){
        Pinjaman peminjaman = new Pinjaman();
        peminjaman.setIdTipeBunga(Constant.TIPE_BUNGA.get(this.cbTipeBunga.getValue()));
        peminjaman.setTenor(Constant.TENOR.get(this.cbTenor.getValue()));
        peminjaman.setSukuBunga(Double.valueOf(this.tfSukuBunga.getText()));
        peminjaman.setJumlahUang(Double.valueOf(this.tfJumlahPeminjaman.getText()));
        peminjaman.setNikAnggota(Main.anggota.getNik());
        
        if(Main.anggota.pengajuanPeminjaman(peminjaman)){
            FormUtils.showLabelMessage(this.responseLabel, "Peminjaman sukses diajukan.", false);
            this.resetForm();
        }else{
            FormUtils.showLabelMessage(this.responseLabel, "Terjadi kesalahan", true);
        }
    }
    
    public void setPageHeader() {
        mc.setPageHeader("Pengajuan Pinjaman", "Form Pengajuan Pinjaman");
    }

    @Override
    public void onShown() {
        this.mc = (MainController) super.getParentController();
        this.setPageHeader();
        this.setFormValidation();
        this.initComboBox();
    }
    
    
    private void resetForm(){
        this.tfJumlahPeminjaman.setText("");
        this.tfSukuBunga.setText("");
        this.cbTipeBunga.getSelectionModel().clearSelection();
        this.cbTenor.getSelectionModel().clearSelection();
        FormUtils.textFieldPristine(this.tfJumlahPeminjaman);
        FormUtils.textFieldPristine(this.tfSukuBunga);
        FormUtils.comboBoxPristine(cbTipeBunga);
        FormUtils.comboBoxPristine(cbTenor);
    }
    
    private void initComboBox(){
        String[] listTipeBunga = {"Menurun", "Menurun Efektif"};
        FormUtils.initComboBox(this.cbTipeBunga, listTipeBunga );
        
        String[] listTenor = {"6 Bulan", "18 Bulan", "24 Bulan", "36 Bulan", "48 Bulan"};
        FormUtils.initComboBox(this.cbTenor, listTenor );
    }
    
    private void setFormValidation(){
        FormUtils.addDecimalMinMaxHandler(this, tfSukuBunga, 0.1, 10);
        FormUtils.addNumberMinMaxHandler(this, tfJumlahPeminjaman, Constant.MIN_PEMINJAMAN, Constant.MAX_PEMINJAMAN);
        FormUtils.addComboBoxNotNullHandler(this, this.cbTenor);
        FormUtils.addComboBoxNotNullHandler(this, this.cbTipeBunga);
    }
    
    private void isValidForm(){
        boolean valid = true;
        //Text Field
        valid = valid && FormUtils.formTextFieldIsValid(tfSukuBunga);
        valid = valid && FormUtils.formTextFieldIsValid(tfJumlahPeminjaman);
        
        //ComboBox
        valid = valid && FormUtils.formComboBoxIsValid(cbTenor);
        valid = valid && FormUtils.formComboBoxIsValid(cbTipeBunga);
        
        this.submitBtn.setDisable(!valid);
    }
    
    @Override
    public void onChangeAnyFormValue() {
        //TODO
        this.isValidForm();
    }
}
