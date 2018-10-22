package gm.almacenamientodearchivos.vistacontrol;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;

public class RegistrarResponse implements Response.Listener<String>, Response.ErrorListener {

    private Context context;

    RegistrarResponse(Context context) {

        this.context = context;
    }

    @Override
    public void onErrorResponse(VolleyError error) {

        error.printStackTrace();
    }

    @Override
    public void onResponse(String response) {

        Toast toast;

        toast = Toast.makeText(context, response, Toast.LENGTH_LONG);
        toast.show();
    }
}
