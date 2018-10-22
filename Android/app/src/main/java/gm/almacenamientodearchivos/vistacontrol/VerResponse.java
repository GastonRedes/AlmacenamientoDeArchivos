package gm.almacenamientodearchivos.vistacontrol;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class VerResponse implements Response.Listener<String>, Response.ErrorListener {

    private ArchivosAdapter archivosAdapter;

    VerResponse(ArchivosAdapter archivosAdapter) {

        this.archivosAdapter = archivosAdapter;
    }

    @Override
    public void onErrorResponse(VolleyError error) {

        error.printStackTrace();
    }

    @Override
    public void onResponse(String response) {

        JSONObject jsonObject;
        JSONArray jsonArray;
        int i;

        try {

            jsonObject = new JSONObject(response);
            jsonArray = jsonObject.getJSONArray("archivos");

            archivosAdapter.vaciar();

            for (i = 0; i < jsonArray.length(); i++) {

                jsonObject = jsonArray.getJSONObject(i);

                archivosAdapter.agregarNombre(jsonObject.optString("nombre"));
                archivosAdapter.agregarEstado(jsonObject.optString("estado"));
                archivosAdapter.agregarTipo(jsonObject.optString("tipo"));
            }

            archivosAdapter.notifyDataSetChanged();

        } catch (JSONException jsonException) {

            jsonException.printStackTrace();
        }
    }
}
