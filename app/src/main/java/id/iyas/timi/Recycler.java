package id.iyas.timi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class Recycler extends AppCompatActivity implements View.OnClickListener{

    private RecyclerView rv;
    private FloatingActionButton fab_add;
    private DBHelper dbHelper;
    private AdapterList adapterList;

    private ArrayList<List> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        dbHelper = new DBHelper(Recycler.this);
        adapterList = new AdapterList(data, Recycler.this);

        rv = findViewById(R.id.rv);
        RecyclerView.LayoutManager layRV = new LinearLayoutManager(Recycler.this);
        rv.setLayoutManager(layRV);
        rv.setItemAnimator(new DefaultItemAnimator());
        rv.setAdapter(adapterList);

        fab_add = findViewById(R.id.fab_add);
        fab_add.setOnClickListener(this);

        loadData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadData();
    }

    @Override
    public void onClick(View v) {
        if(v == fab_add){
            Intent i_input = new Intent(Recycler.this, InputActivity.class);
            i_input.putExtra("MODE", "TAMBAH");
            i_input.putExtra("TITLE", "");
            i_input.putExtra("DESC", "");
            startActivity(i_input);
        }
    }

    private void loadData(){
        data.clear();
        int baris = Integer.parseInt(dbHelper.cari("select count (*) from list;"));
        int kolom = 2;

        String temp[][] = dbHelper.cari_array("select * from list;", baris, kolom);
        for (String[] strings : temp) {
            List a = new List();
            a.setTitle(strings[0]);
            a.setDescription(strings[1]);
            data.add(a);

            adapterList.notifyDataSetChanged();
        }
    }
}