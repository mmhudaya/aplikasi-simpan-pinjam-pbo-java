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
import Utils.FormUtils;
import ViewTable.AnggotaTable;
import ViewTable.TransaksiTable;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

/**
 * FXML Controller class
 *
 * @author Muttabi Hudaya
 */
public class LihatPendaftarController extends CorePage implements Initializable {
    @FXML
    private HBox selectedPendaftarPane;
    
    @FXML
    private Label nikLabel, namaLabel, ttlLabel, responseLabel;
    
    @FXML
    private CheckBox checkSlipGaji, checkKtp, checkKartuKeluarga, checkTagihanListrik;
    
    @FXML
    private TableView<AnggotaTable> anggotaTable;
    
    @FXML
    private JFXButton btnAktifasi, btnUpdate;
    
    @FXML
    private TableColumn<AnggotaTable, String> nik, nama, ttl, jenisKelamin, golonganDarah, 
            pekerjaan,email,noTelp,tglAktif;
    
    private List<Integer> listBerkasTercatat = new ArrayList<>();
    
    public void setPageHeader() {
        mc.setPageHeader("Daftar Pendaftar", "Tabel Seluruh Pendaftar Koperasi");
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
        this.resetSelection();
        this.createTable();
        anggotaTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                onSelectionRow();
            }else{
                resetSelection();
            }
        });
    }
    
    private void onSelectionRow(){
        this.resetSelection();
        this.selectedPendaftarPane.setDisable(false);
        
        AnggotaTable selected = anggotaTable.getSelectionModel().getSelectedItem();
        this.nikLabel.setText("NIK: "+selected.getNik());
        this.namaLabel.setText("Nama: "+selected.getNama());
        this.ttlLabel.setText("Tempat, dan Tanggal Lahir: "+ selected.getTtl());
        
        //TODO Kelengkapan berkas)
        Anggota anggota = new Anggota();
        anggota.setNik(selected.getNik());
        List<Integer> kelengkapanBerkas = anggota.getKelengkapanBerkas();
        this.setCheckBoxKelengkapan(kelengkapanBerkas);
        
        this.setBtnAktifasi();
        this.setBtnUpdate();
    }
    
    private void setCheckBoxKelengkapan(List<Integer> kelengkapanBerkas){
        for(Integer berkasId: kelengkapanBerkas){
            this.listBerkasTercatat.add(berkasId);
            switch(berkasId){
                case 1:  this.checkKtp.setSelected(true); ;break;
                case 2: this.checkKartuKeluarga.setSelected(true); break;
                case 3: this.checkSlipGaji.setSelected(true); break;
                case 4: this.checkTagihanListrik.setSelected(true); break;
            }
        }
    }
    
    private void resetSelection(){
        this.listBerkasTercatat.clear();
        this.selectedPendaftarPane.setDisable(true);
        this.nikLabel.setText("");
        this.namaLabel.setText("");
        this.ttlLabel.setText("");
        
        this.checkKartuKeluarga.setSelected(false);
        this.checkKtp.setSelected(false);
        this.checkSlipGaji.setSelected(false);
        this.checkTagihanListrik.setSelected(false);
    }
    
    private List<Integer> getKelengkapanBerkas(){
        List<Integer> kelengkapanBerkas = new ArrayList<>();
        if(this.checkKtp.isSelected() && !this.listBerkasTercatat.contains(1)) kelengkapanBerkas.add(1);
        if(this.checkKartuKeluarga.isSelected() && !this.listBerkasTercatat.contains(2)) kelengkapanBerkas.add(2);
        if(this.checkSlipGaji.isSelected() && !this.listBerkasTercatat.contains(3)) kelengkapanBerkas.add(3);
        if(this.checkTagihanListrik.isSelected() && !this.listBerkasTercatat.contains(4)) kelengkapanBerkas.add(4);
        
        return kelengkapanBerkas;
    }
    
    @FXML
    private void onClickedCheckbox(Event ev){
        this.setBtnAktifasi();
    }
    
    private void setBtnAktifasi(){
        this.btnAktifasi.setDisable(!(this.checkKtp.isSelected() && this.checkKartuKeluarga.isSelected() && this.checkSlipGaji. isSelected() && this.checkTagihanListrik.isSelected()));
    }
    
    private void setBtnUpdate(){
        this.btnUpdate.setDisable(this.listBerkasTercatat.size() == 4);
    }
    
    
    @FXML
    private void onClickBtnUpdate(Event ev){
        AnggotaTable selectedItem = anggotaTable.getSelectionModel().getSelectedItem();
        
        if(Main.pengurus.updateKelengkapanBerkas(selectedItem.getNik(), this.getKelengkapanBerkas())){
            FormUtils.showLabelMessage(this.responseLabel, "Berkas angotta sukses ditambah", false);
            this.createTable();
            this.resetSelection();
        }else{
            FormUtils.showLabelMessage(this.responseLabel, "Terjadi kesalahan", false);
        }
        
    }
    
    @FXML
    private void onClickBtnAktifasi(Event ev){
        AnggotaTable selectedItem = anggotaTable.getSelectionModel().getSelectedItem();
        
        if(Main.pengurus.aktifasiPendaftar(selectedItem.getNik())){
            FormUtils.showLabelMessage(this.responseLabel, "Anggota sukses di aktifasi", false);
            this.createTable();
            this.resetSelection();
        }else{
            FormUtils.showLabelMessage(this.responseLabel, "Terjadi kesalahan", false);
        }
    }

    
    @Override
    public void onChangeAnyFormValue() {
        //TODO
    }
    
    private void createTable(){
        anggotaTable.setId(null);
        
        nik.setCellValueFactory(new PropertyValueFactory<>("nik"));
        nama.setCellValueFactory(new PropertyValueFactory<>("nama"));
        ttl.setCellValueFactory(new PropertyValueFactory<>("ttl"));
        jenisKelamin.setCellValueFactory(new PropertyValueFactory<>("jenisKelamin"));
        golonganDarah.setCellValueFactory(new PropertyValueFactory<>("golonganDarah"));
        pekerjaan.setCellValueFactory(new PropertyValueFactory<>("pekerjaan"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        noTelp.setCellValueFactory(new PropertyValueFactory<>("noTelp"));
        
        LinkedHashMap<String, Object> whereValues = new LinkedHashMap<>();
        whereValues.put("is_aktif", false);
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
