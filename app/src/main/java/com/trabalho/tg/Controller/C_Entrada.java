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

    public Boolean plantioExists(DBHelper dbHelper, Integer lID){
        Cursor c = null;
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Boolean ret = false;
        String query = "SELECT 1 FROM " + Contrato.Entrada.TABLENAME + " e, " + Contrato.Entrada_Detalhe.TABLENAME + " d, " +
                Contrato.Tipo_Entrada.TABLENAME + " t " +
                " WHERE " +
                "e." + Contrato.Entrada.COLUMN_DETALHE + " = " + "d." + Contrato.Entrada_Detalhe.COLUMN_DETALHE_NUMERO +
                " AND t." + Contrato.Tipo_Entrada.COLUMN_TIPO_NUMERO + " = " + "d." + Contrato.Entrada_Detalhe.COLUMN_TIPO_NUM +
                " AND e." + Contrato.Entrada.COLUMN_LOTE_ID + " = " + lID +
                " AND d." + Contrato.Entrada_Detalhe.COLUMN_TIPO_NUM + " = 1";

        try{
            c = db.rawQuery(query,null);
            if(c.moveToFirst()){
                    ret = true;
            }
            c.close();
            db.endTransaction();
        }
        finally {
            db.close();
            return ret;
        }
    }

    public ArrayList<Entrada> selectEntrada(DBHelper dbHelper, Integer lID){
        Cursor c = null;
        ArrayList<Entrada> e = new ArrayList<>();

        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String query = "SELECT * FROM " + Contrato.Entrada.TABLENAME + " e, " + Contrato.Entrada_Detalhe.TABLENAME + " d, " +
                Contrato.Tipo_Entrada.TABLENAME + " t " +
                " WHERE " +
                "e." + Contrato.Entrada.COLUMN_DETALHE + " = " + "d." + Contrato.Entrada_Detalhe.COLUMN_DETALHE_NUMERO +
                " AND t." + Contrato.Tipo_Entrada.COLUMN_TIPO_NUMERO + " = " + "d." + Contrato.Entrada_Detalhe.COLUMN_TIPO_NUM +
                " AND e." + Contrato.Entrada.COLUMN_LOTE_ID + " = " + lID;

        try{
            c = db.rawQuery(query,null);
            Integer i = 0;
            if (c.moveToFirst()) {
                do{
                    e.add(new Entrada(c.getInt(c.getColumnIndex(Contrato.Entrada.COLUMN_NUMERO))));
                    e.get(i).setEnt_detalhe_num(c.getColumnIndex(Contrato.Entrada_Detalhe.COLUMN_DETALHE_NUMERO));
                    e.get(i).setEnt_data(new Utils_TG().getStringToDate(c.getString(c.getColumnIndex(Contrato.Entrada.COLUMN_DATA))));
                    e.get(i).setEnt_tipo(c.getInt(c.getColumnIndex(Contrato.Entrada_Detalhe.COLUMN_TIPO_NUM)));
                    e.get(i).setEnt_desc(c.getString(c.getColumnIndex(Contrato.Tipo_Entrada.COLUMN_DESCRICAO)));
                    e.get(i).setEnt_tempo(c.getDouble(c.getColumnIndex(Contrato.Entrada_Detalhe.COLUMN_TEMPO)));
                    e.get(i).setEnt_tpun(c.getString(c.getColumnIndex(Contrato.Entrada_Detalhe.COLUMN_TPUN)));
                    e.get(i).setEnt_qtde(c.getDouble(c.getColumnIndex(Contrato.Entrada_Detalhe.COLUMN_QTDE)));
                    e.get(i).setEnt_qtun(c.getString(c.getColumnIndex(Contrato.Entrada_Detalhe.COLUMN_QTUN)));
                    e.get(i).setEnt_mudas_bandeja(c.getInt(c.getColumnIndex(Contrato.Entrada_Detalhe.COLUMN_MUDAS_BANDEJA)));
                    e.get(i).setEnt_valor(c.getDouble(c.getColumnIndex(Contrato.Entrada_Detalhe.COLUMN_VALOR)));
                    e.get(i).setEnt_reg_lote(c.getString(c.getColumnIndex(Contrato.Entrada_Detalhe.COLUMN_REG_LOTE)));
                    if(!c.isNull(c.getColumnIndex(Contrato.Entrada_Detalhe.COLUMN_REG_NUM))){
                        e.get(i).setEnt_reg(new C_Reg_Agrotoxico().selectReg_Agrotoxico_Especifico(dbHelper,c.getInt(c.getColumnIndex(Contrato.Entrada_Detalhe.COLUMN_REG_NUM))));
                    }
                    else{
                        e.get(i).setEnt_reg(null);
                    }

                    if(e.get(i).getEnt_tempo() == 0){
                        e.get(i).setEnt_tempo(null);
                    }
                    if(e.get(i).getEnt_qtde() == 0){
                        e.get(i).setEnt_qtde(null);
                    }
                    if(e.get(i).getEnt_mudas_bandeja() == 0){
                        e.get(i).setEnt_mudas_bandeja(null);
                    }
                    if(e.get(i).getEnt_valor() == 0){
                        e.get(i).setEnt_valor(null);
                    }



                    i++;
                }
                while(c.moveToNext());
                c.close();
                db.endTransaction();

            }
        }
        finally {

            db.close();
            return e;
        }
    }

    public Integer selectLastEntradaDetalheID(DBHelper dbHelper){
        Cursor c = null;
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Integer id = 0;
        String query = "SELECT MAX(" + Contrato.Entrada_Detalhe.COLUMN_DETALHE_NUMERO + ") FROM " + Contrato.Entrada_Detalhe.TABLENAME;

        try{
            c = db.rawQuery(query,null);
            if(c.moveToFirst()){
                id = c.getInt(c.getColumnIndex("MAX(" + Contrato.Entrada_Detalhe.COLUMN_DETALHE_NUMERO + ")"));
            }
            c.close();

        }
        finally {
            db.endTransaction();
            db.close();
            return id;
        }
    }


    public Boolean insertEntrada(DBHelper dbHelper, Entrada e, Integer lID, Integer uID){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        SQLiteDatabase dbR = dbHelper.getReadableDatabase();
        Boolean ret = false;

        ContentValues values = new ContentValues();

        ContentValues valuesDetalhe = new ContentValues();
        valuesDetalhe.put(Contrato.Entrada_Detalhe.COLUMN_TIPO_NUM,e.getEnt_tipo());
        valuesDetalhe.put(Contrato.Entrada_Detalhe.COLUMN_TEMPO,e.getEnt_tempo());
        valuesDetalhe.put(Contrato.Entrada_Detalhe.COLUMN_TPUN,e.getEnt_tpun());
        valuesDetalhe.put(Contrato.Entrada_Detalhe.COLUMN_QTDE,e.getEnt_qtde());
        valuesDetalhe.put(Contrato.Entrada_Detalhe.COLUMN_QTUN,e.getEnt_qtun());
        valuesDetalhe.put(Contrato.Entrada_Detalhe.COLUMN_MUDAS_BANDEJA,e.getEnt_mudas_bandeja());
        valuesDetalhe.put(Contrato.Entrada_Detalhe.COLUMN_REG_LOTE, e.getEnt_reg_lote());
        valuesDetalhe.put(Contrato.Entrada_Detalhe.COLUMN_VALOR,e.getEnt_valor());

        if (e.getEnt_reg() != null){
            valuesDetalhe.put(Contrato.Entrada_Detalhe.COLUMN_REG_NUM,e.getEnt_reg().getReg_numero());
        }
        /*
        else{
            valuesDetalhe.put(Contrato.Entrada_Detalhe.COLUMN_REG_NUM, (byte[]) null);
        }*/
        valuesDetalhe.put(Contrato.Entrada_Detalhe.COLUMN_USR_ID,uID);

        try{
            db.insert(Contrato.Entrada_Detalhe.TABLENAME, null, valuesDetalhe);

            values.put(Contrato.Entrada.COLUMN_DATA, new Utils_TG().formatDate(e.getEnt_data(), false));
            values.put(Contrato.Entrada.COLUMN_LOTE_ID,lID);
            values.put(Contrato.Entrada.COLUMN_DETALHE,selectLastEntradaDetalheID(dbHelper));
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
    public Boolean updateEntrada(DBHelper dbHelper, Entrada e){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Boolean ret = false;


        ContentValues values = new ContentValues();
        ContentValues valuesDetalhe = new ContentValues();

        values.put(Contrato.Entrada.COLUMN_DATA, new Utils_TG().formatDate(e.getEnt_data(), false));
        valuesDetalhe.put(Contrato.Entrada_Detalhe.COLUMN_TEMPO, e.getEnt_tempo());
        valuesDetalhe.put(Contrato.Entrada_Detalhe.COLUMN_TPUN, e.getEnt_tpun());
        valuesDetalhe.put(Contrato.Entrada_Detalhe.COLUMN_QTDE, e.getEnt_qtde());
        valuesDetalhe.put(Contrato.Entrada_Detalhe.COLUMN_QTUN, e.getEnt_qtun());
        valuesDetalhe.put(Contrato.Entrada_Detalhe.COLUMN_MUDAS_BANDEJA, e.getEnt_mudas_bandeja());
        valuesDetalhe.put(Contrato.Entrada_Detalhe.COLUMN_VALOR, e.getEnt_valor());
        valuesDetalhe.put(Contrato.Entrada_Detalhe.COLUMN_REG_LOTE, e.getEnt_reg_lote());

        if (e.getEnt_reg() != null){
            valuesDetalhe.put(Contrato.Entrada_Detalhe.COLUMN_REG_NUM,e.getEnt_reg().getReg_numero());
        }

        try{

            db.update(Contrato.Entrada.TABLENAME,values,Contrato.Entrada.COLUMN_NUMERO + " = ?", new String[]{e.getEnt_numero().toString()});
            db.update(Contrato.Entrada_Detalhe.TABLENAME,valuesDetalhe,Contrato.Entrada_Detalhe.COLUMN_DETALHE_NUMERO + " = ?", new String[]{e.getEnt_tipo().toString()});
            db.setTransactionSuccessful();
            ret = true;
        }
        finally {
            db.endTransaction();
            db.close();
            return ret;
        }

    }

    public Boolean deleteEntrada(DBHelper dbHelper, Entrada entrada){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Boolean ret = false;
        Integer tipoEntradaID = entrada.getEnt_tipo();
        try{

            db.delete(Contrato.Entrada.TABLENAME,Contrato.Entrada.COLUMN_NUMERO + " = ?", new String[]{entrada.getEnt_numero().toString()});
            db.delete(Contrato.Entrada_Detalhe.TABLENAME,Contrato.Entrada_Detalhe.COLUMN_DETALHE_NUMERO + " = ?", new String[]{tipoEntradaID.toString()});

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
                db.delete(Contrato.Entrada_Detalhe.TABLENAME,Contrato.Entrada_Detalhe.COLUMN_DETALHE_NUMERO + " = ?", new String[]{entradas.get(i).getEnt_tipo().toString()});
            }
            ret = true;
        }
        finally {
            db.setTransactionSuccessful();
            db.close();
            return ret;
        }
        
    }

    public Boolean insertTipoEntrada(DBHelper dbHelper){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Boolean ret = false;

        ArrayList<ContentValues> values = new ArrayList<>();

        values.add(new ContentValues());
        values.get(0).put(Contrato.Tipo_Entrada.COLUMN_DESCRICAO,Contrato.Tipo_Entrada.PLANTIO);

        values.add(new ContentValues());
        values.get(1).put(Contrato.Tipo_Entrada.COLUMN_DESCRICAO,Contrato.Tipo_Entrada.ADUBACAO);

        values.add(new ContentValues());
        values.get(2).put(Contrato.Tipo_Entrada.COLUMN_DESCRICAO,Contrato.Tipo_Entrada.AGROTOXICO);

        values.add(new ContentValues());
        values.get(3).put(Contrato.Tipo_Entrada.COLUMN_DESCRICAO,Contrato.Tipo_Entrada.COLHEITA);

        values.add(new ContentValues());
        values.get(4).put(Contrato.Tipo_Entrada.COLUMN_DESCRICAO,Contrato.Tipo_Entrada.MAO_DE_OBRA);

        values.add(new ContentValues());
        values.get(5).put(Contrato.Tipo_Entrada.COLUMN_DESCRICAO,Contrato.Tipo_Entrada.PREJUIZO);

        values.add(new ContentValues());
        values.get(6).put(Contrato.Tipo_Entrada.COLUMN_DESCRICAO,Contrato.Tipo_Entrada.IRRIGACAO);

        int i = 0;

        try{
            do{

                db.insert(Contrato.Tipo_Entrada.TABLENAME, null,values.get(i));
                i++;
            }
            while(i < values.size());

            ret = true;
        }
        finally{
            db.close();
            return ret;
        }
    }


    public ArrayList<String> selectTipoDescricao(DBHelper dbHelper){
        Cursor c = null;
        ArrayList<String> tipo = new ArrayList<>();

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String query = "SELECT * FROM " + Contrato.Tipo_Entrada.TABLENAME + " order by " + Contrato.Tipo_Entrada.COLUMN_TIPO_NUMERO;

        try{
            c = db.rawQuery(query,null);
            Integer i = 0;
            if (c.moveToFirst()) {
                do{
                    tipo.add(c.getString(c.getColumnIndex(Contrato.Tipo_Entrada.COLUMN_DESCRICAO)));
                    i++;
                }
                while(c.moveToNext());
                c.close();
            }
        }
        finally {
            db.close();
            return tipo;
        }
    }
    public Boolean tipoIsInserted(DBHelper dbHelper){
        Boolean ret = false;
        Cursor c = null;
        ArrayList<String> tipo = new ArrayList<>();

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String query = "SELECT COUNT(*) FROM " + Contrato.Tipo_Entrada.TABLENAME ;

        try{
            c = db.rawQuery(query,null);
            if(c.moveToFirst()){
                if(c.getInt(c.getColumnIndex("COUNT(*)")) > 0){
                    ret = true;
                }
            }

        }
        finally {
            c.close();
            return ret;
        }
    }
}
