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

public class C_Entrada_Fechado {

    public ArrayList<Entrada> selectEntrada(DBHelper dbHelper, Integer lID){
        Cursor c = null;
        ArrayList<Entrada> e = new ArrayList<>();

        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String query = "SELECT * FROM " + Contrato.Fec_entrada.TABLENAME + " e, " + Contrato.Fec_Entrada_Detalhe.TABLENAME + " d, " +
                Contrato.Tipo_Entrada.TABLENAME + " t " +
                " WHERE " +
                "e." + Contrato.Fec_entrada.COLUMN_DETALHE + " = " + "d." + Contrato.Fec_Entrada_Detalhe.COLUMN_DETALHE_NUMERO +
                " AND t." + Contrato.Tipo_Entrada.COLUMN_TIPO_NUMERO + " = " + "d." + Contrato.Fec_Entrada_Detalhe.COLUMN_TIPO_NUM +
                " AND e." + Contrato.Fec_entrada.COLUMN_LOTE_ID + " = " + lID +
                " order by " + Contrato.Fec_entrada.COLUMN_DATA + " desc ";

        try{
            c = db.rawQuery(query,null);
            Integer i = 0;
            if (c.moveToFirst()) {
                do{
                    e.add(new Entrada(c.getInt(c.getColumnIndex(Contrato.Fec_entrada.COLUMN_NUMERO))));
                    e.get(i).setEnt_detalhe_num(c.getInt(c.getColumnIndex(Contrato.Fec_Entrada_Detalhe.COLUMN_DETALHE_NUMERO)));
                    e.get(i).setEnt_data(new Utils_TG().getStringToDate(c.getString(c.getColumnIndex(Contrato.Fec_entrada.COLUMN_DATA))));
                    e.get(i).setEnt_desc(c.getString(c.getColumnIndex(Contrato.Tipo_Entrada.COLUMN_DESCRICAO)));
                    e.get(i).setEnt_tipo(c.getInt(c.getColumnIndex(Contrato.Fec_Entrada_Detalhe.COLUMN_TIPO_NUM)));
                    e.get(i).setEnt_tempo(c.getDouble(c.getColumnIndex(Contrato.Fec_Entrada_Detalhe.COLUMN_TEMPO)));
                    e.get(i).setEnt_tpun(c.getString(c.getColumnIndex(Contrato.Fec_Entrada_Detalhe.COLUMN_TPUN)));
                    e.get(i).setEnt_qtde(c.getDouble(c.getColumnIndex(Contrato.Fec_Entrada_Detalhe.COLUMN_QTDE)));
                    e.get(i).setEnt_qtun(c.getString(c.getColumnIndex(Contrato.Fec_Entrada_Detalhe.COLUMN_QTUN)));
                    e.get(i).setEnt_mudas_bandeja(c.getInt(c.getColumnIndex(Contrato.Fec_Entrada_Detalhe.COLUMN_MUDAS_BANDEJA)));
                    e.get(i).setEnt_valor(c.getDouble(c.getColumnIndex(Contrato.Fec_Entrada_Detalhe.COLUMN_VALOR)));
                    if(!c.isNull(c.getColumnIndex(Contrato.Fec_Entrada_Detalhe.COLUMN_REG_NUM))){
                        e.get(i).setEnt_reg(new C_Reg_Agrotoxico().selectReg_Agrotoxico_Especifico(dbHelper,c.getInt(c.getColumnIndex(Contrato.Fec_Entrada_Detalhe.COLUMN_REG_NUM))));
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
            }
        }
        finally {

            return e;
        }
    }

    public Boolean insertListEntrada(DBHelper dbHelper, List<Entrada> entrada, Integer lID, Integer uID){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Boolean ret = false;

        ContentValues values = new ContentValues();
        ContentValues valuesDetalhe = new ContentValues();


        for(Entrada e : entrada ){
            values.put(Contrato.Fec_entrada.COLUMN_NUMERO,e.getEnt_numero());
            values.put(Contrato.Fec_entrada.COLUMN_DATA, new Utils_TG().formatDate(e.getEnt_data(), false));
            values.put(Contrato.Fec_entrada.COLUMN_LOTE_ID,lID);
            values.put(Contrato.Fec_entrada.COLUMN_DETALHE,e.getEnt_detalhe_num());
            values.put(Contrato.Fec_entrada.COLUMN_USR_ID, uID);


            valuesDetalhe.put(Contrato.Fec_Entrada_Detalhe.COLUMN_TIPO_NUM,e.getEnt_tipo());
            valuesDetalhe.put(Contrato.Fec_Entrada_Detalhe.COLUMN_TEMPO,e.getEnt_tempo());
            valuesDetalhe.put(Contrato.Fec_Entrada_Detalhe.COLUMN_TPUN,e.getEnt_tpun());
            valuesDetalhe.put(Contrato.Fec_Entrada_Detalhe.COLUMN_QTDE,e.getEnt_qtde());
            valuesDetalhe.put(Contrato.Fec_Entrada_Detalhe.COLUMN_QTUN,e.getEnt_qtun());
            valuesDetalhe.put(Contrato.Fec_Entrada_Detalhe.COLUMN_MUDAS_BANDEJA,e.getEnt_mudas_bandeja());
            valuesDetalhe.put(Contrato.Fec_Entrada_Detalhe.COLUMN_VALOR,e.getEnt_valor());

            if (e.getEnt_reg() != null) {
                valuesDetalhe.put(Contrato.Fec_Entrada_Detalhe.COLUMN_REG_NUM, e.getEnt_reg().getReg_numero());
            }
            valuesDetalhe.put(Contrato.Fec_Entrada_Detalhe.COLUMN_USR_ID,uID);

            try{
                db.insert(Contrato.Fec_entrada.TABLENAME, null,values);
                db.insert(Contrato.Fec_Entrada_Detalhe.TABLENAME, null, valuesDetalhe);
                ret = true;
            }
            catch(Exception error){
                System.out.println(error.getMessage());
                ret = false;
            }
        }
        return ret;
    }

    public Boolean insertEntrada(DBHelper dbHelper, Entrada e, Integer lID, Integer uID){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Boolean ret = false;

        ContentValues values = new ContentValues();
        values.put(Contrato.Fec_entrada.COLUMN_NUMERO,e.getEnt_numero());
        values.put(Contrato.Fec_entrada.COLUMN_DATA, new Utils_TG().formatDate(e.getEnt_data(), false));
        values.put(Contrato.Fec_entrada.COLUMN_LOTE_ID,lID);
        values.put(Contrato.Fec_entrada.COLUMN_DETALHE,e.getEnt_detalhe_num());
        values.put(Contrato.Fec_entrada.COLUMN_USR_ID, uID);

        ContentValues valuesDetalhe = new ContentValues();
        valuesDetalhe.put(Contrato.Entrada_Detalhe.COLUMN_TIPO_NUM,e.getEnt_tipo());
        valuesDetalhe.put(Contrato.Entrada_Detalhe.COLUMN_TEMPO,e.getEnt_tempo());
        valuesDetalhe.put(Contrato.Entrada_Detalhe.COLUMN_TPUN,e.getEnt_tpun());
        valuesDetalhe.put(Contrato.Entrada_Detalhe.COLUMN_QTDE,e.getEnt_qtde());
        valuesDetalhe.put(Contrato.Entrada_Detalhe.COLUMN_QTUN,e.getEnt_qtun());
        valuesDetalhe.put(Contrato.Entrada_Detalhe.COLUMN_MUDAS_BANDEJA,e.getEnt_mudas_bandeja());
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
            db.insert(Contrato.Fec_entrada.TABLENAME, null,values);
            db.insert(Contrato.Fec_Entrada_Detalhe.TABLENAME, null, valuesDetalhe);
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
        valuesTipo.put(Contrato.Fec_Entrada_Detalhe.COLUMN_DETALHE_NUMERO, e.getEnt_detalhe_num());
        valuesTipo.put(Contrato.Fec_Entrada_Detalhe.COLUMN_TEMPO, e.getEnt_tempo());
        valuesTipo.put(Contrato.Fec_Entrada_Detalhe.COLUMN_TPUN, e.getEnt_tpun());
        valuesTipo.put(Contrato.Fec_Entrada_Detalhe.COLUMN_QTDE, e.getEnt_qtde());
        valuesTipo.put(Contrato.Fec_Entrada_Detalhe.COLUMN_QTUN, e.getEnt_qtun());
        valuesTipo.put(Contrato.Fec_Entrada_Detalhe.COLUMN_MUDAS_BANDEJA, e.getEnt_mudas_bandeja());
        valuesTipo.put(Contrato.Fec_Entrada_Detalhe.COLUMN_VALOR, e.getEnt_valor());
        valuesTipo.put(Contrato.Fec_Entrada_Detalhe.COLUMN_REG_NUM, e.getEnt_reg().getReg_numero());

        try{

            db.update(Contrato.Fec_entrada.TABLENAME,values,Contrato.Fec_entrada.COLUMN_NUMERO + " = ?", new String[]{e.getEnt_numero().toString()});
            db.update(Contrato.Fec_Entrada_Detalhe.TABLENAME,valuesTipo,Contrato.Fec_Entrada_Detalhe.COLUMN_DETALHE_NUMERO + " = ?", new String[]{e.getEnt_tipo().toString()});
            db.setTransactionSuccessful();
            ret = true;
        }
        finally {
            db.endTransaction();
            return ret;
        }

    }
    
}
