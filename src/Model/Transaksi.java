/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Core.Database.CoreDatabaseTable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Muttabi Hudaya
 */
public abstract class Transaksi<T> extends CoreDatabaseTable<Transaksi> implements Comparable<Transaksi> {
    private Timestamp tglTransaksi;
    private Double jumlahUang;
    private Pengurus pengurus;
    
    //Distinct
    private String tipeTransaksi;
    private String id;
    private String nikAnggota;

    public Transaksi(String tableName, String[] columnNames) {
        super(tableName, columnNames);
    }
    
    public String getNikAnggota() {
        return nikAnggota;
    }

    public void setNikAnggota(String nikAnggota) {
        this.nikAnggota = nikAnggota;
    }
    
    
    public Timestamp getTglTransaksi() {
        return tglTransaksi;
    }

    public void setTglTransaksi(Timestamp tglTransaksi) {
        this.tglTransaksi = tglTransaksi;
    }

    public Double getJumlahUang() {
        return jumlahUang;
    }

    public void setJumlahUang(Double jumlahUang) {
        this.jumlahUang = jumlahUang;
    }

    public Pengurus getPengurus() {
        return pengurus;
    }

    public void setPengurus(Pengurus pengurus) {
        this.pengurus = pengurus;
    }

    public String getTipeTransaksi() {
        return tipeTransaksi;
    }

    public void setTipeTransaksi(String tipeTransaksi) {
        this.tipeTransaksi = tipeTransaksi;
    }
    
    public Double getTotalTransaksi(){
        Object[] res = super.customReadOneQuery("SELECT SUM(`jumlah_uang`) FROM `"+this.tableName+"`", 1);
        if(res != null && res[0] != null){
            return (Double) res[0];
        }
        return 0.0;
    }
    
    public Double getTotalTransaksiPribadi(String nik){
        Object[] res = super.customReadOneQuery("SELECT SUM(`jumlah_uang`) FROM `"+this.tableName+"` WHERE anggota_nik='"+nik+"'", 1);
        if(res != null && res[0] != null){
            return (Double) res[0];
        }
        return 0.0;
    }
    
    public String getId(){
        return this.id;
    }
    
    public void setId(String id){
        this.id = id;
    }
    
    protected String getId(String namaAnggota, String tipeTransaksi){
        namaAnggota = namaAnggota.replaceAll("\\s","");
        String code1 = namaAnggota.substring(0, Math.min(namaAnggota.length(), 3)).toUpperCase();
        String code2 = tipeTransaksi.substring(0, Math.min(namaAnggota.length(), 1)).toUpperCase();
        String code3 = String.valueOf(new Date().getTime());
        
        return code1 + code2 + code3;
    }

    @Override
    public int compareTo(Transaksi o) {
        if (getTglTransaksi() == null || o.getTglTransaksi() == null)
          return 0;
        
        return getTglTransaksi().compareTo(o.getTglTransaksi());
        
    }

    @Override
    public abstract Transaksi map(Object[] values);

    @Override
    protected abstract HashMap<String, Object> getValuesFromModelAttribute();
    
    protected abstract List<T> convertToThis(List<Transaksi> listTransaksi);
}
