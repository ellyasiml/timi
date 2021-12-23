package id.iyas.timi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView dashboard_hello,dashboard_all;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dashboard_hello = (TextView) findViewById(R.id.dashboard_hello);
        dashboard_all = (TextView) findViewById(R.id.dashboard_all);

        Intent intent = getIntent();
        String username = intent.getStringExtra("USERNAME");
        dashboard_hello.setText("Welcome to Timi, " +username);

        dashboard_all.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Recycler.class);
                startActivity(intent);
            }
        });
    }

    /*@Override
    protected void onRestart() {
        super.onRestart();
        Toast.makeText(MainActivity.this, "Welcome back!", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(MainActivity.this, "Welcome!", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(MainActivity.this, "Come back!", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(MainActivity.this, "Goodbye!", Toast.LENGTH_SHORT).show();
    }*/
}