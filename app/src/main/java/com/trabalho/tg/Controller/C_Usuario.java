package com.trabalho.tg.Controller;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.trabalho.tg.Helper.Contrato;
import com.trabalho.tg.Helper.DBHelper;
import com.trabalho.tg.Model.Usuario;

import java.util.ArrayList;

public class C_Usuario {

    public ArrayList<Usuario> selectUsuario(DBHelper dbHelper){
        Cursor c = null;
        ArrayList<Usuario> u = new ArrayList<>();

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String query = "SELECT * FROM " + Contrato.Usuario.TABLENAME  +  ";";


        try{
            c = db.rawQuery(query,null);
            Integer i = 0;
            if (c.moveToFirst()) {
                do{
                    u.add(new Usuario(c.getInt(c.getColumnIndex(Contrato.Usuario.COLUMN_ID))));
                    u.get(i).setUsr_nome(c.getString(c.getColumnIndex(Contrato.Usuario.COLUMN_NOME)));
                    u.get(i).setUsr_email(c.getString(c.getColumnIndex(Contrato.Usuario.COLUMN_EMAIL)));
                    u.get(i).setUsr_senha(c.getString(c.getColumnIndex(Contrato.Usuario.COLUMN_SENHA)));
                    i++;
                }
                while(c.moveToNext());

                c.close();
            }
        }
        finally {
            db.close();
            return u;
        }
    }

    public Boolean insertUsuario(DBHelper dbHelper, Usuario u){
        /*      Usuario dummyUsuario = new Usuario(1);
        dummyUsuario.setUsr_Apelido("admin");
        dummyUsuario.setUsr_Status(Contrato.UsuarioTB.STATUS_ACTIVE);
        dummyUsuario.setUsr_Nome("Administrador");
        dummyUsuario.setUsr_Email("admin@gmail.com");
        dummyUsuario.setUsr_Senha(12345);

        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Contrato.UsuarioTB.COLUMN_KEY,dummyUsuario.getUsr_Codigo());
        values.put(Contrato.UsuarioTB.COLUMN_APELIDO,dummyUsuario.getUsr_Apelido());
        values.put(Contrato.UsuarioTB.COLUMN_NOME,dummyUsuario.getUsr_Nome());
        values.put(Contrato.UsuarioTB.COLUMN_STATUS, dummyUsuario.getUsr_Status());
        values.put(Contrato.UsuarioTB.COLUMN_EMAIL, dummyUsuario.getUsr_Email());
        values.put(Contrato.UsuarioTB.COLUMN_SENHA, dummyUsuario.getUsr_Senha());

        db.insert(Contrato.UsuarioTB.TABLENAME, null,values);
        */
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Boolean ret = false;

        /*TESTE */
        ContentValues values = new ContentValues();
        values.put(Contrato.Usuario.COLUMN_NOME, u.getUsr_nome());
        values.put(Contrato.Usuario.COLUMN_EMAIL, u.getUsr_email());
        values.put(Contrato.Usuario.COLUMN_SENHA, u.getUsr_senha());

        try{
            db.insert(Contrato.Usuario.TABLENAME, null,values);
            ret = true;
        }
        finally{
            db.close();
            return ret;
        }
    }
}
