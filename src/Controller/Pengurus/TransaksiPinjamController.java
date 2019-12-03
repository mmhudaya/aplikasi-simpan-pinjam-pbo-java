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
import Model.Anggota;
import Model.Pinjaman;
import Model.Simpanan;
import Utils.FormUtils;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.ResourceBundle;
import javafx.animation.PauseTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Muttabi Hudaya
 */
public class TransaksiPinjamController extends CorePage implements Initializable {

    private boolean isManualInputMode = false;
    private boolean isAutoFilledMode = true;
    private boolean isFilled = false;
    private boolean isEditMode = false;
    private PauseTransition pause;
    
    MainController mc;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
    public void setPageHeader() {
        mc.setPageHeader("Transaksi Pinjaman", "Form Transaksi Pinjaman");
    }
    
    @FXML
    private JFXButton submitBtn, btnIsiForm, btnEdit;
    @FXML
    private Label responseLabel, nikCheckLabel, idPeminjamanCheckLabel;
    @FXML
    private JFXTextField tfIdPeminjaman, tfNikAnggota, tfJumlahPeminjaman, tfSukuBunga;
    @FXML
    private JFXComboBox cbTipeBunga, cbTenor;
    
    @FXML
    private void onMouseClickSubmitBtn(Event ev){
        Pinjaman pinjaman = new Pinjaman();
        pinjaman.setIdTipeBunga(Constant.TIPE_BUNGA.get(this.cbTipeBunga.getValue()));
        pinjaman.setTenor(Constant.TENOR.get(this.cbTenor.getValue()));
        pinjaman.setSukuBunga(Double.valueOf(this.tfSukuBunga.getText()));
        pinjaman.setJumlahUang(Double.valueOf(this.tfJumlahPeminjaman.getText()));
        pinjaman.setNikAnggota(this.tfNikAnggota.getText());
        pinjaman.setPengurus(Main.pengurus);
        pinjaman.setTglTransaksi(new Timestamp(new Date().getTime()));
        pinjaman.setIsLunas(false);
        pinjaman.setIsDiterima(true);
        
        if(this.isFilled || this.isEditMode){
            pinjaman.setId(this.tfIdPeminjaman.getText());
            LinkedHashMap<String, Object> whereValues = new LinkedHashMap<String, Object>();
            whereValues.put("id", pinjaman.getId());
            if(pinjaman.update(whereValues)){
                FormUtils.showLabelMessage(this.responseLabel, "Transaksi sukses dilakukan", false);
                this.resetForm();
            }else{
                FormUtils.showLabelMessage(this.responseLabel, "Terjadi kesalahan", true);
            }
            return;
        }
        
        if(Main.pengurus.transaksi(pinjaman)){
            FormUtils.showLabelMessage(this.responseLabel, "Transaksi sukses dilakukan", false);
            this.resetForm();
        }else{
            FormUtils.showLabelMessage(this.responseLabel, "Terjadi kesalahan", true);
        }
    }
    
    
    private void tryGetPinjaman(String idPinjaman){
        Pinjaman pinjaman = new Pinjaman();
        LinkedHashMap<String, Object> whereValues = new LinkedHashMap<>();
        whereValues.put("id", idPinjaman);
        whereValues.put("is_diterima", false);
        Object[] res = pinjaman.getOneByFilter(whereValues);
        System.out.println(Arrays.toString(res));
        if(res != null && res.length > 0){
            this.isFilled = true;
            this.mapPinjamanToForm(pinjaman.map(res));
            this.setCheckLabel(idPeminjamanCheckLabel, false);
        }else{
            this.setCheckLabel(idPeminjamanCheckLabel, true);
        }
    }
    
    private void setCheckLabel(Label target, boolean isError){
        if(isError){
            target.setText("X");
            target.setStyle("-fx-text-fill: red");
            target.setVisible(true);
        }else{
            target.setText("✓");
            target.setStyle("-fx-text-fill: #10a13c");
            target.setVisible(true);
        }
    }
    
    private void mapPinjamanToForm(Pinjaman pinjaman){
        this.btnIsiForm.setVisible(false);
        this.tfIdPeminjaman.setText(pinjaman.getId());
        this.tfIdPeminjaman.setDisable(true);
        this.tfNikAnggota.setText(pinjaman.getNikAnggota());
        this.tfJumlahPeminjaman.setText(pinjaman.getJumlahUang().toString());
        this.tfJumlahPeminjaman.setDisable(true);
        this.tfSukuBunga.setText(String.valueOf(pinjaman.getSukuBunga()));
        this.tfSukuBunga.setDisable(true);
        this.cbTenor.getSelectionModel().select(pinjaman.getTenor() + " Bulan");
        this.cbTenor.setDisable(true);
        this.cbTipeBunga.getSelectionModel().select(pinjaman.getIdTipeBunga() -1);
        this.cbTipeBunga.setDisable(true);
        this.btnEdit.setVisible(true);
        
    }
    
    private void tryCheckNik(String nikAnggota){
        Anggota anggota = new Anggota();
        if(anggota.isAktifDanTerdaftar(nikAnggota)){
            this.setCheckLabel(nikCheckLabel, false);
            this.tfNikAnggota.setAccessibleHelp("valid");
        }else{
            this.setCheckLabel(nikCheckLabel, true);
            this.tfNikAnggota.setAccessibleHelp("error");
        }
        
    }
    
