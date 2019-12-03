/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ViewTable;

import Model.Transaksi;
import Utils.CoreStringUtils;
import java.time.LocalDate;
import java.time.LocalDateTime;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
/**
 *
 * @author Muttabi Hudaya
 */
public class TransaksiTable {
    private SimpleStringProperty idTransaksi;
    private SimpleStringProperty jumlahTransaksi;
    private LocalDateTime tanggalTransaksi;
    private SimpleStringProperty pengurus;
    private SimpleStringProperty tipeTransaksi;

    public TransaksiTable(Transaksi t) {
        this.idTransaksi = new SimpleStringProperty(t.getId());
        this.jumlahTransaksi = new SimpleStringProperty("Rp. " + CoreStringUtils.getAmountFormat(t.getJumlahUang()));
        this.tanggalTransaksi = t.getTglTransaksi().toLocalDateTime();
        this.pengurus = new SimpleStringProperty(t.getPengurus().getNik());
        this.tipeTransaksi = new SimpleStringProperty(t.getTipeTransaksi());
    }

    public String getIdTransaksi() {
        return idTransaksi.get();
    }

    public String getJumlahTransaksi() {
        return jumlahTransaksi.get();
    }
    
    public LocalDateTime getTanggalTransaksi() {
        return tanggalTransaksi;
    }

    public String getPengurus() {
        return pengurus.get();
    }

    public String getTipeTransaksi() {
        return tipeTransaksi.get();
    }
}
