package com.carmware.tussamero.datos;

import android.content.ContentProvider;
import android.content.ContentResolver;
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
public class SaldoProvider extends ContentProvider {
    //Base de datos
    private SaldoSQL saldodb;
    private static final String AUTHORITY = "com.carmware.tussamero";
    private static final String SALDOS_PATH ="saldos";
    //Definición del CONTENT_URI
    private static final String uri = "content://" + AUTHORITY;
    public static final Uri CONTENT_URI = Uri.parse(uri);

    public static final Uri SALDOS_URI = CONTENT_URI.buildUpon().appendPath(SALDOS_PATH).build();

    //UriMatcher
    private static final int SALDOS = 100;
    private static final UriMatcher uriMatcher;

    //Inicializamos el UriMatcher
    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY, SALDOS_PATH, SALDOS);
    }


    @Override
    public boolean onCreate() {
        saldodb = new SaldoSQL(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection,
                        String selection, String[] selectionArgs, String sortOrder) {

        //Si es una consulta a un ID concreto construimos el WHERE
        String where = selection;
        if (uriMatcher.match(uri) == SALDOS) {

            SQLiteDatabase db = saldodb.getWritableDatabase();

            Cursor c = db.query(SaldoSQL.TABLE_SALDO, projection, selection,
                    selectionArgs, null, null, sortOrder);
            return c;
        } else {
            throw new UnsupportedOperationException("uri desconocida");
        }
    }

    @Override
    public String getType(Uri uri) {

        int match = uriMatcher.match(uri);

        switch (match) {
            case SALDOS:
                return ContentResolver.CURSOR_ITEM_BASE_TYPE+"/"+AUTHORITY+"/"+SALDOS_PATH;
            default:
                throw new UnsupportedOperationException("TIPO Uri desconocida");
        }
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {

        long regId = 1;

        SQLiteDatabase db = saldodb.getWritableDatabase();

        regId = db.insert(SaldoSQL.TABLE_SALDO, null, values);

        Uri newUri = ContentUris.withAppendedId(CONTENT_URI, regId);

        return newUri;

    }


    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {

        int cont;

        SQLiteDatabase db = saldodb.getWritableDatabase();

        cont = db.delete(SaldoSQL.TABLE_SALDO, selection, selectionArgs);

        return cont;
    }

    @Override
    public int update(Uri uri, ContentValues values,
                      String selection, String[] selectionArgs) {

        int cont;

        SQLiteDatabase db = saldodb.getWritableDatabase();

        cont = db.update(SaldoSQL.TABLE_SALDO, values, selection, selectionArgs);

        return cont;
    }




}
