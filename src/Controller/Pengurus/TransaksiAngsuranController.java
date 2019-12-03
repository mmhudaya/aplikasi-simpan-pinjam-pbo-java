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
import Model.Angsuran;
import Model.Pinjaman;
import Utils.CoreStringUtils;
import Utils.FormUtils;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.ResourceBundle;
import javafx.animation.PauseTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Muttabi Hudaya
 */
public class TransaksiAngsuranController extends CorePage implements Initializable {
    private PauseTransition pause;
    private boolean isFilled = false;
    private boolean isLunas = false;
    
    MainController mc;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
    public void setPageHeader() {
        mc.setPageHeader("Transaksi Angsuran", "Form Transaksi Angsuran");
    }
    
    @FXML
    private JFXButton submitBtn, cancelBtn;
    @FXML
    private Label responseLabel, idPeminjamanCheckLabel, angsuranKeLabel;
    @FXML
    private JFXTextField tfIdPeminjaman, tfNikAnggota, tfNamaAnggota, tfJumlahPeminjaman, tfTenor, tfTipeBunga, tfJumlahPembayaran;
    
    @FXML
    private void onBtnCancelClick(Event ev){
        this.resetForm();
        this.tfIdPeminjaman.setDisable(false);
        this.isFilled = false;
    }
    
    @FXML
    private void onMouseClickSubmitBtn(Event ev){
        Angsuran angsuran = new Angsuran();
        angsuran.setPinjamId(this.tfIdPeminjaman.getText());
        angsuran.setNikAnggota(this.tfNikAnggota.getText());
        angsuran.setJumlahUang(Double.parseDouble(this.tfJumlahPembayaran.getText().replaceAll(",", "")));
        angsuran.setJumlahBunga(angsuran.getBunga(Constant.TIPE_BUNGA.get(this.tfTipeBunga.getText()), this.tfIdPeminjaman.getText()));
        
        if(Main.pengurus.transaksi(angsuran)){
            if(isLunas){
                Pinjaman pinjaman = new Pinjaman();
                HashMap<String, Object> values = new HashMap<>();
                values.put("is_lunas", true);
                
                HashMap<String, Object> whereValues = new HashMap<>();
                whereValues.put("id", this.tfIdPeminjaman.getText());
                
                pinjaman.update(values, whereValues);
            }
            FormUtils.showLabelMessage(this.responseLabel, "Transaksi sukses dilakukan", false);
            this.resetForm();
        }else{
            FormUtils.showLabelMessage(this.responseLabel, "Terjadi kesalahan", true);
        }
    }
    
    
    
    private void mapPinjamanToForm(Pinjaman pinjaman){
        this.tfIdPeminjaman.setText(pinjaman.getId());
        this.tfNikAnggota.setText(pinjaman.getNikAnggota());
        this.tfJumlahPeminjaman.setText(CoreStringUtils.getAmountFormat(Double.valueOf(pinjaman.getJumlahUang())));
        this.tfTenor.setText(pinjaman.getTenor() +" Bulan");
        this.tfTipeBunga.setText(this.getKeyFromValue(pinjaman.getIdTipeBunga()));
        
        Anggota anggota = new Anggota();
        HashMap<String, Object> whereValues = new HashMap<>();
        whereValues.put("nik", pinjaman.getNikAnggota());
        anggota.map(anggota.getOneByFilter(whereValues));
        System.out.println(anggota.getNama());
        this.tfNamaAnggota.setText(anggota.getNama()); //TODO
        
        Angsuran angsuran = new Angsuran();
        if(angsuran.getAngsuranKe(pinjaman.getId()) >= pinjaman.getTenor()){
            this.isLunas = true;
        }
        this.tfJumlahPembayaran.setText(CoreStringUtils.getAmountFormat(Double.valueOf(angsuran.getBayar(pinjaman.getId()))));
        this.angsuranKeLabel.setText("Angsuran Ke-"+angsuran.getAngsuranKe(pinjaman.getId()));
    }
    
    private String getKeyFromValue(int value){
        System.out.println(value);
        for (Entry<String, Integer> entry : Constant.TIPE_BUNGA.entrySet()) {
            System.out.println(entry.getValue());
            if (entry.getValue().equals(value)) {
                return entry.getKey();
            }
        }
        
        return "";
    }

    @Override
    public void onShown() {
        this.mc = (MainController) super.getParentController();
        this.setPageHeader();
        this.resetForm();
        this.setTextFieldListener();
        this.idPeminjamanCheckLabel.setVisible(false);
    }
    
    private void resetForm(){
        this.isLunas = false;
        this.angsuranKeLabel.setText("");
        this.cancelBtn.setVisible(false);
        
        this.tfIdPeminjaman.setDisable(false);
        this.tfIdPeminjaman.setText("");
        this.tfNikAnggota.setText("");
        this.tfJumlahPeminjaman.setText("");
        this.tfNamaAnggota.setText("");
        this.tfTenor.setText("");
        this.tfTipeBunga.setText("");
        this.tfJumlahPembayaran.setText("");
        
        FormUtils.textFieldPristine(tfIdPeminjaman);
        FormUtils.textFieldPristine(tfNikAnggota);
        FormUtils.textFieldPristine(tfJumlahPeminjaman);
        FormUtils.textFieldPristine(tfNamaAnggota);
        FormUtils.textFieldPristine(tfTenor);
        FormUtils.textFieldPristine(tfTipeBunga);
        FormUtils.textFieldPristine(tfJumlahPembayaran);
        
        this.idPeminjamanCheckLabel.setVisible(false);
    }
    
    private void setTextFieldListener(){
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
            };
        });
    }
    
    private void tryGetPinjaman(String idPinjaman){
        Pinjaman pinjaman = new Pinjaman();
        LinkedHashMap<String, Object> whereValues = new LinkedHashMap<>();
        whereValues.put("id", idPinjaman);
        whereValues.put("is_diterima", true);
        whereValues.put("is_lunas", false);
        Object[] res = pinjaman.getOneByFilter(whereValues);
        System.out.println(Arrays.toString(res));
        if(res != null && res.length > 0){
            this.resetForm();
            this.isFilled = true;
            this.responseLabel.setText("");
            this.tfIdPeminjaman.setDisable(true);
            this.submitBtn.setDisable(false);
            this.cancelBtn.setVisible(true);
            this.cancelBtn.setDisable(false);
            this.mapPinjamanToForm(pinjaman.map(res));
            this.setCheckLabel(idPeminjamanCheckLabel, false);
        }else{
            this.submitBtn.setDisable(true);
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
    
    @Override
    public void onChangeAnyFormValue() {
        
    }
}
