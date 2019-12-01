/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Constant.Constant;
import java.util.List;

/**
 *
 * @author Muttabi Hudaya
 */
public class Pengurus extends Anggota {
    
    //Aktifasi
    public boolean updateKelengkapanBerkas(String nikPendaftar, String[] daftarBerkas){
        return false;
    }
    
    public boolean aktifasiPendaftar(String nikPendaftar){
        //TODO Aktifasi anggota
        return false;
    }
    
    //Pengangkatan
    public boolean angkatAnggotaMenjadiPengurus(String nikAnggota){
        return false;
    }
    
    //Transaksi
    public boolean transaksiSimpanan(String nikAnggota, Simpanan simpanan){
        return false;
    }
    
    public boolean transaksiPinjaman(String nikAnggota, Pinjaman pinjaman){
        return false;
    }
    
    public boolean transaksiAngsuran(String nikAnggota, String idPinjaman, Angsuran angsuran){
        return false;
    }
    
    public boolean transaksiPenarikan(String nikAnggota, Penarikan penarikan){
        return false;
    }
    
    //Deaktifasi
    public boolean deaktifasiAnggota(String nikAnggota){
        //TODO Aktifasi anggota
        return false;
    }
}
