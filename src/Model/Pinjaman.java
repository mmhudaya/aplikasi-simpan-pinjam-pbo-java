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
public class Pinjaman extends Transaksi<Pinjaman> {
    private double sukuBunga;
    private int idTipeBunga;
    private List<Angsuran> listAngsuran;
    private Boolean isDiterima;
    private Boolean isLunas;
    
    public Pinjaman(Date tglTransaksi, Double jumlahUang, Pengurus pengurus) {
        super("pinjam", Constant.SIMPAN_PINJAM_TABLE.get("pinjam"));
    }
    
    public List<Pinjaman> getSemuaPinjaman(){
        return this.convertToThis(super.getAll());
    }
    
    public List<Pinjaman> getSemuaPinjaman(String nikAnggota){
        HashMap<String, Object> whereValues = new HashMap<>();
        whereValues.put("nik", nikAnggota);
        return this.convertToThis(super.getAllByFilter(whereValues));
    }
    
    public List<Pinjaman> getSemuaPinjaman(String nikAnggota, int idTipeSimpanan){
        HashMap<String, Object> whereValues = new HashMap<>();
        whereValues.put("nik", nikAnggota);
        whereValues.put("tipe_simpanan_id", idTipeSimpanan);
        return this.convertToThis(super.getAllByFilter(whereValues));
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

    private void setTipeBungaByJumlahPinjaman(double jumlahPinjaman){
        
    }
    
    @Override
    public Pinjaman map(Object[] values) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected HashMap<String, Object> getValuesFromModelAttribute() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
