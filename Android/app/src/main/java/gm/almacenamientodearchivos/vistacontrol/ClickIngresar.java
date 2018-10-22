package gm.almacenamientodearchivos.vistacontrol;

import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import gm.almacenamientodearchivos.modelo.NombreInvalidoException;
import gm.almacenamientodearchivos.modelo.PasswordInvalidoException;
import gm.almacenamientodearchivos.modelo.Usuario;

public class ClickIngresar implements View.OnClickListener {

    private Context context;
    private EditText editTextNombre, editTextPassword;

    ClickIngresar(Context context, EditText editTextNombre, EditText editTextPassword) {

        this.context = context;
        this.editTextNombre = editTextNombre;
        this.editTextPassword = editTextPassword;
    }

    @Override
    public void onClick(View v) {

        Usuario usuario;
        IngresarResponse ingresarResponse;
        IngresarRequest ingresarRequest;
        RequestQueue requestQueue;
        Toast toast;

        try {

            usuario = new Usuario(editTextNombre.getText().toString(), editTextPassword.getText().toString());

            ingresarResponse = new IngresarResponse(context, usuario.getNombre());
            ingresarRequest = new IngresarRequest(ingresarResponse ,usuario);

            requestQueue = Volley.newRequestQueue(context);
            requestQueue.add(ingresarRequest);

        } catch (NombreInvalidoException | PasswordInvalidoException exception) {

            toast = Toast.makeText(context, exception.getMessage(), Toast.LENGTH_LONG);
            toast.show();
        }
    }
}
