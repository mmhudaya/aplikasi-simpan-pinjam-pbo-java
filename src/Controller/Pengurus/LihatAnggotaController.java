/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Pengurus;

import Controller.MainController;
import Core.Controller.CorePage;
import Main.Main;
import Model.Anggota;
import ViewTable.AnggotaTable;
import ViewTable.TransaksiTable;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Muttabi Hudaya
 */
public class LihatAnggotaController extends CorePage implements Initializable {
    
    @FXML
    private TableView<AnggotaTable> anggotaTable;
    
    @FXML
    private TableColumn<AnggotaTable, String> nik, nama, ttl, jenisKelamin, golonganDarah, 
            pekerjaan,email,noTelp,tglAktif;
    
    public void setPageHeader() {
        mc.setPageHeader("Daftar Anggota", "Tabel Seluruh Anggota Koperasi");
    }

    MainController mc;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    



    @Override
    public void onShown() {
        this.mc = (MainController) super.getParentController();
        this.setPageHeader();
        this.createTable();
    }

    
    @Override
    public void onChangeAnyFormValue() {
        //TODO
    }
    
    private void createTable(){
        nik.setCellValueFactory(new PropertyValueFactory<>("nik"));
        nama.setCellValueFactory(new PropertyValueFactory<>("nama"));
        ttl.setCellValueFactory(new PropertyValueFactory<>("ttl"));
        jenisKelamin.setCellValueFactory(new PropertyValueFactory<>("jenisKelamin"));
        golonganDarah.setCellValueFactory(new PropertyValueFactory<>("golonganDarah"));
        pekerjaan.setCellValueFactory(new PropertyValueFactory<>("pekerjaan"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        noTelp.setCellValueFactory(new PropertyValueFactory<>("noTelp"));
        tglAktif.setCellValueFactory(new PropertyValueFactory<>("tglAktif"));
        
        LinkedHashMap<String, Object> whereValues = new LinkedHashMap<>();
        whereValues.put("is_aktif", true);
        whereValues.put("is_stop", false);
        whereValues.put("is_pengurus", false);
        
        ObservableList<AnggotaTable> rows = FXCollections.observableArrayList();
        Anggota test = new Anggota();
        List<Object[]> listAnggota = test.getAllObjectByFilter(whereValues);
        for(Object[] objAnggota: listAnggota){
            Anggota anggota = new Anggota();
            anggota.map(objAnggota);
            rows.add(new AnggotaTable(anggota));
        }
        
        anggotaTable.setItems(rows);
    }
        
}