    @FXML
    private void onClickIsiFormBtn(Event ev){
        this.isEditMode = true;
        if(!this.isManualInputMode){;
            this.setInputManualForm();
        }else{
            this.setAutoFilledForm();
        }
        this.isManualInputMode = !this.isManualInputMode;
    }
    
    @FXML
    private void onClickUbahBtn(Event ev){
        this.isEditMode = true;
        this.setEditFilledForm();
    }
    

    @Override
    public void onShown() {
        this.mc = (MainController) super.getParentController();
        this.setPageHeader();
        this.setFormValidation();
        this.initComboBox();
        this.setAutoFilledForm();
        this.idPeminjamanCheckLabel.setVisible(false);
        this.nikCheckLabel.setVisible(false);
        this.setListenerTextField();
    }
    
    private void setListenerTextField(){
        this.tfNikAnggota.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if(pause != null) pause.stop();
                pause = new PauseTransition(Duration.seconds(0.5));
                pause.setOnFinished(event ->{
                    tryCheckNik(newValue);
                    pause.stop();
                });
                pause.play();
            }
        });
        this.tfIdPeminjaman.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if(pause != null) pause.stop();
                pause = new PauseTransition(Duration.seconds(0.5));
                pause.setOnFinished(event ->{
                    tryGetPinjaman(newValue);
                    pause.stop();
                });
                pause.play();
            }
        });
    };
    
    private void resetForm(){
        this.isEditMode = false;
        this.isAutoFilledMode = true;
        this.isFilled = false;
        
        this.tfIdPeminjaman.setText("");
        this.tfNikAnggota.setText("");
        this.tfJumlahPeminjaman.setText("");
        this.tfSukuBunga.setText("");
        this.cbTenor.getSelectionModel().clearSelection();
        this.cbTipeBunga.getSelectionModel().clearSelection();
        
        FormUtils.textFieldPristine(tfIdPeminjaman);
        FormUtils.textFieldPristine(tfNikAnggota);
        FormUtils.textFieldPristine(tfJumlahPeminjaman);
        FormUtils.textFieldPristine(tfSukuBunga);
        FormUtils.comboBoxPristine(cbTenor);
        FormUtils.comboBoxPristine(cbTipeBunga);
        this.nikCheckLabel.setVisible(false);
        this.idPeminjamanCheckLabel.setVisible(false);
    }
    
    private void setInputManualForm(){
        this.resetForm();
        this.tfIdPeminjaman.setDisable(true);
        this.btnIsiForm.setText("Cancel");
        this.tfNikAnggota.setDisable(false);
        this.tfJumlahPeminjaman.setDisable(false);
        this.tfSukuBunga.setDisable(false);
        this.cbTenor.setDisable(false);
        this.cbTipeBunga.setDisable(false);
        this.btnEdit.setVisible(false);
    }
    
    private void setEditFilledForm(){
        this.tfIdPeminjaman.setDisable(true);
        this.btnIsiForm.setVisible(false);
        this.tfNikAnggota.setDisable(true);
        this.tfJumlahPeminjaman.setDisable(false);
        this.tfSukuBunga.setDisable(false);
        this.cbTenor.setDisable(false);
        this.cbTipeBunga.setDisable(false);
        this.btnEdit.setVisible(false);
    }
    
    private void setAutoFilledForm(){
        this.resetForm();
        this.tfIdPeminjaman.setDisable(false);
        this.btnIsiForm.setText("ISI FORM");
        this.tfNikAnggota.setDisable(true);
        this.tfJumlahPeminjaman.setDisable(true);
        this.tfSukuBunga.setDisable(true);
        this.cbTenor.setDisable(true);
        this.cbTipeBunga.setDisable(true);
        this.btnEdit.setVisible(false);
    }
    
    
    private void initComboBox(){
        String[] listTipeBunga = {"Menurun", "Menurun Efektif"};
        FormUtils.initComboBox(this.cbTipeBunga, listTipeBunga );
        
        String[] listTenor = {"6 Bulan", "18 Bulan", "24 Bulan", "36 Bulan", "48 Bulan"};
        FormUtils.initComboBox(this.cbTenor, listTenor );
    }
    
    private void setFormValidation(){
        FormUtils.addLengthHandler(this, this.tfIdPeminjaman, 5, 50);
        FormUtils.addNumberHandler(this, this.tfNikAnggota, 16, 16);
        FormUtils.addDecimalMinMaxHandler(this, tfJumlahPeminjaman, Constant.MIN_PEMINJAMAN, Constant.MAX_PEMINJAMAN);
        FormUtils.addDecimalMinMaxHandler(this, tfSukuBunga, 0.1, 10);
        FormUtils.addComboBoxNotNullHandler(this, this.cbTenor);
        FormUtils.addComboBoxNotNullHandler(this, this.cbTipeBunga);
    }
    
    private void isValidForm(){
        boolean valid = true;
        //Text Field
        if(!this.isManualInputMode){
            valid = valid && FormUtils.formTextFieldIsValid(tfIdPeminjaman);
        }
        valid = valid && FormUtils.formTextFieldIsValid(tfNikAnggota);
        valid = valid && FormUtils.formTextFieldIsValid(tfJumlahPeminjaman);
        valid = valid && FormUtils.formComboBoxIsValid(cbTenor);
        valid = valid && FormUtils.formComboBoxIsValid(cbTipeBunga);
        
        this.submitBtn.setDisable(!valid);
    }
    
    @Override
    public void onChangeAnyFormValue() {
        this.isValidForm();
    }
}
