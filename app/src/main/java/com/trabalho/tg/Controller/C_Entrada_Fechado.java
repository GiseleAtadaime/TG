package com.trabalho.tg.Controller;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.trabalho.tg.Helper.Contrato;
import com.trabalho.tg.Helper.DBHelper;
import com.trabalho.tg.Model.Entrada;
import com.trabalho.tg.Model.Reg_Agrotoxico;

import java.util.ArrayList;

public class C_Entrada_Fechado {

    public ArrayList<Entrada> selectEntrada(DBHelper dbHelper, Integer lID){
        Cursor c = null;
        ArrayList<Entrada> e = new ArrayList<>();

        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String query = "SELECT * FROM " + Contrato.Fec_entrada.TABLENAME + " e, " + Contrato.Tipo_Entrada.TABLENAME + " t WHERE " +
                " e." + Contrato.Fec_entrada.COLUMN_TIPO + " = " + "t." + Contrato.Tipo_Entrada.COLUMN_TIPO_NUMERO +
                " AND e." + Contrato.Fec_entrada.COLUMN_LOTE_ID + " = " + lID;

        try{
            c = db.rawQuery(query,null);
            Integer i = 0;
            if (c.moveToFirst()) {
                do{
                    e.add(new Entrada(c.getInt(c.getColumnIndex(Contrato.Fec_entrada.COLUMN_ID))));
                    //e.get(i).setEnt_data(c.getString(c.getColumnIndex(Contrato.Fec_entrada.COLUMN_DATA)));
                    e.get(i).setEnt_desc(c.getString(c.getColumnIndex(Contrato.Tipo_Entrada.COLUMN_DESCRICAO)));
                    e.get(i).setEnt_tipo(c.getInt(c.getColumnIndex(Contrato.Fec_entrada.COLUMN_TIPO)));
                    e.get(i).setEnt_tempo(c.getDouble(c.getColumnIndex(Contrato.Tipo_Entrada.COLUMN_TEMPO)));
                    e.get(i).setEnt_tpun(c.getString(c.getColumnIndex(Contrato.Tipo_Entrada.COLUMN_TPUN)));
                    e.get(i).setEnt_qtde(c.getDouble(c.getColumnIndex(Contrato.Tipo_Entrada.COLUMN_QTDE)));
                    e.get(i).setEnt_qtun(c.getString(c.getColumnIndex(Contrato.Tipo_Entrada.COLUMN_QTUN)));
                    e.get(i).setEnt_mudas_bandeja(c.getInt(c.getColumnIndex(Contrato.Tipo_Entrada.COLUMN_MUDAS_BANDEJA)));
                    e.get(i).setEnt_valor(c.getDouble(c.getColumnIndex(Contrato.Tipo_Entrada.COLUMN_VALOR)));
                    e.get(i).setEnt_reg(new Reg_Agrotoxico(c.getInt(c.getColumnIndex(Contrato.Tipo_Entrada.COLUMN_REG_NUM))));
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

    public Boolean insertEntrada(DBHelper dbHelper, Entrada e, Integer lID, Integer uID){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Boolean ret = false;

        ContentValues values = new ContentValues();
        values.put(Contrato.Fec_entrada.COLUMN_ID,e.getEnt_numero());
        //values.put(Contrato.Fec_entrada.COLUMN_DATA,e.getEnt_data());
        values.put(Contrato.Fec_entrada.COLUMN_LOTE_ID,lID);
        values.put(Contrato.Fec_entrada.COLUMN_TIPO,e.getEnt_tipo());
        values.put(Contrato.Fec_entrada.COLUMN_USR_ID, uID);

        ContentValues valuesTipo = new ContentValues();
        values.put(Contrato.Tipo_Entrada.COLUMN_TIPO_NUMERO,e.getEnt_tipo());
        values.put(Contrato.Tipo_Entrada.COLUMN_DESCRICAO,e.getEnt_desc());
        values.put(Contrato.Tipo_Entrada.COLUMN_TEMPO,e.getEnt_tempo());
        values.put(Contrato.Tipo_Entrada.COLUMN_TPUN,e.getEnt_tpun());
        values.put(Contrato.Tipo_Entrada.COLUMN_QTDE,e.getEnt_qtde());
        values.put(Contrato.Tipo_Entrada.COLUMN_QTUN,e.getEnt_qtun());
        values.put(Contrato.Tipo_Entrada.COLUMN_MUDAS_BANDEJA,e.getEnt_mudas_bandeja());
        values.put(Contrato.Tipo_Entrada.COLUMN_VALOR,e.getEnt_valor());
        values.put(Contrato.Tipo_Entrada.COLUMN_REG_NUM,e.getEnt_reg().getReg_numero());
        values.put(Contrato.Tipo_Entrada.COLUMN_USR_ID,uID);

        try{
            db.insert(Contrato.Fec_entrada.TABLENAME, null,values);
            db.insert(Contrato.Tipo_Entrada.TABLENAME, null, valuesTipo);
            ret = true;
            db.setTransactionSuccessful();
        }
        finally{
            db.endTransaction();
            db.close();
            return ret;
        }
    }
    //TODO update
    //update should update all fields regardless of modification or keep it as is?
    //Better as is since it's using object values? They won't be modified by the user directly without treatment
    public Boolean updateArea(DBHelper dbHelper, Entrada e){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Boolean ret = false;


        ContentValues values = new ContentValues();
        ContentValues valuesTipo = new ContentValues();

        //values.put(Contrato.Fec_entrada.COLUMN_DATA, e.getEnt_data());
        valuesTipo.put(Contrato.Tipo_Entrada.COLUMN_DESCRICAO, e.getEnt_desc());
        valuesTipo.put(Contrato.Tipo_Entrada.COLUMN_TEMPO, e.getEnt_tempo());
        valuesTipo.put(Contrato.Tipo_Entrada.COLUMN_TPUN, e.getEnt_tpun());
        valuesTipo.put(Contrato.Tipo_Entrada.COLUMN_QTDE, e.getEnt_qtde());
        valuesTipo.put(Contrato.Tipo_Entrada.COLUMN_QTUN, e.getEnt_qtun());
        valuesTipo.put(Contrato.Tipo_Entrada.COLUMN_MUDAS_BANDEJA, e.getEnt_mudas_bandeja());
        valuesTipo.put(Contrato.Tipo_Entrada.COLUMN_VALOR, e.getEnt_valor());
        valuesTipo.put(Contrato.Tipo_Entrada.COLUMN_REG_NUM, e.getEnt_reg().getReg_numero());

        try{

            db.update(Contrato.Fec_entrada.TABLENAME,values,Contrato.Fec_entrada.COLUMN_ID + " = ?", new String[]{e.getEnt_numero().toString()});
            db.update(Contrato.Tipo_Entrada.TABLENAME,valuesTipo,Contrato.Tipo_Entrada.COLUMN_TIPO_NUMERO + " = ?", new String[]{e.getEnt_tipo().toString()});
            db.setTransactionSuccessful();
            ret = true;
        }
        finally {
            db.endTransaction();
            return ret;
        }

    }
    
}
