/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Anggota;

import Controller.MainController;
import Core.Controller.CorePage;
import Main.Main;
import Model.Angsuran;
import Model.Penarikan;
import Model.Pinjaman;
import Model.Simpanan;
import Model.Transaksi;
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
import javafx.scene.control.cell.TextFieldTableCell;

/**
 * FXML Controller class
 *
 * @author Muttabi Hudaya
 */
public class LihatDaftarTransaksiController extends CorePage implements Initializable {
    
    @FXML
    private TableView<TransaksiTable> transaksiTable;
    
    @FXML
    private TableColumn<TransaksiTable, String> idTransaksi, jumlahTransaksi, tanggalTransaksi, pengurus, tipeTransaksi;
    
    public void setPageHeader() {
        mc.setPageHeader("Transaksi", "Transaksi Simpan Pinjam Anggota");
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
        idTransaksi.setCellValueFactory(new PropertyValueFactory<>("idTransaksi"));
        jumlahTransaksi.setCellValueFactory(new PropertyValueFactory<>("jumlahTransaksi"));
        tanggalTransaksi.setCellValueFactory(new PropertyValueFactory<>("tanggalTransaksi"));
        pengurus.setCellValueFactory(new PropertyValueFactory<>("pengurus"));
        tipeTransaksi.setCellValueFactory(new PropertyValueFactory<>("tipeTransaksi"));
        LinkedHashMap<String, Object> whereValues = new LinkedHashMap<>();
        String nik;
        
        if(Main.anggota != null){
            nik = Main.anggota.getNik();
        }else{
            nik = Main.pengurus.getNik();
        }
        
        ObservableList<TransaksiTable> rows = FXCollections.observableArrayList();
        whereValues = new LinkedHashMap<>();
        whereValues.put("anggota_nik", nik);
        
        
        Pinjaman pinjaman = new Pinjaman();
        Angsuran angsuran = new Angsuran();
        List<Object[]> listObjectPinjaman = pinjaman.getAllObjectByFilter(whereValues);
        for (Object[] objectPinjaman: listObjectPinjaman) {
            Pinjaman _pinjaman = new Pinjaman();
            _pinjaman.map(objectPinjaman);
            rows.add(new TransaksiTable(_pinjaman));
            
            whereValues = new LinkedHashMap<>();
            whereValues.put("pinjam_id", _pinjaman.getId());
            for (Object[] objectAngsuran: angsuran.getAllObjectByFilter(whereValues)) {
                Angsuran _angusran = new Angsuran();
                _angusran.map(objectAngsuran);
                rows.add(new TransaksiTable(_angusran));
            }
        }
        
        Simpanan simpanan = new Simpanan();
        whereValues = new LinkedHashMap<>();
        whereValues.put("anggota_nik", nik);
        for (Object[] objectSimpanan: simpanan.getAllObjectByFilter(whereValues)) {
            Simpanan _simpanan = new Simpanan();
            _simpanan.map(objectSimpanan);
            rows.add(new TransaksiTable(_simpanan));
        }
        
        
        Penarikan penarikan = new Penarikan();
        whereValues = new LinkedHashMap<>();
        whereValues.put("anggota_nik", nik);
        for (Object[] objectPenarikan: penarikan.getAllObjectByFilter(whereValues)) {
            Penarikan _penarikan = new Penarikan();
            _penarikan.map(objectPenarikan);
            rows.add(new TransaksiTable(_penarikan));
        }
        
        
        transaksiTable.setItems(rows);
        transaksiTable.getSelectionModel().setCellSelectionEnabled(true);
    }
        
}
