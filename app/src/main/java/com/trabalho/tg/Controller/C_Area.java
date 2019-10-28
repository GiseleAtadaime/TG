package com.trabalho.tg.Controller;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.trabalho.tg.Helper.Contrato;
import com.trabalho.tg.Helper.DBHelper;
import com.trabalho.tg.Model.Area;

import java.util.ArrayList;

public class C_Area {


    public ArrayList<Area> selectArea(DBHelper dbHelper, Boolean aberto){
        Cursor c = null;
        ArrayList<Area> a = new ArrayList<>();

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String query = "SELECT * FROM " + Contrato.Area.TABLENAME;

        if (aberto){
            query = query.concat(" WHERE " + Contrato.Area.COLUMN_DEL + " = '" + Contrato.Area.STATUS_ATIVO + "';");
        }
        else{
            query = query.concat(" WHERE " + Contrato.Area.COLUMN_DEL + " = '" + Contrato.Area.STATUS_DELETADO + "';");
        }

        try{
            c = db.rawQuery(query,null);
            Integer i = 0;
            if (c.moveToFirst()) {
                do{
                    a.add(new Area(c.getInt(c.getColumnIndex(Contrato.Area.COLUMN_ID))));
                    a.get(i).setAr_nome(c.getString(c.getColumnIndex(Contrato.Area.COLUMN_NOME)));
                    a.get(i).setAr_imagem(c.getString(c.getColumnIndex(Contrato.Area.COLUMN_IMAGEM)));
                    a.get(i).setAr_lote_cont(c.getString(c.getColumnIndex(Contrato.Area.COLUMN_LOTE_CONT)));
                    a.get(i).setAr_del(c.getString(c.getColumnIndex(Contrato.Area.COLUMN_DEL)));
                    i++;
                }
                while(c.moveToNext());
                c.close();
            }
        }
        finally {

            return a;
        }
    }

    public Boolean insertArea(DBHelper dbHelper, Area a, Integer uID){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Boolean ret = false;

        ContentValues values = new ContentValues();
        values.put(Contrato.Area.COLUMN_NOME,a.getAr_nome());
        values.put(Contrato.Area.COLUMN_IMAGEM,a.getAr_imagem());
        values.put(Contrato.Area.COLUMN_LOTE_CONT,a.getAr_lote_cont());
        values.put(Contrato.Area.COLUMN_USR_ID, uID);
        values.put(Contrato.Area.COLUMN_DEL,a.getAr_del());

        try{
            db.insert(Contrato.Area.TABLENAME, null,values);
            ret = true;
            db.setTransactionSuccessful();
        }
        finally{
            db.close();
            return ret;
        }
    }

    public Boolean updateArea(DBHelper dbHelper, Area a){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Boolean ret = false;

        ContentValues values = new ContentValues();

        values.put(Contrato.Area.COLUMN_LOTE_CONT, a.getAr_lote_cont());
        values.put(Contrato.Area.COLUMN_NOME,a.getAr_nome());
        values.put(Contrato.Area.COLUMN_IMAGEM,a.getAr_imagem());
        values.put(Contrato.Area.COLUMN_DEL,a.getAr_del());


        try{

            db.update(Contrato.Area.TABLENAME,values,Contrato.Area.COLUMN_ID + " = ?", new String[]{a.getAr_id().toString()});
            ret = true;
        }
        finally {
            return ret;
        }

    }

}
