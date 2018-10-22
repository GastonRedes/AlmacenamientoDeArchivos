package gm.almacenamientodearchivos.vistacontrol;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ListView;

import gm.almacenamientodearchivos.R;

public class CargaActivity extends AppCompatActivity {

    private int idEstado, idTipo;
    private CargaAdapter cargaAdapter;
    private ClickCargaOpcion clickCargaOpcion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        ListView listViewCarga;
        ClickCargar clickCargar;
        Button button;

        setContentView(R.layout.activity_carga);

        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        cargaAdapter = new CargaAdapter(this);

        idEstado = R.id.radioButtonPrivado;
        idTipo = R.id.radioButtonOtro;
        clickCargaOpcion = new ClickCargaOpcion(this, idEstado, idTipo);

        listViewCarga = findViewById(R.id.listViewCarga);
        listViewCarga.setAdapter(cargaAdapter);
        listViewCarga.setOnItemClickListener(clickCargaOpcion);

        clickCargar = new ClickCargar(this);

        button = findViewById(R.id.buttonCargar);
        button.setOnClickListener(clickCargar);
    }

   @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

       final int REQUESTCODE_ESTADO = 1, REQUESTCODE_TIPO = 2, REQUESTCODE_ARCHIVO = 3;
       Bundle extras;
       String texto, usuario, estado, tipo, ruta;
       Uri uri;
       CargaUtilidades cargaUtilidades;
       CargaAsyncTask cargaAsyncTask;

       if (resultCode == Activity.RESULT_OK) {

           switch (requestCode) {

               case REQUESTCODE_ESTADO:

                   extras = data.getExtras();

                   if (extras != null) {

                       idEstado = extras.getInt("id");
                       clickCargaOpcion.setIdEstado(idEstado);

                       texto = extras.getString("texto");
                       cargaAdapter.getContenidos()[0] = texto;
                       cargaAdapter.notifyDataSetChanged();
                   }

                   break;

               case REQUESTCODE_TIPO:

                   extras = data.getExtras();

                   if (extras != null) {

                       idTipo = extras.getInt("id");
                       clickCargaOpcion.setIdTipo(idTipo);

                       texto = extras.getString("texto");
                       cargaAdapter.getContenidos()[1] = texto;
                       cargaAdapter.notifyDataSetChanged();
                   }

                   break;

               case REQUESTCODE_ARCHIVO:

                   extras = getIntent().getExtras();

                   if (extras != null) {

                       usuario = extras.getString("usuario");
                       estado = cargaAdapter.getContenidos()[0];
                       tipo = cargaAdapter.getContenidos()[1];

                       uri = data.getData();
                       cargaUtilidades = new CargaUtilidades();
                       ruta = cargaUtilidades.getRuta(this, uri);

                       cargaAsyncTask = new CargaAsyncTask(this);
                       cargaAsyncTask.execute(usuario, estado, tipo, ruta);
                   }
           }
       }
   }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home){

            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {

        Intent intent;
        Bundle extras;

        extras = getIntent().getExtras();

        if (extras != null) {

            intent = new Intent(this, ArchivosActivity.class);
            intent.putExtra("usuario", extras.getString("usuario"));
            startActivity(intent);
            finish();
        }
    }
}
