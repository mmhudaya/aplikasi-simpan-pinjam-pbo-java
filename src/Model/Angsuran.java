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

/**
 *
 * @author Muttabi Hudaya
 */
public class Angsuran extends Transaksi<Angsuran> {
    private double bunga;
    
    public Angsuran(Date tglTransaksi, Double jumlahUang, Pengurus pengurus) {
        super("angsuran", Constant.SIMPAN_PINJAM_TABLE.get("angsuran"));
    }
    
    public List<Angsuran> getSemuaAngsuran(){
        return this.convertToThis(super.getAll());
    }
    
    public List<Angsuran> getSemuaAngsuran(String idPeminjaman){
        HashMap<String, Object> whereValues = new HashMap<>();
        whereValues.put("pinjam_id", idPeminjaman);
        return this.convertToThis(super.getAllByFilter(whereValues));
    }
    
    public double getBunga(){
        //TODO Hitung Bunga
        return 0.0;
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

    @Override
    public Angsuran map(Object[] values) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected HashMap<String, Object> getValuesFromModelAttribute() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
