package com.trabalho.tg.Controller;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.trabalho.tg.Helper.Contrato;
import com.trabalho.tg.Helper.DBHelper;
import com.trabalho.tg.Model.Endereco;
import com.trabalho.tg.Model.Reg_Agrotoxico;

import java.util.ArrayList;

public class C_Endereco {

    public ArrayList<Endereco> selectEndereco(DBHelper dbHelper, Integer iID){
        Cursor c = null;
        ArrayList<Endereco> e = new ArrayList<>();

        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String query = "SELECT * FROM " + Contrato.Endereco.TABLENAME  +  " WHERE " + Contrato.Endereco.COLUMN_INFO_ID + " = " + iID + " order by " + Contrato.Endereco.COLUMN_ID + "  asc;";


        try{
            c = db.rawQuery(query,null);
            Integer i = 0;
            if (c.moveToFirst()) {
                do{
                    e.add(new Endereco(c.getInt(c.getColumnIndex(Contrato.Endereco.COLUMN_ID))));
                    e.get(i).setEnd_carty(c.getDouble(c.getColumnIndex(Contrato.Endereco.COLUMN_CARTY)));
                    e.get(i).setEnd_catx(c.getDouble(c.getColumnIndex(Contrato.Endereco.COLUMN_CARTX)));
                    e.get(i).setEnd_Cidade(c.getString(c.getColumnIndex(Contrato.Endereco.COLUMN_CIDADE)));
                    e.get(i).setEnd_logradouro(c.getString(c.getColumnIndex(Contrato.Endereco.COLUMN_LOGRADOURO)));
                    e.get(i).setEnd_bairro(c.getString(c.getColumnIndex(Contrato.Endereco.COLUMN_BAIRRO)));
                    e.get(i).setEnd_cep(c.getInt(c.getColumnIndex(Contrato.Endereco.COLUMN_CEP)));
                    e.get(i).setEnd_uf(c.getString(c.getColumnIndex(Contrato.Endereco.COLUMN_UF)));
                    i++;
                }
                while(c.moveToNext());
                c.close();
            }
        }
        finally {

            return e;
        }
    }

    public Boolean insertEndereco(DBHelper dbHelper, Endereco e, Integer iID, Integer uID){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Boolean ret = false;

        ContentValues values = new ContentValues();
        values.put(Contrato.Endereco.COLUMN_INFO_ID,iID);
        values.put(Contrato.Endereco.COLUMN_CARTX,e.getEnd_catx());
        values.put(Contrato.Endereco.COLUMN_CARTY,e.getEnd_carty());
        values.put(Contrato.Endereco.COLUMN_CIDADE,e.getEnd_Cidade());
        values.put(Contrato.Endereco.COLUMN_LOGRADOURO,e.getEnd_logradouro());
        values.put(Contrato.Endereco.COLUMN_BAIRRO,e.getEnd_bairro());
        values.put(Contrato.Endereco.COLUMN_CEP,e.getEnd_cep());
        values.put(Contrato.Endereco.COLUMN_UF,e.getEnd_uf());
        values.put(Contrato.Endereco.COLUMN_USR_ID, uID);

        try{
            db.insert(Contrato.Endereco.TABLENAME, null,values);
            ret = true;
            //db.setTransactionSuccessful();
        }
        finally{
            //db.endTransaction();
            db.close();
            return ret;
        }
    }
    //TODO update
    //update should update all fields regardless of modification or keep it as is?
    //Better as is since it's using object values? They won't be modified by the user directly without treatment
    public Boolean updateArea(DBHelper dbHelper, Endereco e){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Boolean ret = false;

        ContentValues values = new ContentValues();

        values.put(Contrato.Endereco.COLUMN_CARTY, e.getEnd_carty());
        values.put(Contrato.Endereco.COLUMN_CARTX, e.getEnd_catx());
        values.put(Contrato.Endereco.COLUMN_CIDADE, e.getEnd_Cidade());
        values.put(Contrato.Endereco.COLUMN_LOGRADOURO, e.getEnd_logradouro());
        values.put(Contrato.Endereco.COLUMN_BAIRRO, e.getEnd_bairro());
        values.put(Contrato.Endereco.COLUMN_CEP, e.getEnd_cep());
        values.put(Contrato.Endereco.COLUMN_UF, e.getEnd_uf());

        try{

            db.update(Contrato.Endereco.TABLENAME,values,Contrato.Endereco.COLUMN_ID + " = ?", new String[]{e.getEnd_id().toString()});
            //db.setTransactionSuccessful();
            ret = true;
        }
        finally {
            //db.endTransaction();
            return ret;
        }

    }

    public Boolean deleteEndereco(DBHelper dbHelper, Integer e){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Boolean ret = false;

        try{

            db.delete(Contrato.Endereco.TABLENAME,Contrato.Endereco.COLUMN_ID + " = ?", new String[]{e.toString()});
            ret = true;
        }
        finally {
            return ret;
        }
    }
}
