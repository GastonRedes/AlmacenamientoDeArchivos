package gm.almacenamientodearchivos.vistacontrol;

import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class DescargarRequest extends StringRequest {

    private String usuario;

    DescargarRequest(DescargarResponse descargarResponse, String usuario) {

        super(Method.POST, "http://192.168.1.7/AlmacenamientoDeArchivos/seleccionar_archivos.php", descargarResponse, descargarResponse);

        this.usuario = usuario;
    }

    @Override
    protected Map<String, String> getParams() {

        Map<String, String> parametros = new HashMap<>();

        parametros.put("usuario", usuario);

        return parametros;
    }
}
