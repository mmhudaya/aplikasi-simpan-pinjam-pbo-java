/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Constant.Constant;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

/**
 *
 * @author Muttabi Hudaya
 */
public class Pinjaman extends Transaksi<Pinjaman> {
    private double sukuBunga;
    private int idTipeBunga;
    private List<Angsuran> listAngsuran;
    private Boolean isDiterima;
    private Boolean isLunas;
    private int tenor;
    
    
    public Pinjaman() {
        super("pinjam", Constant.SIMPAN_PINJAM_TABLE.get("pinjam"));
    }
    
    public Pinjaman get(String idPinjaman){
        return this.map(super.get("id", idPinjaman));
    }
    
    public double getSukuBunga() {
        return sukuBunga;
    }

    public void setSukuBunga(double sukuBunga) {
        this.sukuBunga = sukuBunga;
    }

    public Boolean getIsDiterima() {
        return isDiterima;
    }

    public void setIsDiterima(Boolean isDiterima) {
        this.isDiterima = isDiterima;
    }

    public Boolean getIsLunas() {
        return isLunas;
    }

    public void setIsLunas(Boolean isLunas) {
        this.isLunas = isLunas;
    }

    public List<Angsuran> getListAngsuran() {
        return listAngsuran;
    }

    public void setListAngsuran(List<Angsuran> listAngsuran) {
        this.listAngsuran = listAngsuran;
    }

    public int getIdTipeBunga() {
        return idTipeBunga;
    }

    public void setIdTipeBunga(int idTipeBunga) {
        this.idTipeBunga = idTipeBunga;
    }

    public int getTenor() {
        return tenor;
    }

    public void setTenor(int tenor) {
        this.tenor = tenor;
    }
    
    public List<Transaksi> getAllTransaksi(){
        return super.getAll();
    }
    
    public boolean ajukan(){
        return super.save(this.getValuesFromModelAttribute());
    }
    
    public boolean update(LinkedHashMap<String, Object> whereValues){
        return super.update(this.getValuesFromModelAttribute(), whereValues);
    }
    
    @Override
    public Pinjaman map(Object[] values) {
        super.setId(values[0] != null ? (String) values[0] : null);
        super.setNikAnggota(values[1] != null ? (String) values[1] : null);
        this.setIdTipeBunga(values[2] != null ? (Integer) values[2] : null);
        this.setSukuBunga(values[3] != null ? (Double) values[3] : null);
        this.setTenor(values[4] != null ? (Integer) values[4] : null);
        this.setIsDiterima(values[5] != null ? (Integer) values[5] == 1 ? true : false : false);
        this.setIsLunas(values[6] != null ? (Integer) values[6] == 1 ? true : false : false);
        super.setTglTransaksi(values[7] != null ? (Timestamp) values[7] : null);
        super.setJumlahUang(values[8] != null ? (Double) values[8] : null);
        Pengurus pengurus = new Pengurus();
        pengurus.setNik(values[9] != null ? (String) values[9] : null);
        super.setPengurus(pengurus);
        super.setTipeTransaksi(this.getIsDiterima() ? "Pinjam" : "Pengajuan Pinjaman");
        
        return this;
    }
    

    public HashMap<String, Object> getValuesFromModelAttribute() {
        LinkedHashMap<String, Object> values = new LinkedHashMap<>();
        values.put("id", this.getId());
        values.put("anggota_nik", this.getNikAnggota());
        values.put("tipe_bunga_id", this.getIdTipeBunga());
        values.put("suku_bunga", this.getSukuBunga());
        values.put("tenor", this.getTenor());
        values.put("is_diterima", this.getIsDiterima());
        values.put("is_lunas", this.getIsLunas());
        values.put("tgl_transaksi", this.getTglTransaksi());
        values.put("jumlah_uang", this.getJumlahUang());
        values.put("pengurus_nik", this.getPengurus() != null ? this.getPengurus().getNik() : "0000000000000000");
        return values;
    }

    @Override
    protected List<Pinjaman> convertToThis(List<Transaksi> listTransaksi) {
        List<Pinjaman> listPinjaman = new ArrayList<>();
        
        for(Transaksi transaksi: listTransaksi){
            listPinjaman.add((Pinjaman) transaksi);
        }
        
        return listPinjaman;
    }
    
}
