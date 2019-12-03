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
        put("pinjam", new String[]{"id", "anggota_nik",  "tipe_bunga_id", "suku_bunga", "tenor", "is_diterima", "is_lunas", "tgl_transaksi", "jumlah_uang", "pengurus_nik"});
        put("angsuran", new String[]{"id", "pinjmam_id", "tgl_transaksi", "jumlah_uang", "jumlah_bunga", "pengurus_nik"});
        put("ambil", new String[]{"id", "anggota_nik", "tgl_transaksi", "jumlah_uang", "pengurus_nik"});
        
        //Master Data
        put("berkas", new String[]{"id", "nama"});
        put("tipe_simpanan", new String[]{"id", "nama"});
        put("tipe_bunga", new String[]{"id", "nama"});
    }};
    
    public static final HashMap<Integer, String> BERKAS = new HashMap<Integer, String>(){{
        put(1, "Foto Copy KTP");
        put(2, "Kartu Keluarga");
        put(3, "Slip Gaji");
        put(4, "Tagihan Listrik");
    }};
    
    public static final HashMap<String, Integer> TIPE_BUNGA = new HashMap<String, Integer>(){{
        put("Menurun", 1);
        put("Menurun Efektif", 2);
    }};
    
    public static final HashMap<String, Integer> TENOR = new HashMap<String, Integer>(){{
        put("6 Bulan", 6);
        put("12 Bulan", 12);
        put("18 Bulan", 18);
        put("24 Bulan", 24);
        put("36 Bulan", 36);
        put("48 Bulan", 48);
    }};
    
    public static final HashMap<String, Integer> TIPE_SIMPANAN = new HashMap<String, Integer>(){{
        put("Wajib", 1);
        put("Pokok", 2);
        put("Sukarela", 3);
    }};
    
    public static final HashMap<Integer, String> AGAMA = new HashMap<Integer, String>(){{
        put(1, "Islam");
        put(2, "Kristen");
        put(3, "Katolik");
        put(4, "Hindu");
        put(5, "Budha");
    }};
    
    public static final Integer MAX_PEMINJAMAN = 100000000;
    public static final Integer MIN_PEMINJAMAN = 100000;
}
