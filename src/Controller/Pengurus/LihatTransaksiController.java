/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Pengurus;

import Controller.MainController;
import Core.Controller.CorePage;
import Model.Angsuran;
import Model.Penarikan;
import Model.Pinjaman;
import Model.Simpanan;
import Model.Transaksi;
import ViewTable.TransaksiTable;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.KeyCode;

/**
 * FXML Controller class
 *
 * @author Muttabi Hudaya
 */
public class LihatTransaksiController extends CorePage implements Initializable {
    
    @FXML
    private TableView<TransaksiTable> transaksiTable;
    
    @FXML
    private TableColumn<TransaksiTable, String> idTransaksi, jumlahTransaksi, tanggalTransaksi, pengurus, tipeTransaksi;
    
    public void setPageHeader() {
        mc.setPageHeader("Transaksi", "Transaksi Simpan Pinjam Seluruh Anggota Koperasi");
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
        
        transaksiTable.setOnKeyReleased(evt -> {
            if (evt.isControlDown() && evt.getCode() == KeyCode.C) {
                List<TablePosition> selectedCells = transaksiTable.getSelectionModel().getSelectedCells();
                if (!selectedCells.isEmpty()) {
                    TablePosition selectedCell = selectedCells.get(0);
                    if (selectedCell.getTableColumn() == idTransaksi) {
                        String value = idTransaksi.getCellData(selectedCell.getRow());
                        Clipboard clipboard = Clipboard.getSystemClipboard();
                        ClipboardContent content = new ClipboardContent();
                        content.putString(value);
                        clipboard.setContent(content);
                    }
                }
            }
        });
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
        
        Pinjaman pinjaman = new Pinjaman();
        Simpanan simpanan = new Simpanan();
        Penarikan penarikan = new Penarikan();
        Angsuran angsuran = new Angsuran();
        
        ObservableList<TransaksiTable> rows = FXCollections.observableArrayList();
        
        for (Object[] objectTransaksi: pinjaman.getAllObject()) {
            Pinjaman _pinjaman = new Pinjaman();
            _pinjaman.map(objectTransaksi);
            rows.add(new TransaksiTable(_pinjaman));
        }
        for (Object[] objectTransaksi: simpanan.getAllObject()) {
            Simpanan _simpanan = new Simpanan();
            _simpanan.map(objectTransaksi);
            rows.add(new TransaksiTable(_simpanan));
        }
        for (Object[] objectTransaksi: penarikan.getAllObject()) {
            Penarikan _penarikan = new Penarikan();
            _penarikan.map(objectTransaksi);
            rows.add(new TransaksiTable(_penarikan));
        }
        for (Object[] objectTransaksi: angsuran.getAllObject()) {
            Angsuran _angsuran = new Angsuran();
            _angsuran.map(objectTransaksi);
            rows.add(new TransaksiTable(_angsuran));
        }
        
        transaksiTable.setItems(rows);
    }
        
}
