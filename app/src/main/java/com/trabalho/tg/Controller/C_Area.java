package com.trabalho.tg.Controller;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.trabalho.tg.Helper.Contrato;
import com.trabalho.tg.Helper.DBHelper;
import com.trabalho.tg.Model.Area;

import java.util.ArrayList;

public class C_Area {

    public ArrayList<Area> selectUsuario(DBHelper dbHelper){
        Cursor c = null;
        ArrayList<Area> a = new ArrayList<>();

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String query = "SELECT * FROM " + Contrato.Area.TABLENAME  +  ";";

        try{
            c = db.rawQuery(query,null);
            Integer i = 0;
            if (c.moveToFirst()) {
                do{
                    a.add(new Area(c.getInt(c.getColumnIndex(Contrato.Area.COLUMN_ID))));
                    a.get(i).setAr_nome(c.getString(c.getColumnIndex(Contrato.Area.COLUMN_NOME)));
                    a.get(i).setAr_imagem(c.getString(c.getColumnIndex(Contrato.Area.COLUMN_IMAGEM)));
                    a.get(i).setAr_lote_cont(c.getString(c.getColumnIndex(Contrato.Area.COLUMN_LOTE_CONT)));
                    a.get(i).setAr_del(c.getInt(c.getColumnIndex(Contrato.Area.COLUMN_DEL)));
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
}
