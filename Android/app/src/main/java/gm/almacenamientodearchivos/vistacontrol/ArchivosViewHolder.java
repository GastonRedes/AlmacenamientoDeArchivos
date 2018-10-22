package gm.almacenamientodearchivos.vistacontrol;

import android.view.View;
import android.widget.TextView;

import gm.almacenamientodearchivos.R;

public class ArchivosViewHolder {

    private TextView nombre, estado, tipo;

    ArchivosViewHolder(View view) {

        nombre = view.findViewById(R.id.textViewNombre);
        estado = view.findViewById(R.id.textViewEstado);
        tipo = view.findViewById(R.id.textViewTipo);
    }

    public TextView getNombre() {

        return nombre;
    }

    public TextView getEstado() {

        return estado;
    }

    public TextView getTipo() {

        return tipo;
    }
}
