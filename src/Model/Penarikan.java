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
public class Penarikan extends Transaksi<Penarikan> {

    public Penarikan() {
        super("ambil", Constant.SIMPAN_PINJAM_TABLE.get("ambil"));
    }
    
    public List<Penarikan> getSemuaPenarikan(){
        return this.convertToThis(super.getAll());
    }
    
    public List<Penarikan> getSemuaPenarikan(String nik){
        HashMap<String, Object> whereValues = new HashMap<>();
        whereValues.put("nik", nik);
        return this.convertToThis(super.getAllByFilter(whereValues));
    }
    
    @Override
    protected List<Penarikan> convertToThis(List<Transaksi> listTransaksi){
        List<Penarikan> listPenarikan = new ArrayList<>();
        for(Transaksi transaksi: listTransaksi){
            listPenarikan.add((Penarikan) transaksi);
        }
        
        return listPenarikan;
    }
    
    public double getJumlahMaksimalPenarikan(){
        Anggota anggota = new Anggota();
        anggota.setNik(this.getNikAnggota());
        return (anggota.getTotalSimpanan() - anggota.getTotalPinjaman() - anggota.getTotalPenarikan());
    }
    
    public boolean isDapatDilakukan(double jumlahPenarikan){
        return jumlahPenarikan < (this.getJumlahMaksimalPenarikan());
    }

    @Override
    public Penarikan map(Object[] values) {
        super.setId(values[0] != null ? (String) values[0] : null);
        super.setNikAnggota(values[1] != null ? (String) values[1] : null);
        super.setTglTransaksi(values[2] != null ? (Timestamp) values[2] : null);
        super.setJumlahUang(values[3] != null ? (Double) values[3] : null);
        Pengurus pengurus = new Pengurus();
        pengurus.setNik(values[4] != null ? (String) values[4] : null);
        super.setPengurus(pengurus);
        super.setTipeTransaksi("Tarik");
        
        return this;
    }

    @Override
    protected HashMap<String, Object> getValuesFromModelAttribute() {
        LinkedHashMap<String, Object> values = new LinkedHashMap<>();
        values.put("id", this.getId());
        values.put("anggota_nik", this.getNikAnggota());
        values.put("tgl_transaksi", this.getTglTransaksi());
        values.put("jumlah_uang", this.getJumlahUang());
        values.put("pengurus_nik", this.getPengurus() != null ? this.getPengurus().getNik() : "0000000000000000");
        return values;
        
    }
}
