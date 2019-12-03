/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Constant.Constant;
import Core.Database.CoreDatabaseTable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

/**
 *
 * @author Muttabi Hudaya
 */
public class Pendaftar extends CoreDatabaseTable<Pendaftar>{
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
    
    public Pendaftar() {
        super("anggota", Constant.SIMPAN_PINJAM_TABLE.get("anggota"));
    }
    
    
    public boolean registrasi(){
        return super.save(this.getValuesFromModelAttribute());
    }
    
    public boolean tolak(){
        HashMap<String, Object> values = new HashMap<>();
        values.put("is_aktif", false);
        values.put("is_stop", true);
        
        HashMap<String, Object> whereValues = new HashMap<>();
        whereValues.put("nik", this.getNik());
        return super.update(values, whereValues);
    }
    
    public boolean aktifasi(){
        LinkedHashMap<String, Object> values = new LinkedHashMap<>();
        values.put("is_aktif", true);
        values.put("is_stop", false);
        values.put("is_pengurus", false);
        java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());
        values.put("tgl_aktif", date);
        HashMap<String, Object> whereValues = new HashMap<>();
        whereValues.put("nik", this.getNik());
        
        return super.update(values, whereValues);
    }
    
    public List<Pendaftar> getAll(){
        HashMap<String, Object> whereValues = new HashMap<>();
        whereValues.put("is_aktif", false);
        whereValues.put("is_stop", false);
        return super.getAllByFilter(whereValues);
    }

    @Override
    public Pendaftar map(Object[] values) {
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
        
        return this;
    }
    
    
    @Override
    public HashMap<String, Object> getValuesFromModelAttribute() {
        LinkedHashMap<String, Object> values = new LinkedHashMap<>();
        values.put("nik", this.nik);
        values.put("nama", this.nama);
        values.put("ttl", this.ttl);
        values.put("jenis_kelamin", this.jenisKelamin);
        values.put("golongan_darah", this.golonganDarah);
        values.put("agama", this.agama);
        values.put("pekerjaan", this.pekerjaan);
        values.put("alamat", this.alamat);
        values.put("email", this.email);
        values.put("no_telp", this.noTelp);
        values.put("email", this.email);
        values.put("password", this.password);
        values.put("is_pengurus", false);
        values.put("is_aktif", false);
        values.put("is_stop", false);
                
        return values;
    }
    
    //Getter and Setter
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

    public String getAgama() {
        return agama;
    }

    public void setAgama(String agama) {
        this.agama = agama;
    }

    public String getGolonganDarah() {
        return golonganDarah;
    }

    public void setGolonganDarah(String golonganDarah) {
        this.golonganDarah = golonganDarah;
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
}
