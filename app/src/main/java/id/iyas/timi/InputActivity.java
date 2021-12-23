package id.iyas.timi;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class InputActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText txtTitle, txtDescription;
    private Button btnSave;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        dbHelper = new DBHelper(InputActivity.this);

        txtTitle = findViewById(R.id.txtTitle);
        txtDescription = findViewById(R.id.txtDescription);

        btnSave = findViewById(R.id.btnSave);
        btnSave.setOnClickListener(this);

        loadData();
    }

    private void loadData(){
        txtTitle.setText(getIntent().getStringExtra("TITLE"));
        txtDescription.setText(getIntent().getStringExtra("DESC"));
    }

    @Override
    public void onClick(View v) {
        if(v == btnSave){
            String mode = getIntent().getStringExtra("MODE");
            Log.d("MODE", mode);
            if(mode.equals("TAMBAH")){
                dbHelper.dml("insert into list values ('" + txtTitle.getText().toString() + "','" + txtDescription.getText().toString() + "');");
                Toast.makeText(this, "Data Tersimpan", Toast.LENGTH_SHORT).show();

            }else if(mode.equals("EDIT")){
                dbHelper.dml("update list set description = '" + txtDescription.getText().toString() +"' where title = '" + txtTitle.getText().toString() + "';");
                Toast.makeText(this, "Data Terubah", Toast.LENGTH_SHORT).show();
            }
            finish();
        }
    }
}
