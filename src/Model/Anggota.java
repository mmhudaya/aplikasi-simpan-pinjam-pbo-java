    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Constant.Constant;
import Core.Database.CoreDatabaseTable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Muttabi Hudaya
 */
public class Anggota extends CoreDatabaseTable<Anggota>{
    private String nik;
    private String nama;
    private String ttl;
    private String jenisKelamin;
    private String golonganDarah;
    private String agama;
    private String pekerjaan;
    private String alamat;
    private String email;
    private String noTelp;
    private String password;
    private Timestamp tglAktif;
    private List<Pinjaman> listPinjaman = new ArrayList<Pinjaman>();
    private List<Simpanan> listSimpanan = new ArrayList<Simpanan>();
    private List<Penarikan> listPenarikan = new ArrayList<Penarikan>();
    
    //Distinct
    private boolean isPengurus;

    public Anggota() {
        super("anggota", Constant.SIMPAN_PINJAM_TABLE.get("anggota"));
    }
    
    public List<Pinjaman> getListPinjaman() {
        return listPinjaman;
    }

    public void setListPinjaman(List<Pinjaman> listPinjaman) {
        this.listPinjaman = listPinjaman;
    }

    public List<Simpanan> getListSimpanan() {
        return listSimpanan;
    }

    public void setListSimpanan(List<Simpanan> listSimpanan) {
        this.listSimpanan = listSimpanan;
    }

    public List<Penarikan> getListPenarikan() {
        return listPenarikan;
    }

    public void setListPenarikan(List<Penarikan> listPenarikan) {
        this.listPenarikan = listPenarikan;
    }

    @Override
    public Anggota map(Object[] values) {
        this.setNik((String) values[0]);
        this.setNama((String) values[1]);
        this.setTtl((String) values[2]);
        this.setJenisKelamin((String) values[3]);
        this.setGolonganDarah((String) values[4]);
        this.setAgama((String) values[5]);
        this.setPekerjaan((String) values[6]);
        this.setAlamat((String) values[7]);
        this.setEmail((String) values[8]);
        this.setNoTelp((String) values[9]);
        this.setPassword((String) values[10]);
        System.out.println(values[11]);
        this.setIsPengurus((Integer) values[11] == 1 ? true : false);
        
        return this;
    }
    
    @Override
    public HashMap<String, Object> getValuesFromModelAttribute() {
        HashMap<String, Object> values = new HashMap<>();
        values.put("nik", this.getNik());
        values.put("nama", this.getNama());
        values.put("ttl", this.getTtl());
        values.put("jenis_kelamin", this.getJenisKelamin());
        values.put("golongan_darah", this.getGolonganDarah());
        values.put("agama", this.getAgama());
        values.put("pekerjaan", this.getPekerjaan());
        values.put("alamat", this.getAlamat());
        values.put("email", this.getEmail());
        values.put("no_telp", this.getNoTelp());
        values.put("email", this.getEmail());
        values.put("password", this.getPassword());
        values.put("tgl_aktif", this.getTglAktif());
                
        return values;
    }
    
    
    public boolean login(){
        HashMap<String, Object> whereValues = new HashMap<>();
        whereValues.put("nik", this.getNik());
        whereValues.put("password", this.getPassword());
        whereValues.put("is_aktif", true);
        
        Object[] dataAnggota = this.getOneByFilter(whereValues);
        if(dataAnggota[0] != null){
            this.map(dataAnggota);
            return true;
        }
        
        return false;
    }
    
    //Getter setter
    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getTtl() {
        return ttl;
    }

    public void setTtl(String ttl) {
        this.ttl = ttl;
    }

    public String getJenisKelamin() {
        return jenisKelamin;
    }

    public void setJenisKelamin(String jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }

    public String getGolonganDarah() {
        return golonganDarah;
    }

    public void setGolonganDarah(String golonganDarah) {
        this.golonganDarah = golonganDarah;
    }

    public String getAgama() {
        return agama;
    }

    public void setAgama(String agama) {
        this.agama = agama;
    }

    public String getPekerjaan() {
        return pekerjaan;
    }

    public void setPekerjaan(String pekerjaan) {
        this.pekerjaan = pekerjaan;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNoTelp() {
        return noTelp;
    }

    public void setNoTelp(String noTelp) {
        this.noTelp = noTelp;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Timestamp getTglAktif() {
        return tglAktif;
    }

    public void setTglAktif(Timestamp tglAktif) {
        this.tglAktif = tglAktif;
    }

    public boolean isPengurus() {
        return isPengurus;
    }

    public void setIsPengurus(boolean isPengurus) {
        this.isPengurus = isPengurus;
    }
    
}
