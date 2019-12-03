/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Constant.Constant;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

/**
 *
 * @author Muttabi Hudaya
 */
public class Simpanan extends Transaksi<Simpanan> {
    private int idTipeSimpanan;
    
    public Simpanan() {
        super("simpan", Constant.SIMPAN_PINJAM_TABLE.get("simpan"));
    }
    
    public List<Simpanan> getSemuaSimpanan(){
        return this.convertToThis(super.getAll());
    }
    
    public List<Simpanan> getSemuaSimpanan(String nikAnggota){
        HashMap<String, Object> whereValues = new HashMap<>();
        whereValues.put("nik", nikAnggota);
        return this.convertToThis(super.getAllByFilter(whereValues));
    }
    
    public List<Simpanan> getSemuaSimpananAnggota(String nikAnggota, int idTipeSimpanan){
        HashMap<String, Object> whereValues = new HashMap<>();
        whereValues.put("nik", nikAnggota);
        whereValues.put("tipe_simpanan_id", idTipeSimpanan);
        return this.convertToThis(super.getAllByFilter(whereValues));
    }
    
    public Integer getIdTipeSimpanan() {
        return idTipeSimpanan;
    }

    public void setTipeSimpanan(int idTipeSimpanan) {
        this.idTipeSimpanan = idTipeSimpanan;
    }
    

    @Override
    public Simpanan map(Object[] values) {
        super.setId(values[0] != null ? (String) values[0] : null);
        super.setNikAnggota(values[1] != null ? (String) values[1] : null);
        this.setTipeSimpanan(idTipeSimpanan);
        super.setTglTransaksi(values[3] != null ? (Timestamp) values[3] : null);
        super.setJumlahUang(values[4] != null ? (Double) values[4] : null);
        Pengurus pengurus = new Pengurus();
        pengurus.setNik(values[5] != null ? (String) values[5] : null);
        super.setPengurus(pengurus);
        super.setTipeTransaksi("Simpan");
        
        return this;
    }

    @Override
    protected HashMap<String, Object> getValuesFromModelAttribute() {
        LinkedHashMap<String, Object> values = new LinkedHashMap<>();
        values.put("id", this.getId());
        values.put("anggota_nik", this.getNikAnggota());
        values.put("tipe_simpanan_id", this.getIdTipeSimpanan());
        values.put("tgl_transaksi", this.getTglTransaksi());
        values.put("jumlah_uang", this.getJumlahUang());
        values.put("pengurus_nik", this.getPengurus() != null ? this.getPengurus().getNik() : "0000000000000000");
        return values;
        
    }

    @Override
    protected List<Simpanan> convertToThis(List<Transaksi> listTransaksi) {
        List<Simpanan> listSimpanan = new ArrayList<>();
        for(Transaksi transaksi: listTransaksi){
            listSimpanan.add((Simpanan) transaksi);
        }
        
        return listSimpanan;
    }
    
    
}
