package com.trabalho.tg.Controller;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.trabalho.tg.Helper.Contrato;
import com.trabalho.tg.Helper.DBHelper;
import com.trabalho.tg.Model.Lote_Fechado;

import java.util.ArrayList;

public class C_Lote_Fechado {
    
    public ArrayList<Lote_Fechado> selectLote_Fechado(DBHelper dbHelper, Integer aID){
        Cursor c = null;
        ArrayList<Lote_Fechado> l = new ArrayList<>();

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String query = "SELECT * FROM " + Contrato.Fec_lote.TABLENAME  +  " WHERE " + Contrato.Fec_lote.COLUMN_AREA_ID + " = " + aID + ";";

        try{
            c = db.rawQuery(query,null);
            Integer i = 0;
            if (c.moveToFirst()) {
                do{
                    l.add(new Lote_Fechado(c.getInt(c.getColumnIndex(Contrato.Fec_lote.COLUMN_ID))));
                    l.get(i).setLot_nome(c.getString(c.getColumnIndex(Contrato.Fec_lote.COLUMN_NOME)));
                    l.get(i).setLot_planta(c.getString(c.getColumnIndex(Contrato.Fec_lote.COLUMN_PLANTA)));
                    l.get(i).setLot_imagem(c.getString(c.getColumnIndex(Contrato.Fec_lote.COLUMN_IMAGEM)));
                    l.get(i).setLot_pdf_link(c.getString(c.getColumnIndex(Contrato.Fec_lote.COLUMN_PDF_LINK)));
                    l.get(i).setLot_userpdf_link(c.getString(c.getColumnIndex(Contrato.Fec_lote.COLUMN_LOT_USERPDF_LINK)));
                    l.get(i).setLot_qrcode_link(c.getString(c.getColumnIndex(Contrato.Fec_lote.COLUMN_QRCODE_LINK)));
                    i++;
                }
                while(c.moveToNext());
                c.close();
            }
        }
        finally {

            return l;
        }
    }

    public Boolean insertLote_Fechado(DBHelper dbHelper, Lote_Fechado l, Integer aID, Integer uID){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Boolean ret = false;

        ContentValues values = new ContentValues();
        values.put(Contrato.Fec_lote.COLUMN_ID,l.getLot_id());
        values.put(Contrato.Fec_lote.COLUMN_NOME,l.getLot_nome());
        values.put(Contrato.Fec_lote.COLUMN_PLANTA, l.getLot_planta());
        values.put(Contrato.Fec_lote.COLUMN_IMAGEM,l.getLot_imagem());
        values.put(Contrato.Fec_lote.COLUMN_PDF_LINK,l.getLot_pdf_link());
        values.put(Contrato.Fec_lote.COLUMN_LOT_USERPDF_LINK,l.getLot_userpdf_link());
        values.put(Contrato.Fec_lote.COLUMN_QRCODE_LINK,l.getLot_qrcode_link());
        values.put(Contrato.Fec_lote.COLUMN_AREA_ID, aID);
        values.put(Contrato.Fec_lote.COLUMN_USR_ID, uID);

        try{
            db.insert(Contrato.Fec_lote.TABLENAME, null,values);
            ret = true;
        }
        finally{
            db.close();
            return ret;
        }
    }

    public Boolean updateLote_Fechado(DBHelper dbHelper, Lote_Fechado l){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Boolean ret = false;

        ContentValues values = new ContentValues();

        values.put(Contrato.Fec_lote.COLUMN_NOME, l.getLot_nome());
        values.put(Contrato.Fec_lote.COLUMN_PLANTA, l.getLot_planta());
        values.put(Contrato.Fec_lote.COLUMN_IMAGEM,l.getLot_imagem());
        values.put(Contrato.Fec_lote.COLUMN_PDF_LINK,l.getLot_pdf_link());
        values.put(Contrato.Fec_lote.COLUMN_LOT_USERPDF_LINK,l.getLot_userpdf_link());
        values.put(Contrato.Fec_lote.COLUMN_QRCODE_LINK,l.getLot_qrcode_link());


        try{

            db.update(Contrato.Fec_lote.TABLENAME,values,Contrato.Fec_lote.COLUMN_ID + " = ?", new String[]{l.getLot_id().toString()});
            ret = true;
        }
        finally {
            return ret;
        }
    }
}
