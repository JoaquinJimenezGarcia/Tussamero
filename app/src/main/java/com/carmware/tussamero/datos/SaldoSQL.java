package com.carmware.tussamero.datos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Juaki on 24/03/2015.
 */
public class SaldoSQL extends SQLiteOpenHelper {

    //Sentencia SQL para crear la tabla de Clientes
    String sqlCreate = "CREATE TABLE Saldo " +
            "(_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            " saldo TEXT) " ;
    public SaldoSQL (Context contexto, String nombre,
                                SQLiteDatabase.CursorFactory factory, int version) {

        super(contexto, nombre, factory, version);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqlCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
