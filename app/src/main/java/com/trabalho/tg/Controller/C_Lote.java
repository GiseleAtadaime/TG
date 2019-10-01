package com.trabalho.tg.Controller;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.trabalho.tg.Helper.Contrato;
import com.trabalho.tg.Helper.DBHelper;
import com.trabalho.tg.Model.Lote;

import java.util.ArrayList;

public class C_Lote {


    public ArrayList<Lote> selectLote(DBHelper dbHelper, Integer aID){
        Cursor c = null;
        ArrayList<Lote> l = new ArrayList<>();

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String query = "SELECT * FROM " + Contrato.Lote.TABLENAME  +  " WHERE " + Contrato.Lote.COLUMN_AREA_ID + " = " + aID + ";";

        try{
            c = db.rawQuery(query,null);
            Integer i = 0;
            if (c.moveToFirst()) {
                do{
                    l.add(new Lote(c.getInt(c.getColumnIndex(Contrato.Lote.COLUMN_ID))));
                    l.get(i).setLot_nome(c.getString(c.getColumnIndex(Contrato.Lote.COLUMN_NOME)));
                    l.get(i).setLot_imagem(c.getString(c.getColumnIndex(Contrato.Lote.COLUMN_IMAGEM)));
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

    public Boolean insertLote(DBHelper dbHelper, Lote l, Integer aID, Integer uID){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Boolean ret = false;

        ContentValues values = new ContentValues();
        values.put(Contrato.Lote.COLUMN_NOME,l.getLot_nome());
        values.put(Contrato.Lote.COLUMN_IMAGEM,l.getLot_imagem());
        values.put(Contrato.Lote.COLUMN_AREA_ID, aID);
        values.put(Contrato.Lote.COLUMN_USR_ID, uID);

        try{
            db.insert(Contrato.Lote.TABLENAME, null,values);
            ret = true;
        }
        finally{
            db.close();
            return ret;
        }
    }

    public Boolean updateLote(DBHelper dbHelper, Lote l){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Boolean ret = false;

        ContentValues values = new ContentValues();

        values.put(Contrato.Lote.COLUMN_NOME, l.getLot_nome());
        values.put(Contrato.Lote.COLUMN_IMAGEM,l.getLot_imagem());

        try{

            db.update(Contrato.Lote.TABLENAME,values,Contrato.Lote.COLUMN_ID + " = ?", new String[]{l.getLot_id().toString()});
            ret = true;
        }
        finally {
            return ret;
        }
    }

    //TODO criar excluir Lote
    //O lote vai ser salvo na tabela de fechado e deverá ser excluido da ativa
    //Verificar se a classe da Area deveria ter tbm um objeto do tipo lote_fechado
    //A entrada que é fechada tbm deve ser salva em outra tabela e excluída da original
    //A entrada não precisa de status aberto ou fechado pois ele é definido pelo status do Lote

    public Boolean deleteLote(DBHelper dbHelper, Lote l){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Boolean ret = false;

        try{

            db.delete(Contrato.Lote.TABLENAME,Contrato.Lote.COLUMN_ID + " = ?", new String[]{l.getLot_id().toString()});
            ret = true;
        }
        finally {
            return ret;
        }
    }


}
