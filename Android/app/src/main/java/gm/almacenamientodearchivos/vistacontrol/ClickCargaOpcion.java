package gm.almacenamientodearchivos.vistacontrol;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;

public class ClickCargaOpcion implements AdapterView.OnItemClickListener {

    private Context context;
    private int idEstado, idTipo;

    ClickCargaOpcion(Context context, int idEstado, int idTipo) {

        this.context = context;
        this.idEstado = idEstado;
        this.idTipo = idTipo;
    }

    public void setIdEstado(int idEstado) {

        this.idEstado = idEstado;
    }

    public void setIdTipo(int idTipo) {

        this.idTipo = idTipo;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        AppCompatActivity appCompatActivity;
        Intent intent;
        final int REQUESTCODE_ESTADO = 1, REQUESTCODE_TIPO = 2;

        if (position == 0) {

            intent = new Intent(context, EstadoActivity.class);
            intent.putExtra("id", idEstado);

            appCompatActivity = (AppCompatActivity) context;
            appCompatActivity.startActivityForResult(intent, REQUESTCODE_ESTADO);
        }

        if (position == 1) {

            intent = new Intent(context, TipoActivity.class);
            intent.putExtra("id", idTipo);

            appCompatActivity = (AppCompatActivity) context;
            appCompatActivity.startActivityForResult(intent, REQUESTCODE_TIPO);
        }
    }
}
