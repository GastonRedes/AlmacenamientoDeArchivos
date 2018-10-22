package gm.almacenamientodearchivos.vistacontrol;

import android.content.Context;
import android.os.Environment;
import android.util.Base64;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class DescargarResponse implements Response.Listener<String>, Response.ErrorListener {

    private Context context;

    DescargarResponse(Context context) {

        this.context = context;
    }

    @Override
    public void onErrorResponse(VolleyError error) {

        error.printStackTrace();
    }

    @Override
    public void onResponse(String response) {

        JSONObject jsonObject;
        JSONArray jsonArray;
        File file;
        int i, cantidad;
        FileOutputStream fileOutputStream;
        byte[] bytes;
        Toast toast;

        try {

            jsonObject = new JSONObject(response);
            jsonArray = jsonObject.getJSONArray("archivos");

            file = new File(Environment.getExternalStorageDirectory(), "AlmacenamientoDeArchivos");

            cantidad = 0;

            if (file.exists() || file.mkdir()) {

                for (i = 0; i < jsonArray.length(); i++) {

                    try {

                        jsonObject = jsonArray.getJSONObject(i);

                        fileOutputStream = new FileOutputStream(file + "/" + jsonObject.optString("nombre"));

                        bytes = Base64.decode(jsonObject.optString("contenido"), Base64.DEFAULT);

                        fileOutputStream.write(bytes);
                        fileOutputStream.close();

                        cantidad++;

                    } catch (IOException ioException) {

                        ioException.printStackTrace();
                    }
                }
            }

            toast = Toast.makeText(context, "Archivos descargados: " + Integer.toString(cantidad), Toast.LENGTH_LONG);
            toast.show();

        } catch (JSONException jsonException) {

            jsonException.printStackTrace();
        }
    }
}
