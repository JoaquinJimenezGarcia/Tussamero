package com.carmware.tussamero;


import android.os.Bundle;
import android.view.MenuItem;
import android.app.Activity;
import android.widget.TextView;


public class Info extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recargo);

        final TextView desarrollador = (TextView) findViewById(R.id.t_desarrollador);
            desarrollador.setText("Joaquín Jiménez");
        final TextView descripcion = (TextView) findViewById(R.id.t_descripcion);
            descripcion.setText("Esta aplicación actúa de modo de contador; es decir, le pone un contador con el valor del viaje sobre tu saldo total. No actualiza tu saldo por si solo.");
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


