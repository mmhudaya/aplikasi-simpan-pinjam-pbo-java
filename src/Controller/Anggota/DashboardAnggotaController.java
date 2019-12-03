/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Anggota;

import Controller.MainController;
import Core.Controller.CorePage;
import Main.Main;
import Utils.CoreStringUtils;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author Muttabi Hudaya
 */
public class DashboardAnggotaController extends CorePage implements Initializable {
    MainController mc;
    
    @FXML
    private Label pribadiSimpananLabel, pribadiPinjamanLabel, pribadiPenarikanLabel, pribadiHutangLabel;

    public void setPageHeader() {
        mc.setPageHeader("Dashboard", "Dashboard Pengurus");
    }

    @Override
    public void onShown() {
        this.mc = (MainController) super.getParentController();
        this.setPageHeader();
        this.initDashboard();
    }

    private void initDashboard(){
        //Pribadi
        double pinjamanPribadi = Main.anggota.getTotalPinjaman();
        double angsuranPribadi = Main.anggota.getTotalAngsuran();
        this.pribadiSimpananLabel.setText("Rp. "+ CoreStringUtils.getAmountFormat(Main.anggota.getTotalSimpanan()));
        this.pribadiPinjamanLabel.setText("Rp. "+ CoreStringUtils.getAmountFormat(pinjamanPribadi));
        this.pribadiPenarikanLabel.setText("Rp. "+ CoreStringUtils.getAmountFormat(Main.anggota.getTotalPenarikan()));
        this.pribadiHutangLabel.setText("Rp. "+ CoreStringUtils.getAmountFormat(pinjamanPribadi - angsuranPribadi));
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    
    @Override
    public void onChangeAnyFormValue() {
        //TODO
    }
}
