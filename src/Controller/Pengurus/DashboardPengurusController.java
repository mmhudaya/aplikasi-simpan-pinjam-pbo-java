/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Pengurus;

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
public class DashboardPengurusController extends CorePage implements Initializable {
    MainController mc;
    @FXML
    private Label koperasiSimpananLabel, koperasiPinjamanLabel, koperasiPenarikanLabel, koperasiHutangLabel;
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
        System.out.println((Main.pengurus.getNik()));
        //Koperasi
        double pinjamanKoperasi = Main.pengurus.getTotalPinjamanKoperasi();
        double angsuranKoperasi = Main.pengurus.getTotalAngsuranKoperasi();
        System.out.println(angsuranKoperasi);
        this.koperasiSimpananLabel.setText("Rp. "+ CoreStringUtils.getAmountFormat(Main.pengurus.getTotalSimpananKoperasi()));
        this.koperasiPinjamanLabel.setText("Rp. "+ CoreStringUtils.getAmountFormat(pinjamanKoperasi));
        this.koperasiPenarikanLabel.setText("Rp. "+ CoreStringUtils.getAmountFormat(Main.pengurus.getTotalPenarikanKoperasi()));
        this.koperasiHutangLabel.setText("Rp. "+ CoreStringUtils.getAmountFormat(pinjamanKoperasi - angsuranKoperasi));
        
        //Pribadi
        double pinjamanPribadi = Main.pengurus.getTotalPinjaman();
        double angsuranPribadi = Main.pengurus.getTotalAngsuran();
        this.pribadiSimpananLabel.setText("Rp. "+ CoreStringUtils.getAmountFormat(Main.pengurus.getTotalSimpanan()));
        this.pribadiPinjamanLabel.setText("Rp. "+ CoreStringUtils.getAmountFormat(pinjamanPribadi));
        this.pribadiPenarikanLabel.setText("Rp. "+ CoreStringUtils.getAmountFormat(Main.pengurus.getTotalPenarikan()));
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
