    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Constant.Constant;
import Core.Database.CoreDatabaseTable;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
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
    
    //Distinct
    private boolean isPengurus;
    private boolean isAktif;
    
    public Anggota() {
        super("anggota", Constant.SIMPAN_PINJAM_TABLE.get("anggota"));
    }

    @Override
    public Anggota map(Object[] values) {
        Anggota anggota = new Anggota();
        this.setNik(values[0] != null ? (String) values[0] : null);
        this.setNama(values[1] != null ? (String) values[1] : null);
        this.setTtl(values[2] != null ? (String) values[2] : null);
        this.setJenisKelamin(values[3] != null ? (String) values[3] : null);
        this.setGolonganDarah(values[4] != null ? (String) values[4] : null);
        this.setAgama(values[5] != null ? (String) values[5] : null);
        this.setPekerjaan(values[6] != null ? (String) values[6] : null);
        this.setAlamat(values[7] != null ? (String) values[7] : null);
        this.setEmail(values[8] != null ? (String) values[8] : null);
        this.setNoTelp(values[9] != null ? (String) values[9] : null);
        this.setPassword(values[10] != null ? (String) values[10] : null);
        System.out.println(Arrays.toString(values));
        this.setIsPengurus(values[11] != null ? (Integer) values[11] == 1 ? true : false : false );
        this.setIsAktif(values[12] != null ? (Integer) values[12] == 1 ? true : false : false);
        
        return this;
    }
    
    @Override
    public HashMap<String, Object> getValuesFromModelAttribute() {
        LinkedHashMap<String, Object> values = new LinkedHashMap<>();
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
        LinkedHashMap<String, Object> whereValues = new LinkedHashMap<>();
        whereValues.put("nik", this.getNik());
        whereValues.put("password", this.getPassword());
//        whereValues.put("is_aktif", true);
        
        Object[] dataAnggota = this.getOneByFilter(whereValues);
        if(dataAnggota != null && dataAnggota[0] != null){
            this.map(dataAnggota);
            return true;
        }
        
        return false;
    }
    
    //Transaksi
    public double getTotalPinjaman(){
        return new Pinjaman().getTotalTransaksiPribadi(this.getNik());
    }
    public double getTotalSimpanan(){
        return new Simpanan().getTotalTransaksiPribadi(this.getNik());
    }
    public double getTotalSimpanan(int tipeSimpanan){
        Object[] res = super.customReadOneQuery("SELECT SUM(`jumlah_uang`) FROM `"+this.tableName+"` WHERE anggota_nik='"+nik+"' AND tipe_simpanan_id="+tipeSimpanan, 1);
        if(res != null && res[0] != null){
            return (Double) res[0];
        }
        return 0.0;
    }
    
    public List<Integer> getKelengkapanBerkas(){
        List<Object[]> listRes = super.customReadQuery("SELECT * FROM `berkas_anggota` WHERE `anggota_nik`="+this.getNik(), 3);
        List<Integer> listKelengkpan = new ArrayList<>();
        for(Object[] res : listRes){
            listKelengkpan.add(Integer.valueOf(String.valueOf(res[1])));
        }
        
        return listKelengkpan;
    }
    
    public double getTotalAngsuran(){
        return new Angsuran().getTotalAngsuran(this.getNik());
    }
    public double getTotalPenarikan(){
        return new Penarikan().getTotalTransaksiPribadi(this.getNik());
    }
    
    public boolean pengajuanPeminjaman(Pinjaman pinjaman){
        pinjaman.setTglTransaksi(new Timestamp(new Date().getTime()));
        pinjaman.setIsDiterima(false);
        pinjaman.setIsLunas(false);
        pinjaman.setPengurus(null);
        pinjaman.setId(pinjaman.getId(this.getNama(), "Pinjaman"));
        
        return pinjaman.ajukan();
    }
    
    public boolean menjadiPengurus(){
        LinkedHashMap<String, Object> values = new LinkedHashMap<>();
        values.put("is_pengurus", true);
        HashMap<String, Object> whereValues = new HashMap<>();
        whereValues.put("nik", this.getNik());
        
        return super.update(values, whereValues);
    }
    
    public boolean nonAktifasi(){
        LinkedHashMap<String, Object> values = new LinkedHashMap<>();
        values.put("is_aktif", false);
        values.put("is_stop", true);
        HashMap<String, Object> whereValues = new HashMap<>();
        whereValues.put("nik", this.getNik());
        
        return super.update(values, whereValues);
    }
    
    public boolean isAktifDanTerdaftar(String nik){
        LinkedHashMap<String, Object> whereValues = new LinkedHashMap<>();
        whereValues.put("nik", nik);
        whereValues.put("is_aktif", true);
        Object[] res = super.getOneByFilter(whereValues);
        return res != null && res.length > 0;
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

    public boolean isIsAktif() {
        return isAktif;
    }

    public void setIsAktif(boolean isAktif) {
        this.isAktif = isAktif;
    }

    
}
