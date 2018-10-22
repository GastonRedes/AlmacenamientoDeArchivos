package gm.almacenamientodearchivos.vistacontrol;

import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

import gm.almacenamientodearchivos.modelo.Encriptacion;
import gm.almacenamientodearchivos.modelo.Usuario;

public class IngresarRequest extends StringRequest {

    private Usuario usuario;

    IngresarRequest(IngresarResponse ingresarResponse, Usuario usuario) {

        super(Method.POST, "http://192.168.1.7/AlmacenamientoDeArchivos/seleccionar_usuario.php", ingresarResponse, ingresarResponse);

        this.usuario = usuario;
    }

    @Override
    protected Map<String, String> getParams() {

        Map<String, String> parametros = new HashMap<>();
        Encriptacion encriptacion;

        parametros.put("nombre", usuario.getNombre());

        encriptacion = new Encriptacion();
        parametros.put("password", encriptacion.getHash(usuario.getPassword()));

        return parametros;
    }
}
