package gm.almacenamientodearchivos.vistacontrol;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import gm.almacenamientodearchivos.R;

public class InicioActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        EditText editTextNombre, editTextPassword;
        ClickIngresar clickIngresar;
        ClickRegistrar clickRegistrar;
        Button buttonIngresar, buttonRegistrar;

        setContentView(R.layout.activity_inicio);

        editTextNombre = findViewById(R.id.editTextNombre);
        editTextPassword = findViewById(R.id.editTextPassword);

        clickIngresar = new ClickIngresar(this, editTextNombre, editTextPassword);
        clickRegistrar = new ClickRegistrar(this, editTextNombre, editTextPassword);

        buttonIngresar = findViewById(R.id.buttonIngresar);
        buttonIngresar.setOnClickListener(clickIngresar);

        buttonRegistrar = findViewById(R.id.buttonRegistrar);
        buttonRegistrar.setOnClickListener(clickRegistrar);
    }
}
