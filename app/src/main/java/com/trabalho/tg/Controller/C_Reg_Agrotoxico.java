package com.trabalho.tg.Controller;

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
        String query = "SELECT * FROM " + Contrato.Reg_Agrotoxico.TABLENAME;

        try{
            c = db.rawQuery(query,null);
            Integer i = 0;
            if (c.moveToFirst()){

                    r = new Reg_Agrotoxico(c.getInt(c.getColumnIndex(Contrato.Reg_Agrotoxico.COLUMN_NUMERO)));
                    r.setReg_ing_ativo(c.getString(c.getColumnIndex(Contrato.Reg_Agrotoxico.COLUMN_ING_ATIVO)));
                    r.setReg_empresa(c.getString(c.getColumnIndex(Contrato.Reg_Agrotoxico.COLUMN_EMPRESA)));
                    r.setReg_nomecom(c.getString(c.getColumnIndex(Contrato.Reg_Agrotoxico.COLUMN_NOMECOM)));
                    c.close();
            }
        }
        finally {

            return r;
        }
    }
}
