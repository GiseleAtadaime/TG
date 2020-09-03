package com.trabalho.tg.Controller;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.trabalho.tg.Helper.Contrato;
import com.trabalho.tg.Helper.DBHelper;
import com.trabalho.tg.Model.User_Info;

public class C_User_Info {

    public User_Info selectUser_Info(DBHelper dbHelper){
        Cursor c = null;
        User_Info u = null;

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String query = "SELECT * FROM " + Contrato.Usuario_Info.TABLENAME;

        try{
            c = db.rawQuery(query,null);
            if (c.moveToFirst()){

                u = new User_Info(c.getInt(c.getColumnIndex(Contrato.Usuario_Info.COLUMN_INFO_ID)));
                u.setInfo_cnpj(c.getString(c.getColumnIndex(Contrato.Usuario_Info.COLUMN_CNPJ)));
                u.setInfo_nomefantasia(c.getString(c.getColumnIndex(Contrato.Usuario_Info.COLUMN_NOMEFANTASIA)));
                u.setInfo_rzsocial(c.getString(c.getColumnIndex(Contrato.Usuario_Info.COLUMN_RZSOCIAL)));
                u.setInfo_telefone(c.getString(c.getColumnIndex(Contrato.Usuario_Info.COLUMN_TELEFONE)));
                u.setInfo_site(c.getString(c.getColumnIndex(Contrato.Usuario_Info.COLUMN_SITE)));

                c.close();
            }
        }
        finally {

            return u;
        }
    }

    public Boolean insertUser_Info(DBHelper dbHelper,User_Info u, Integer uID){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Boolean ret = false;

        ContentValues values = new ContentValues();
        values.put(Contrato.Usuario_Info.COLUMN_CNPJ,u.getInfo_cnpj());
        values.put(Contrato.Usuario_Info.COLUMN_NOMEFANTASIA,u.getInfo_nomefantasia());
        values.put(Contrato.Usuario_Info.COLUMN_RZSOCIAL, u.getInfo_rzsocial());
        values.put(Contrato.Usuario_Info.COLUMN_SITE,u.getInfo_site());
        values.put(Contrato.Usuario_Info.COLUMN_TELEFONE,u.getInfo_telefone());
        values.put(Contrato.Usuario_Info.COLUMN_USR_ID, uID);

        try{
            db.insert(Contrato.Usuario_Info.TABLENAME, null,values);
            ret = true;
        }
        finally{
            db.close();
            return ret;
        }
    }

    public Boolean updateUser_Info(DBHelper dbHelper, User_Info u){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Boolean ret = false;

        ContentValues values = new ContentValues();

        values.put(Contrato.Usuario_Info.COLUMN_CNPJ,u.getInfo_cnpj());
        values.put(Contrato.Usuario_Info.COLUMN_NOMEFANTASIA,u.getInfo_nomefantasia());
        values.put(Contrato.Usuario_Info.COLUMN_RZSOCIAL, u.getInfo_rzsocial());
        values.put(Contrato.Usuario_Info.COLUMN_SITE,u.getInfo_site());
        values.put(Contrato.Usuario_Info.COLUMN_TELEFONE,u.getInfo_telefone());

        try{

            db.update(Contrato.Usuario_Info.TABLENAME,values,Contrato.Usuario_Info.COLUMN_INFO_ID + " = ?", new String[]{u.getInfo_id().toString()});
            ret = true;
        }
        finally {
            return ret;
        }
    }
}
