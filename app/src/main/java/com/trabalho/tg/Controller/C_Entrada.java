package com.trabalho.tg.Controller;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.trabalho.tg.Helper.Contrato;
import com.trabalho.tg.Helper.DBHelper;
import com.trabalho.tg.Helper.Utils_TG;
import com.trabalho.tg.Model.Entrada;
import com.trabalho.tg.Model.Reg_Agrotoxico;

import java.util.ArrayList;
import java.util.List;

public class C_Entrada {

    public ArrayList<Entrada> selectEntrada(DBHelper dbHelper, Integer lID){
        Cursor c = null;
        ArrayList<Entrada> e = new ArrayList<>();

        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String query = "SELECT * FROM " + Contrato.Entrada.TABLENAME + " e, " + Contrato.Tipo_Entrada.TABLENAME + " t WHERE " +
                " e." + Contrato.Entrada.COLUMN_TIPO + " = " + "t." + Contrato.Tipo_Entrada.COLUMN_TIPO_NUMERO +
                " AND e." + Contrato.Entrada.COLUMN_LOTE_ID + " = " + lID;

        try{
            c = db.rawQuery(query,null);
            Integer i = 0;
            if (c.moveToFirst()) {
                do{
                    e.add(new Entrada(c.getInt(c.getColumnIndex(Contrato.Entrada.COLUMN_NUMERO))));
                    //e.get(i).setEnt_data(c.getString(c.getColumnIndex(Contrato.Entrada.COLUMN_DATA)));
                    e.get(i).setEnt_desc(c.getString(c.getColumnIndex(Contrato.Tipo_Entrada.COLUMN_DESCRICAO)));
                    e.get(i).setEnt_tipo(c.getInt(c.getColumnIndex(Contrato.Entrada.COLUMN_TIPO)));
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

    public Integer selectLastTipoEntradaID(DBHelper dbHelper){
        Cursor c = null;
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Integer id = 0;
        String query = "SELECT MAX(" + Contrato.Tipo_Entrada.COLUMN_TIPO_NUMERO + ") FROM " + Contrato.Tipo_Entrada.TABLENAME;

        try{
            c = db.rawQuery(query,null);
            if(c.moveToFirst()){
                id = c.getInt(c.getColumnIndex("MAX(" + Contrato.Tipo_Entrada.COLUMN_TIPO_NUMERO + ")"));
            }
            c.close();
        }
        finally {
            return id;
        }
    }


    public Boolean insertEntrada(DBHelper dbHelper, Entrada e, Integer lID, Integer uID){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        SQLiteDatabase dbR = dbHelper.getReadableDatabase();
        Boolean ret = false;

        ContentValues values = new ContentValues();

        ContentValues valuesTipo = new ContentValues();
        valuesTipo.put(Contrato.Tipo_Entrada.COLUMN_DESCRICAO,e.getEnt_desc());
        valuesTipo.put(Contrato.Tipo_Entrada.COLUMN_TEMPO,e.getEnt_tempo());
        valuesTipo.put(Contrato.Tipo_Entrada.COLUMN_TPUN,e.getEnt_tpun());
        valuesTipo.put(Contrato.Tipo_Entrada.COLUMN_QTDE,e.getEnt_qtde());
        valuesTipo.put(Contrato.Tipo_Entrada.COLUMN_QTUN,e.getEnt_qtun());
        valuesTipo.put(Contrato.Tipo_Entrada.COLUMN_MUDAS_BANDEJA,e.getEnt_mudas_bandeja());
        valuesTipo.put(Contrato.Tipo_Entrada.COLUMN_VALOR,e.getEnt_valor());

        if (e.getEnt_reg() != null){
            valuesTipo.put(Contrato.Tipo_Entrada.COLUMN_REG_NUM,e.getEnt_reg().getReg_numero());
        }
        else{
            valuesTipo.put(Contrato.Tipo_Entrada.COLUMN_REG_NUM, (byte[]) null);
        }
        valuesTipo.put(Contrato.Tipo_Entrada.COLUMN_USR_ID,uID);

        try{
            db.insert(Contrato.Tipo_Entrada.TABLENAME, null, valuesTipo);

            values.put(Contrato.Entrada.COLUMN_DATA, new Utils_TG().formatDate(e.getEnt_data(), false));
            values.put(Contrato.Entrada.COLUMN_LOTE_ID,lID);
            values.put(Contrato.Entrada.COLUMN_TIPO,selectLastTipoEntradaID(dbHelper));
            values.put(Contrato.Entrada.COLUMN_USR_ID, uID);

            db.insert(Contrato.Entrada.TABLENAME, null,values);

            ret = true;
            db.setTransactionSuccessful();
        }
        finally{
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

        //values.put(Contrato.Entrada.COLUMN_DATA, e.getEnt_data());
        valuesTipo.put(Contrato.Tipo_Entrada.COLUMN_DESCRICAO, e.getEnt_desc());
        valuesTipo.put(Contrato.Tipo_Entrada.COLUMN_TEMPO, e.getEnt_tempo());
        valuesTipo.put(Contrato.Tipo_Entrada.COLUMN_TPUN, e.getEnt_tpun());
        valuesTipo.put(Contrato.Tipo_Entrada.COLUMN_QTDE, e.getEnt_qtde());
        valuesTipo.put(Contrato.Tipo_Entrada.COLUMN_QTUN, e.getEnt_qtun());
        valuesTipo.put(Contrato.Tipo_Entrada.COLUMN_MUDAS_BANDEJA, e.getEnt_mudas_bandeja());
        valuesTipo.put(Contrato.Tipo_Entrada.COLUMN_VALOR, e.getEnt_valor());
        valuesTipo.put(Contrato.Tipo_Entrada.COLUMN_REG_NUM, e.getEnt_reg().getReg_numero());

        try{

            db.update(Contrato.Entrada.TABLENAME,values,Contrato.Entrada.COLUMN_NUMERO + " = ?", new String[]{e.getEnt_numero().toString()});
            db.update(Contrato.Tipo_Entrada.TABLENAME,valuesTipo,Contrato.Tipo_Entrada.COLUMN_TIPO_NUMERO + " = ?", new String[]{e.getEnt_tipo().toString()});
            db.setTransactionSuccessful();
            ret = true;
        }
        finally {
            db.endTransaction();
            return ret;
        }

    }

    public Boolean deleteEntrada(DBHelper dbHelper, Entrada entrada){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Boolean ret = false;
        Integer tipoEntradaID = entrada.getEnt_tipo();
        try{

            db.delete(Contrato.Entrada.TABLENAME,Contrato.Entrada.COLUMN_NUMERO + " = ?", new String[]{entrada.getEnt_numero().toString()});
            db.delete(Contrato.Tipo_Entrada.TABLENAME,Contrato.Tipo_Entrada.COLUMN_TIPO_NUMERO + " = ?", new String[]{tipoEntradaID.toString()});

            ret = true;
        }
        finally {
            return ret;
        }
    }
    
    public Boolean deleteAllEntradasByLote(DBHelper dbHelper, Integer loteID){

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Boolean ret = false;
        ArrayList<Entrada> entradas = null;
        Integer i = 0;
        try{
            entradas = selectEntrada(dbHelper,loteID);

            for(i=0;i< entradas.size() - 1;i++){
                db.delete(Contrato.Entrada.TABLENAME,Contrato.Entrada.COLUMN_NUMERO + " = ?", new String[]{entradas.get(i).getEnt_numero().toString()});
                db.delete(Contrato.Tipo_Entrada.TABLENAME,Contrato.Tipo_Entrada.COLUMN_TIPO_NUMERO + " = ?", new String[]{entradas.get(i).getEnt_tipo().toString()});
            }
            ret = true;
        }
        finally {
            return ret;
        }
        
    }
}
