package com.trabalho.tg.Controller;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.trabalho.tg.Helper.Contrato;
import com.trabalho.tg.Helper.DBHelper;
import com.trabalho.tg.Model.Reg_Agrotoxico;

import java.util.ArrayList;

public class C_Reg_Agrotoxico {

    public ArrayList<Reg_Agrotoxico> selectReg_Agrotoxico(DBHelper dbHelper){
        Cursor c = null;
        ArrayList<Reg_Agrotoxico> r = new ArrayList<>();

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String query = "SELECT * FROM " + Contrato.Reg_Agrotoxico.TABLENAME;

        try{
            c = db.rawQuery(query,null);
            Integer i = 0;
            if (c.moveToFirst()) {
                do{
                    r.add(new Reg_Agrotoxico(c.getInt(c.getColumnIndex(Contrato.Reg_Agrotoxico.COLUMN_NUMERO))));
                    r.get(i).setReg_ing_ativo(c.getString(c.getColumnIndex(Contrato.Reg_Agrotoxico.COLUMN_ING_ATIVO)));
                    r.get(i).setReg_empresa(c.getString(c.getColumnIndex(Contrato.Reg_Agrotoxico.COLUMN_EMPRESA)));
                    r.get(i).setReg_nomecom(c.getString(c.getColumnIndex(Contrato.Reg_Agrotoxico.COLUMN_NOMECOM)));
                    i++;
                }
                while(c.moveToNext());
                db.setTransactionSuccessful();
                c.close();
            }
        }
        finally {

            return r;
        }
    }

    public Reg_Agrotoxico selectReg_Agrotoxico_Especifico(DBHelper dbHelper, Integer regNum){
        Cursor c = null;
        Reg_Agrotoxico r = null;

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String query = "SELECT * FROM " + Contrato.Reg_Agrotoxico.TABLENAME +
                " WHERE " + Contrato.Reg_Agrotoxico.COLUMN_NUMERO + " = " + regNum;

        try{
            c = db.rawQuery(query,null);
            Integer i = 0;
            if (c.moveToFirst()){

                    r = new Reg_Agrotoxico(c.getInt(c.getColumnIndex(Contrato.Reg_Agrotoxico.COLUMN_NUMERO)));
                    r.setReg_ing_ativo(c.getString(c.getColumnIndex(Contrato.Reg_Agrotoxico.COLUMN_ING_ATIVO)));
                    r.setReg_empresa(c.getString(c.getColumnIndex(Contrato.Reg_Agrotoxico.COLUMN_EMPRESA)));
                    r.setReg_nomecom(c.getString(c.getColumnIndex(Contrato.Reg_Agrotoxico.COLUMN_NOMECOM)));
                    db.setTransactionSuccessful();
                    c.close();
            }
        }
        finally {

            return r;
        }
    }
    public Integer selectReg_Agrotoxico_LastID(DBHelper dbHelper){
        Cursor c = null;
        Integer i = null;

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String query = "SELECT MAX(" + Contrato.Reg_Agrotoxico.COLUMN_NUMERO + ") FROM " + Contrato.Reg_Agrotoxico.TABLENAME;

        try{
            c = db.rawQuery(query,null);
            if (c.moveToFirst()){

                i = c.getInt(c.getColumnIndex("MAX(" + Contrato.Reg_Agrotoxico.COLUMN_NUMERO + ")"));
                db.setTransactionSuccessful();
                c.close();
            }
        }
        finally {

            return i;
        }
    }


    public Boolean insertReg_Agrotoxico(DBHelper dbHelper, Reg_Agrotoxico reg){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Boolean ret = false;

        ContentValues values = new ContentValues();
        values.put(Contrato.Reg_Agrotoxico.COLUMN_NOMECOM, reg.getReg_nomecom());
        values.put(Contrato.Reg_Agrotoxico.COLUMN_EMPRESA, reg.getReg_empresa());
        values.put(Contrato.Reg_Agrotoxico.COLUMN_ING_ATIVO, reg.getReg_ing_ativo());

        try{
            db.insert(Contrato.Reg_Agrotoxico.TABLENAME, null,values);
            ret = true;
            db.setTransactionSuccessful();
        }
        finally{
            db.close();
            return ret;
        }
    }
}
