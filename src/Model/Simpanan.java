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
        return null;
    }

    @Override
    protected HashMap<String, Object> getValuesFromModelAttribute() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
