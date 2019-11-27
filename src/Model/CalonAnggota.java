/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Core.Database.IDatabaseModel;

/**
 *
 * @author Muttabi Hudaya
 */
public class CalonAnggota implements IDatabaseModel<CalonAnggota>{
    private String id;
    private String nik;
    private String nama;
    private String ttl;
    private String jenisKelamin;
    private String golonganDarah;
    private String pekerjaan;
    private String alamat;
    private String email;
    private Integer noTelp;
    

    @Override
    public CalonAnggota map(Object[] values) {
        this.setId((String) values[0]);
        this.setNik((String) values[1]);
        this.setNama((String) values[2]);
        this.setTtl((String) values[3]);
        this.setJenisKelamin((String) values[4]);
        this.setGolonganDarah((String) values[5]);
        this.setPekerjaan((String) values[6]);
        this.setAlamat((String) values[7]);
        this.setEmail((String) values[8]);
        this.setNoTelp((Integer) values[9]);
        
        return this;
    }

    @Override
    public Object[] getValuesFromModelAttribute() {
        Object[] values = new Object[]{
            this.id,
            this.nik,
            this.nama,
            this.ttl,
            this.jenisKelamin,
            this.golonganDarah,
            this.pekerjaan,
            this.alamat,
            this.email,
            this.noTelp
        };
                
        return values;
    }
    
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public Integer getNoTelp() {
        return noTelp;
    }

    public void setNoTelp(Integer noTelp) {
        this.noTelp = noTelp;
    }
    
    
}
