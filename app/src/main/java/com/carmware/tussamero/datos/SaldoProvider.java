package com.carmware.tussamero.datos;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by Juaki on 24/03/2015.
 */
public class SaldoProvider extends ContentProvider{
    //Base de datos
    private SaldoSQL saldodb;
    private static final String BD_SALDOS = "DBSaldos";
    private static final int BD_VERSION = 1;
    private static final String TABLA_SALDOS = "Saldos";
//Definición del CONTENT_URI
private static final String uri =
            "content://net.saldoprovider/datos";
 public static final Uri CONTENT_URI = Uri.parse(uri);
    //UriMatcher
    private static final int SALDOS = 1;
    private static final int SALDOS_ID = 2;
    private static final UriMatcher uriMatcher;

    //Inicializamos el UriMatcher
    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI("net.saldoprovider/datos", "saldos", SALDOS);
        uriMatcher.addURI("net.saldoprovider/datos", "saldos/#", SALDOS_ID);
    }



    @Override
    public boolean onCreate() {
        saldodb = new SaldoSQL(
                getContext(), BD_SALDOS, null, BD_VERSION);

        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection,
                        String selection, String[] selectionArgs, String sortOrder) {

        //Si es una consulta a un ID concreto construimos el WHERE
        String where = selection;
        if(uriMatcher.match(uri) == SALDOS_ID){
            where = "_id=" + uri.getLastPathSegment();
        }

        SQLiteDatabase db = saldodb.getWritableDatabase();

        Cursor c = db.query(TABLA_SALDOS, projection, where,
                selectionArgs, null, null, sortOrder);

        return c;
    }

    @Override
    public String getType(Uri uri) {

        int match = uriMatcher.match(uri);

        switch (match)
        {
            case SALDOS:
                return "vnd.android.cursor.dir/vnd.sgoliver.saldo";
            case SALDOS_ID:
                return "vnd.android.cursor.item/vnd.sgoliver.saldo";
            default:
                return null;
        }
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {

        long regId = 1;

        SQLiteDatabase db = saldodb.getWritableDatabase();

        regId = db.insert(TABLA_SALDOS, null, values);

        Uri newUri = ContentUris.withAppendedId(CONTENT_URI, regId);

        return newUri;

    }


    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {

        int cont;

        //Si es una consulta a un ID concreto construimos el WHERE
        String where = selection;
        if(uriMatcher.match(uri) == SALDOS_ID){
            where = "_id=" + uri.getLastPathSegment();
        }

        SQLiteDatabase db = saldodb.getWritableDatabase();

        cont = db.delete(TABLA_SALDOS, where, selectionArgs);

        return cont;
    }

    @Override
    public int update(Uri uri, ContentValues values,
                      String selection, String[] selectionArgs) {

        int cont;

        //Si es una consulta a un ID concreto construimos el WHERE
        String where = selection;
        if(uriMatcher.match(uri) == SALDOS_ID){
            where = "_id=" + uri.getLastPathSegment();
        }

        SQLiteDatabase db = saldodb.getWritableDatabase();

        cont = db.update(TABLA_SALDOS, values, where, selectionArgs);

        return cont;
    }

    public static final class Saldos implements BaseColumns
    {
        private Saldos() {}

        //Nombres de columnas
        public static final String COL_SALDO = "saldo";

    }


}
