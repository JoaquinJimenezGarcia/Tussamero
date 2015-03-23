package com.carmware.tussamero;



import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;



public class RecargoActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recargo);

        final Button volver = (Button) findViewById(R.id.volver);
            volver.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(RecargoActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            });

        final TextView desarrollador = (TextView) findViewById(R.id.t_desarrollador);
            desarrollador.setText("Joaquín Jiménez");
        final TextView version = (TextView) findViewById(R.id.t_version);
            version.setText("1.0.0");
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


