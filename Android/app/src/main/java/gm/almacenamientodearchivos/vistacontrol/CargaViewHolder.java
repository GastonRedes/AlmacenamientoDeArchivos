package gm.almacenamientodearchivos.vistacontrol;

import android.view.View;
import android.widget.TextView;

import gm.almacenamientodearchivos.R;

public class CargaViewHolder {

    private TextView campo, contenido;

    CargaViewHolder(View view) {

        campo = view.findViewById(R.id.textViewCampo);
        contenido = view.findViewById(R.id.textViewContenido);
    }

    public TextView getCampo() {
        return campo;
    }

    public TextView getContenido() {
        return contenido;
    }
}
