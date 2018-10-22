package gm.almacenamientodearchivos.vistacontrol;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class ClickCargar implements View.OnClickListener {

    private Context context;

    ClickCargar(Context context) {

        this.context = context;
    }

    @Override
    public void onClick(View v) {

        Intent intent;
        AppCompatActivity appCompatActivity;
        final int REQUESTCODE_ARCHIVO = 3;

        intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);

        appCompatActivity = (AppCompatActivity) context;
        appCompatActivity.startActivityForResult(Intent.createChooser(intent, "Seleccionar archivo"), REQUESTCODE_ARCHIVO);
    }
}
