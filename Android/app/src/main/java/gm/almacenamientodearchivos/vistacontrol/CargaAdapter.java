package gm.almacenamientodearchivos.vistacontrol;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import gm.almacenamientodearchivos.R;

public class CargaAdapter extends ArrayAdapter {

    private Context context;
    private String[] campos, contenidos;

    CargaAdapter(Context context) {

        super(context, R.layout.listview_carga);

        this.context = context;
        this.campos = new String[]{"Estado", "Tipo"};
        this.contenidos = new String[]{"Privado", "Otro"};
    }

    public String[] getContenidos() {

        return contenidos;
    }

    @Override
    public int getCount() {

        return contenidos.length;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater;
        View view;
        CargaViewHolder cargaViewHolder;

        if (convertView == null) {

            inflater = LayoutInflater.from(context);
            view = inflater.inflate(R.layout.listview_carga, parent, false);

            cargaViewHolder = new CargaViewHolder(view);
            cargaViewHolder.getCampo().setText(campos[position]);
            cargaViewHolder.getContenido().setText(contenidos[position]);

            view.setTag(cargaViewHolder);
        }
        else {

            view = convertView;

            cargaViewHolder = (CargaViewHolder) view.getTag();
            cargaViewHolder.getCampo().setText(campos[position]);
            cargaViewHolder.getContenido().setText(contenidos[position]);
        }

        return view;
    }
}
