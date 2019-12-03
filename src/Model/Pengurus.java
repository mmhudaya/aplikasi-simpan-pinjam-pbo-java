/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Constant.Constant;
import java.sql.Timestamp;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

/**
 *
 * @author Muttabi Hudaya
 */
public class Pengurus extends Anggota {
    
    //Aktifasi
    public boolean updateKelengkapanBerkas(String nikPendaftar, List<Integer> daftarBerkas){
        boolean success = true;
        for(int berkasId: daftarBerkas){
            success = super.customCUDQuery("INSERT INTO `berkas_anggota`(`anggota_nik`, `berkas_id`, `tgl_diberikan`) VALUES('"+nikPendaftar+"', "+berkasId+", NOW()) ");
            if(!success){
                return false;
            }
        }
       return success;
    }
    
    public boolean aktifasiPendaftar(String nikPendaftar){
        Pendaftar pendaftar = new Pendaftar();
        pendaftar.setNik(nikPendaftar);
        return pendaftar.aktifasi();
    }
    
    //Pengangkatan
    public boolean angkatAnggotaMenjadiPengurus(String nikAnggota){
        Anggota anggota = new Anggota();
        anggota.setNik(nikAnggota);
        
        return anggota.menjadiPengurus();
    }
    
    //Transaksi
    public Double getTotalSimpananKoperasi(){
        return new Simpanan().getTotalTransaksi();
    }
    public Double getTotalPinjamanKoperasi(){
        return new Pinjaman().getTotalTransaksi();
    }
    public Double getTotalAngsuranKoperasi(){
        return new Angsuran().getTotalTransaksi();
    }
    public Double getTotalPenarikanKoperasi(){
        return new Penarikan().getTotalTransaksi();
    }
    
    public boolean transaksi(Simpanan simpanan){
        Anggota anggota = new Anggota();
        anggota.selectOne("nik", simpanan.getNikAnggota());
        simpanan.setId(simpanan.getId(anggota.getNama(), "Simpanan"));
        simpanan.setPengurus(this);
        simpanan.setTglTransaksi(new Timestamp(new Date().getTime()));
        return simpanan.save(simpanan.getValuesFromModelAttribute());
    }
    
    public boolean transaksi(Pinjaman pinjaman){
        Anggota anggota = new Anggota();
        anggota.selectOne("nik", pinjaman.getNikAnggota());
        pinjaman.setId(pinjaman.getId(anggota.getNama(), "Pinjaman"));
        pinjaman.setPengurus(this);
        pinjaman.setTglTransaksi(new Timestamp(new Date().getTime()));
        return pinjaman.save(pinjaman.getValuesFromModelAttribute());
    }
    
    
    public boolean transaksi(Angsuran angsuran){
        Anggota anggota = new Anggota();
        anggota.selectOne("nik", angsuran.getNikAnggota());
        angsuran.setId(angsuran.getId(anggota.getNama(), "Angsuran"));
        angsuran.setPengurus(this);
        angsuran.setTglTransaksi(new Timestamp(new Date().getTime()));
        return angsuran.save(angsuran.getValuesFromModelAttribute());
    }
    
    public boolean transaksi(Penarikan penarikan){
        Anggota anggota = new Anggota();
        anggota.selectOne("nik", penarikan.getNikAnggota());
        penarikan.setId(penarikan.getId(anggota.getNama(), "Tarik"));
        penarikan.setPengurus(this);
        penarikan.setTglTransaksi(new Timestamp(new Date().getTime()));
        return penarikan.save(penarikan.getValuesFromModelAttribute());
    }
    
    //Deaktifasi
    public boolean nonAktifasiAnggota(String nikAnggota){
        Anggota anggota = new Anggota();
        anggota.setNik(nikAnggota);
        return anggota.nonAktifasi();
    }
}
