package gm.almacenamientodearchivos.vistacontrol;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

import gm.almacenamientodearchivos.R;

public class ArchivosAdapter extends ArrayAdapter {

    private Context context;
    private ArrayList<String> nombres, estados, tipos;

    ArchivosAdapter(Context context) {

        super(context, R.layout.listview_archivos);

        this.context = context;
        this.nombres = new ArrayList<>();
        this.estados = new ArrayList<>();
        this.tipos = new ArrayList<>();
    }

    public void agregarNombre(String nombre) {

        nombres.add(nombre);
    }

    public void agregarEstado(String estado) {

        estados.add(estado);
    }

    public void agregarTipo(String tipo) {

        tipos.add(tipo);
    }

    public void vaciar() {

        nombres.clear();
        estados.clear();
        tipos.clear();
    }

    @Override
    public int getCount() {

        return nombres.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater;
        View view;
        ArchivosViewHolder archivosViewHolder;

        if (convertView == null) {

            inflater = LayoutInflater.from(context);
            view = inflater.inflate(R.layout.listview_archivos, parent, false);
            archivosViewHolder = new ArchivosViewHolder(view);

            archivosViewHolder.getNombre().setText(nombres.get(position));
            archivosViewHolder.getEstado().setText(estados.get(position));
            archivosViewHolder.getTipo().setText(tipos.get(position));

            view.setTag(archivosViewHolder);
        }
        else {

            view = convertView;
            archivosViewHolder = (ArchivosViewHolder) view.getTag();

            archivosViewHolder.getNombre().setText(nombres.get(position));
            archivosViewHolder.getEstado().setText(estados.get(position));
            archivosViewHolder.getTipo().setText(tipos.get(position));
        }

        return view;
    }
}
