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

public class ClickRegistrar implements View.OnClickListener {

    private Context context;
    private EditText editTextNombre, editTextPassword;

    ClickRegistrar (Context context, EditText editTextNombre, EditText editTextPassword) {

        this.context = context;
        this.editTextNombre = editTextNombre;
        this.editTextPassword = editTextPassword;
    }

    @Override
    public void onClick(View v) {

        Usuario usuario;
        RegistrarResponse registrarResponse;
        RegistrarRequest registrarRequest;
        RequestQueue requestQueue;
        Toast toast;

        try {

            usuario = new Usuario(editTextNombre.getText().toString(), editTextPassword.getText().toString());

            registrarResponse = new RegistrarResponse(context);
            registrarRequest = new RegistrarRequest(registrarResponse, usuario);

            requestQueue = Volley.newRequestQueue(context);
            requestQueue.add(registrarRequest);

        } catch (NombreInvalidoException | PasswordInvalidoException exception) {

            toast = Toast.makeText(context, exception.getMessage(), Toast.LENGTH_LONG);
            toast.show();
        }
    }
}
