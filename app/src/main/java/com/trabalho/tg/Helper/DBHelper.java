package com.trabalho.tg.Helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "TG.db";
    private static final Integer DATABASE_VERSION = 1;

    public DBHelper(Context context){
        super(context,DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Contrato.CREATE_TABLE_USUARIO);
        db.execSQL(Contrato.CREATE_TABLE_USUARIO_INFO);
        db.execSQL(Contrato.CREATE_TABLE_ENDERECO);
        db.execSQL(Contrato.CREATE_TABLE_REG_AGROTOXICO);
        db.execSQL(Contrato.CREATE_TABLE_AREA);
        db.execSQL(Contrato.CREATE_TABLE_LOTE);
        db.execSQL(Contrato.CREATE_TABLE_ENTRADA);
        db.execSQL(Contrato.CREATE_TABLE_LOTE_FECHADO);
        db.execSQL(Contrato.CREATE_TABLE_ENTRADA_FECHADA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
