package gm.almacenamientodearchivos.vistacontrol;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import gm.almacenamientodearchivos.R;

public class ArchivosActivity extends AppCompatActivity {

    private ArchivosAdapter archivosAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        ListView listView;

        setContentView(R.layout.activity_archivos);

        archivosAdapter = new ArchivosAdapter(this);

        listView = findViewById(R.id.listViewArchivos);
        listView.setAdapter(archivosAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_archivos, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Intent intent;
        Bundle extras;
        VerResponse verResponse;
        VerRequest verRequest;
        DescargarResponse descargarResponse;
        DescargarRequest descargarRequest;
        RequestQueue requestQueue;

        switch (item.getItemId()) {

            case R.id.ver:

                extras = getIntent().getExtras();

                if (extras != null) {

                    verResponse = new VerResponse(archivosAdapter);
                    verRequest = new VerRequest(verResponse, extras.getString("usuario"));

                    requestQueue = Volley.newRequestQueue(this);
                    requestQueue.add(verRequest);
                }

                return true;

            case R.id.cargar:

                extras = getIntent().getExtras();

                if (extras != null) {

                    intent = new Intent(this, CargaActivity.class);
                    intent.putExtra("usuario", extras.getString("usuario"));
                    startActivity(intent);
                    finish();
                }

                return true;

            case R.id.descargar:

                extras = getIntent().getExtras();

                if (extras != null) {

                    descargarResponse = new DescargarResponse(this);
                    descargarRequest = new DescargarRequest(descargarResponse, extras.getString("usuario"));

                    requestQueue = Volley.newRequestQueue(this);
                    requestQueue.add(descargarRequest);
                }

                return true;

            case R.id.cerrar:

                onBackPressed();

                return true;

            default:

                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {

        Intent intent;

        intent = new Intent(this, InicioActivity.class);
        startActivity(intent);
        finish();
    }
}
