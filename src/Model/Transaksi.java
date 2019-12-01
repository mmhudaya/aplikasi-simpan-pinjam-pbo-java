/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Core.Database.CoreDatabaseTable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Muttabi Hudaya
 */
public abstract class Transaksi<T> extends CoreDatabaseTable<Transaksi> implements Comparable<Transaksi> {
    private Date tglTransaksi;
    private Double jumlahUang;
    private Pengurus pengurus;

    public Transaksi(String tableName, String[] columnNames) {
        super(tableName, columnNames);
    }
    
    public Date getTglTransaksi() {
        return tglTransaksi;
    }

    public void setTglTransaksi(Date tglTransaksi) {
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
