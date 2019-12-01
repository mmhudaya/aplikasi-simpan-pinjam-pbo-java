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
public class Penarikan extends Transaksi<Penarikan> {

    public Penarikan(Date tglTransaksi, Double jumlahUang, Pengurus pengurus) {
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

    @Override
    public Penarikan map(Object[] values) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected HashMap<String, Object> getValuesFromModelAttribute() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
