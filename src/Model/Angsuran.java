/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Constant.Constant;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;  
import static java.lang.Math.toIntExact;
import java.sql.Timestamp;
import java.util.LinkedHashMap;


/**
 *
 * @author Muttabi Hudaya
 */
public class Angsuran extends Transaksi<Angsuran> {
    private double jumlahBunga;
    private String pinjamId;
    
    public Angsuran() {
        super("angsuran", Constant.SIMPAN_PINJAM_TABLE.get("angsuran"));
    }
    
    public List<Angsuran> getSemuaAngsuran(String idPeminjaman){
        HashMap<String, Object> whereValues = new HashMap<>();
        whereValues.put("pinjam_id", idPeminjaman);
        return this.convertToThis(super.getAllByFilter(whereValues));
    }
    
    public Double getTotalAngsuran(String nik){
        Object[] res =  super.customReadOneQuery("SELECT SUM(angsuran.jumlah_uang) FROM angsuran INNER JOIN pinjam ON angsuran.pinjam_id = pinjam.id WHERE pinjam.anggota_nik ='"+nik+"'", 1);
        if(res != null && res[0] != null){
            return (Double) res[0];
        }
        return 0.0;
    }
    
    public Double getTotalAngsuranPinjaman(String idPinjaman){
        Object[] res = this.customReadOneQuery("SELECT SUM(angsuran.jumlah_uang) FROM angsuran WHERE pinjam_id='"+idPinjaman+"'", 1);
        if(res != null && res[0] != null){
            return (Double) res[0];
        }
        return 0.0;
    }
    
    public Integer getAngsuranKe(String idPinjaman){
        Object[] res = this.customReadOneQuery("SELECT COUNT(id) FROM angsuran WHERE pinjam_id='"+idPinjaman+"'", 1);
        if(res != null && res[0] != null){
            return toIntExact((Long) res[0]) + 1;
        }
        return 1;
    }
    
    public double getBayar(String idPinjam){
        Pinjaman pinjaman = new Pinjaman().get(idPinjam);
        double angsuran = pinjaman.getJumlahUang() / pinjaman.getTenor();
        
        return angsuran + this.getBunga(pinjaman.getIdTipeBunga(), idPinjam);
    }
    
    public double getBunga(int tipeBunga, String idPinjam){
        double totalAngsuran = this.getTotalAngsuranPinjaman(idPinjam);
        Pinjaman pinjaman = new Pinjaman().get(idPinjam);
        double totalPinjaman = pinjaman.getJumlahUang();
        
        double bunga = 0;
        if(tipeBunga == 1){ //Menurun
//            (Sisa Pinjaman Pokok x Suku Bunga)/30) x Jumlah Hari : Menurun
            bunga = ((totalPinjaman - totalAngsuran)  * ((pinjaman.getSukuBunga()/100) /30) ) * (pinjaman.getTenor() / 30);
        }else if(tipeBunga == 2){
//            Saldo Akhir Pinjaman x (Suku Bunga Per Tahun/12) : Menurun efektif
            bunga = (totalPinjaman - totalAngsuran) * ((pinjaman.getSukuBunga()/12) * 0.01 );
        }
        
        return bunga;
    }
    
    public void setBunga(int tipeBunga){
        //TODO Formula hitung bunga
    }
    
    private double getMinimalAngsuran(){
        return 0.0;
    }
    
    @Override
    protected List<Angsuran> convertToThis(List<Transaksi> listTransaksi){
        List<Angsuran> listAngsuran = new ArrayList<>();
        for(Transaksi transaksi: listTransaksi){
            listAngsuran.add((Angsuran) transaksi);
        }
        
        return listAngsuran;
    }
    
    public double getJumlahBunga() {
        return jumlahBunga;
    }

    public void setJumlahBunga(double jumlahBunga) {
        this.jumlahBunga = jumlahBunga;
    }

    public String getPinjamId() {
        return pinjamId;
    }

    public void setPinjamId(String pinjamId) {
        this.pinjamId = pinjamId;
    }

    @Override
    public Angsuran map(Object[] values) {
        super.setId(values[0] != null ? (String) values[0] : null);
        this.setPinjamId(values[1] != null ? (String) values[1] : null);
        super.setTglTransaksi(values[2] != null ? (Timestamp) values[2] : null);
        super.setJumlahUang(values[3] != null ? (Double) values[3] : null);
        this.setJumlahBunga(values[4] != null ? (Double) values[4] : null);
        Pengurus pengurus = new Pengurus();
        pengurus.setNik(values[5] != null ? (String) values[5] : null);
        super.setPengurus(pengurus);
        super.setTipeTransaksi("Angsuran");
        
        return this;
    }

    @Override
    protected HashMap<String, Object> getValuesFromModelAttribute() {
        LinkedHashMap<String, Object> values = new LinkedHashMap<>();
        values.put("id", this.getId());
        values.put("pinjam_id", this.getPinjamId());
        values.put("tgl_transaksi", this.getTglTransaksi());
        values.put("jumlah_uang", this.getJumlahUang());
        values.put("jumlah_bunga", this.getJumlahBunga());
        values.put("pengurus_nik", this.getPengurus() != null ? this.getPengurus().getNik() : "0000000000000000");
        
        return values;
        
    }
    
}
