package com.carmware.tussamero;

import android.content.Intent;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends Activity implements View.OnClickListener {

    private double total = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//Campo de escritura
       final EditText insertar = (EditText) findViewById(R.id.editText);
//Campo saldo
       final TextView saldo = (TextView)findViewById(R.id.saldo);
       saldo.setText(String.valueOf(0));
       saldo.setText(String.format("%.2f", total));


//Botón recargar
       final Button recargar = (Button) findViewById(R.id.buttonrech);
       recargar.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View clickeadoBotonRecarga) {

               //Coger el campo del saldo
               final EditText recargo = (EditText) findViewById(R.id.editText);
               String recargoText = recargo.getText().toString();
               double recargoNumero = Double.parseDouble(recargoText);
               //Sumarle el recargo
               total = total + recargoNumero;
               //Mostrar saldo total
               saldo.setText(String.format("%.2f", total));
               //Volver el campo del saldo a original
               insertar.setText("");
               insertar.setHint("Insertar Saldo");
           }
       });


//Botón viaje
        final Button viajar = (Button) findViewById(R.id.buttontravel);
            viajar.setOnClickListener(new View.OnClickListener (){
            @Override
            public void onClick(View elementoClikeado) {

          //Restar viaje
                total = total - 0.70;
          //Pintar saldo total.
                saldo.setText(String.format("%.2f",total));

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
}
