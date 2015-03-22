package com.carmware.tussamero;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       final TextView saldo = (TextView)findViewById(R.id.saldo);
       saldo.setText(String.valueOf(0));
       final Button recargar = (Button) findViewById(R.id.buttonrech);
       final Button viajar = (Button) findViewById(R.id.buttontravel);
            viajar.setOnClickListener(new View.OnClickListener (){
            @Override
            public void onClick(View elementoClikeado) {

          //Recuperar saldo transformando el campo en números
                String saldoText = saldo.getText().toString();
                double saldoNumeros = Double.parseDouble(saldoText);
          //Restar viaje
                double cuenta = saldoNumeros - 0.70;
          //Pintar saldo total.
                saldo.setText(String.valueOf(cuenta));


            }
        });
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
}
