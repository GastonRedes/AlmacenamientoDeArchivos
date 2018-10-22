package gm.almacenamientodearchivos.vistacontrol;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import gm.almacenamientodearchivos.R;

public class TipoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        Bundle extras;
        int id;
        RadioButton radioButton;

        setContentView(R.layout.activity_tipo);

        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        extras = getIntent().getExtras();

        if (extras != null) {

            id = extras.getInt("id");
            radioButton = findViewById(id);
            radioButton.setChecked(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home){

            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {

        RadioGroup radioGroup;
        int id;
        RadioButton radioButton;
        Intent intent;

        radioGroup = findViewById(R.id.radioGroupTipo);

        id = radioGroup.getCheckedRadioButtonId();
        radioButton = findViewById(id);

        intent = new Intent();

        intent.putExtra("id", id);
        intent.putExtra("texto", radioButton.getText());

        setResult(Activity.RESULT_OK, intent);
        finish();
    }
}
