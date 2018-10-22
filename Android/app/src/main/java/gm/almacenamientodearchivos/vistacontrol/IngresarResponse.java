package gm.almacenamientodearchivos.vistacontrol;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;

public class IngresarResponse implements Response.Listener<String>, Response.ErrorListener {

    private Context context;
    private String usuario;

    IngresarResponse(Context context, String usuario) {

        this.context = context;
        this.usuario = usuario;
    }

    @Override
    public void onErrorResponse(VolleyError error) {

        error.printStackTrace();
    }

    @Override
    public void onResponse(String response) {

        Intent intent;
        AppCompatActivity appCompatActivity;
        Toast toast;

        if (response.equals("Ingreso correcto")) {

            intent = new Intent(context, ArchivosActivity.class);
            intent.putExtra("usuario", usuario);
            context.startActivity(intent);

            appCompatActivity = (AppCompatActivity) context;
            appCompatActivity.finish();

        } else {

            toast = Toast.makeText(context, response, Toast.LENGTH_LONG);
            toast.show();
        }
    }
}
