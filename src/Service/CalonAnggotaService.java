/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Core.Database.CoreDatabaseTable;
import Core.Database.IDatabaseModel;
import Model.CalonAnggota;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Muttabi Hudaya
 */
public class CalonAnggotaService extends CoreDatabaseTable<CalonAnggota>{
    public CalonAnggotaService() {
        super("anggota", new String[]{"id", "nik", "nama", "ttl", "jenis_kelamin", "golongan_darah", "agama", "pekerjaan", "alamat", "email", "no_telp", "is_active", "is_delete"});
    }
    
    public void register(CalonAnggota calonAnggota){
        super.save(calonAnggota.getValuesFromModelAttribute());
    }
    
    public void reject(String idAnggota){
        HashMap<String, Object> values = new HashMap<>();
        values.put("is_active", false);
        values.put("is_delete", true);
        
        HashMap<String, Object> whereValues = new HashMap<>();
        whereValues.put("id", idAnggota);
        super.update(values, whereValues);
    }
    
    public void activate(String idAnggota){
        HashMap<String, Object> values = new HashMap<>();
        values.put("is_active", true);
        HashMap<String, Object> whereValues = new HashMap<>();
        whereValues.put("id", idAnggota);
        
        super.update(values, whereValues);
    }
    
    public List<CalonAnggota> getAll(){
        HashMap<String, Object> whereValues = new HashMap<>();
        whereValues.put("is_active", false);
        whereValues.put("is_delete", false);
        return super.getAllByFilter(whereValues);
    }

    @Override
    public CalonAnggota map(Object[] values) {
        IDatabaseModel model =  new CalonAnggota();
        return (CalonAnggota) model.map(values);
    }
    
}
