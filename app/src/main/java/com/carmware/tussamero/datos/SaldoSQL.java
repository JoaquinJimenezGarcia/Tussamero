package com.carmware.tussamero.datos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Juaki on 24/03/2015.
 */
public class SaldoSQL extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String NOMBREDB = "saldo.db";

    public static final String TABLE_SALDO = "saldo";
    public static final String CAMPO_SALDO = "saldo";

    //Sentencia SQL para crear la tabla de Clientes
    String sqlCreate = "CREATE TABLE " +TABLE_SALDO+
            " (_id INTEGER PRIMARY KEY AUTOINCREMENT, "+CAMPO_SALDO+" DOUBLE) ";

    public SaldoSQL(Context contexto) {

        super(contexto, NOMBREDB, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqlCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exits "+TABLE_SALDO);
    }
}
