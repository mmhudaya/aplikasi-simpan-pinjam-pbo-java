/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Constant;

import java.util.HashMap;

/**
 *
 * @author Muttabi Hudaya
 */
public final class Constant {
    public static final HashMap<String, String[]> SIMPAN_PINJAM_TABLE = new HashMap<String, String[]>(){{
        put("anggota", new String[]{"nik", "nama", "ttl", "jenis_kelamin", "golongan_darah", "agama", "pekerjaan", "alamat", "email", "no_telp", "password", "is_pengurus", "is_aktif", "tgl_aktif", "is_stop"});
        
        //Registrasi
        put("berkas_anggota", new String[]{"anggota_nik", "berkas_id", "tgl_diberikan"});
        
        //Simpan Pinjam Angsuran Pengambilan
        put("simpan", new String[]{"id", "anggota_nik", "tipe_simpanan_id", "tgl_transaksi", "jumlah_uang", "pengurus_nik"});
        put("pinjam", new String[]{"id", "anggota_nik", "tipe_pinjaman_id", "tipe_bunga_id", "suku_bunga", "tenor", "is_diterima", "is_lunas", "tgl_transaksi", "jumlah_uang", "pengurus_nik"});
        put("angsuran", new String[]{"id", "pinjmam_id", "tgl_transaksi", "jumlah_uang", "pengurus_nik"});
        put("ambil", new String[]{"id", "anggota_nik", "tgl_transaksi", "jumlah_uang", "pengurus_nik"});
        
        //Master Data
        put("berkas", new String[]{"id", "nama"});
        put("tipe_simpanan", new String[]{"id", "nama"});
        put("tipe_bunga", new String[]{"id", "nama"});
    }};
    
    public static final HashMap<Integer, String> BERKAS = new HashMap<Integer, String>(){{
        put(1, "Foto Copy KTP");
        put(1, "Kartu Keluarga");
        put(1, "Slip Gaji");
        put(1, "Tagihan Listrik");
    }};
    
    public static final HashMap<Integer, String> TIPE_BUNGA = new HashMap<Integer, String>(){{
        put(1, "Menurun");
        put(1, "Menurun Efektif");
    }};
    
    public static final HashMap<Integer, String> TIPE_SIMPANAN = new HashMap<Integer, String>(){{
        put(1, "Wajib");
        put(1, "Pokok");
        put(1, "Sukarela");
    }};
    
    public static final HashMap<Integer, String> AGAMA = new HashMap<Integer, String>(){{
        put(1, "Islam");
        put(2, "Kristen");
        put(3, "Katolik");
        put(4, "Hindu");
        put(5, "Budha");
    }};
}
