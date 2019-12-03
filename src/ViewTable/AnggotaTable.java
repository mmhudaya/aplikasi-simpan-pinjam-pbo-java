/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ViewTable;

import Model.Anggota;
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
public class AnggotaTable {
    private SimpleStringProperty nik;
    private SimpleStringProperty nama;
    private SimpleStringProperty ttl;
    private SimpleStringProperty jenisKelamin;
    private SimpleStringProperty golonganDarah;
    private SimpleStringProperty alamat;
    private SimpleStringProperty pekerjaan;
    private SimpleStringProperty email;
    private SimpleStringProperty noTelp;
    private LocalDateTime tglAktif;

    public AnggotaTable(Anggota anggota) {
        this.nik = new SimpleStringProperty(anggota.getNik());
        this.nama = new SimpleStringProperty(anggota.getNama());
        this.ttl = new SimpleStringProperty(anggota.getTtl());
        this.jenisKelamin = new SimpleStringProperty(anggota.getJenisKelamin());
        this.golonganDarah = new SimpleStringProperty(anggota.getGolonganDarah());
        this.alamat = new SimpleStringProperty(anggota.getAlamat());
        this.pekerjaan = new SimpleStringProperty(anggota.getPekerjaan());
        this.email = new SimpleStringProperty(anggota.getEmail());
        this.noTelp = new SimpleStringProperty(anggota.getNoTelp());
        this.tglAktif = anggota.getTglAktif() != null ? anggota.getTglAktif().toLocalDateTime() : null;
    }


    public LocalDateTime getTglAktif() {
        return tglAktif;
    }
    
    public String getNik() {
        return nik.get();
    }

    public String getNama() {
        return nama.get();
    }

    public String getTtl() {
        return ttl.get();
    }

    public String getJenisKelamin() {
        return jenisKelamin.get();
    }

    public String getGolonganDarah() {
        return golonganDarah.get();
    }

    public String getAlamat() {
        return alamat.get();
    }

    public String getPekerjaan() {
        return pekerjaan.get();
    }

    public String getEmail() {
        return email.get();
    }

    public String getNoTelp() {
        return noTelp.get();
    }
}
