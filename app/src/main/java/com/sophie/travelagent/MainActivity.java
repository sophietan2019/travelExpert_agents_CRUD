package com.sophie.travelagent;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    DataSource dataSource;
    ArrayAdapter<Agent> adapter;

    //references to buttons and list view on the layout
    Button btn_new;
    ListView lv_agents;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dataSource = new DataSource(this);
        btn_new = findViewById(R.id.btn_new);
        lv_agents = findViewById(R.id.lv_agents);

        // button listener
        btn_new.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, DetailActivity.class);
            intent.putExtra("mode", "insert");
            startActivity(intent);
        });

        lv_agents.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), DetailActivity.class);
                intent.putExtra("mode", "update");
                intent.putExtra("agent", adapter.getItem(position));
                startActivity(intent);

            }
        });



    loadData();

    }

    @Override
    protected void onStart() {
        super.onStart();
        loadData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadData();
    }

    private void loadData() {
        adapter = new ArrayAdapter<>(this,
                    android.R.layout.simple_list_item_1, dataSource.getAllAgent());
        lv_agents.setAdapter(adapter);
    }

}