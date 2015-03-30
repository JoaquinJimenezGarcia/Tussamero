package com.carmware.tussamero;

import android.content.ContentValues;
import android.content.Intent;
import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.carmware.tussamero.datos.SaldoProvider;
import com.carmware.tussamero.datos.SaldoSQL;


public class MainActivity extends FragmentActivity implements View.OnClickListener, LoaderManager.LoaderCallbacks<Cursor> {
    public static final int LOADER_SALDO = 1;
    public static final double PRECIO_VIAJE = 0.70;
    private Double saldobd;
    private TextView saldoTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//Campo de escritura
        final EditText insertar = (EditText) findViewById(R.id.editText);
//Campo saldo
        getSupportLoaderManager().initLoader(LOADER_SALDO, null, this);
        saldoTextView = (TextView) findViewById(R.id.saldo);

//Botón recargar
        final Button recargar = (Button) findViewById(R.id.buttonrech);
        recargar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View clickeadoBotonRecarga) {

                //Coger el campo del saldo
                final EditText recargo = (EditText) findViewById(R.id.editText);
                String recargoText = recargo.getText().toString();
                double recargoNumero = Double.parseDouble(recargoText);

                updateSaldo(recargoNumero);
                //Sumarle el recargo
                saldobd = saldobd + recargoNumero;
                //Mostrar saldo total
                saldoTextView.setText(String.format("%.2f", saldobd));
                //Volver el campo del saldo a original
                insertar.setText("");
                insertar.setHint("Insertar Saldo");
            }
        });


//Botón viaje
        final Button viajar = (Button) findViewById(R.id.buttontravel);
        viajar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View elementoClikeado) {

                updateSaldo(-PRECIO_VIAJE);
                //Restar viaje
                saldobd = saldobd - PRECIO_VIAJE;
                //Pintar saldo total.
                saldoTextView.setText(String.format("%.2f", saldobd));

            }
        });

        //BOTÓN DE PRUEBA PARA PASAR DE ACTIVITY
        final Button prueba = (Button) findViewById(R.id.nueva);
        prueba.setOnClickListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.nueva) {
            Intent act = new Intent(this, RecargoActivity.class);
            startActivity(act);
        }
    }

    public void updateSaldo(double diferencia) {

        if (saldobd != null) {
            ContentValues values = new ContentValues();
            values.put(SaldoSQL.CAMPO_SALDO, saldobd + diferencia);
            getContentResolver().update(SaldoProvider.SALDOS_URI,
                    values, null, null);
        } else {
            ContentValues values = new ContentValues();
            values.put(SaldoSQL.CAMPO_SALDO, diferencia);
            getContentResolver().insert(SaldoProvider.SALDOS_URI,
                    values);
        }

    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        return new CursorLoader(this, SaldoProvider.SALDOS_URI,
                new String[]{SaldoSQL.CAMPO_SALDO},
                null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> cursorLoader, Cursor cursorsaldo) {

        if (cursorsaldo.moveToFirst()) {
            saldobd = cursorsaldo.getDouble(0);
            saldoTextView.setText(String.format("%.2f", saldobd));
        } else {
            saldobd = 0.0;
            saldoTextView.setText(String.valueOf(0));
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> cursorLoader) {
        saldobd = 0.0;
    }
}
