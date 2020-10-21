package com.sophie.travelagent;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;


public class DetailActivity extends AppCompatActivity {

    EditText et_id,et_firstName,et_middleInitial,et_lastName,et_busPhone,et_email;
    Button btn_save,btn_del;
    String mode;
    Agent agent;
    DataSource dataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        dataSource=new DataSource(this);

        et_id=findViewById(R.id.et_id);
        et_firstName=findViewById(R.id.et_firstName);
        et_middleInitial=findViewById(R.id.et_middleInitial);
        et_lastName=findViewById(R.id.et_lastName);
        et_busPhone=findViewById(R.id.et_busPhone);
        et_email=findViewById(R.id.et_email);

        btn_save=findViewById(R.id.btn_save);
        btn_del=findViewById(R.id.btn_del);

        Intent intent= getIntent();
        mode=intent.getStringExtra("mode");

        if(mode.equals("update")){
            agent = (Agent) intent.getSerializableExtra("agent");
            et_id.setText(String.valueOf(agent.getAgentId()));
            et_firstName.setText(agent.getAgtFirstName());
            et_middleInitial.setText(agent.getAgtMiddleInitial());
            et_lastName.setText(agent.getAgtLastName());
            et_busPhone.setText(agent.getAgtBusPhone());
            et_email.setText(agent.getAgtEmail());
            btn_del.setEnabled(true);
        }
        else
        {
            btn_del.setEnabled(false);

        }


        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mode.equals("update")) {
                    Agent agt = new Agent(Integer.parseInt(et_id.getText().toString()),
                            et_firstName.getText().toString(),
                            et_middleInitial.getText().toString(),
                            et_lastName.getText().toString(),
                            et_busPhone.getText().toString(),
                            et_email.getText().toString());
                    if (dataSource.updateAgent(agt) == -1) {
                        Log.d("Sophie", " Update failed!");
                    } else {
                        Log.d("Sophie", " Update success!");
                    }
                } else {
                    Agent agt = new Agent(0,
                            et_firstName.getText().toString(),
                            et_middleInitial.getText().toString(),
                            et_lastName.getText().toString(),
                            et_busPhone.getText().toString(),
                            et_email.getText().toString());
                    long result = dataSource.addAgent(agt);
                    Log.d("Sophie", " add result: " + result);
                    if (result == -1) {
                        Log.d("Sophie", " add record failed!");
                    } else {
                        Log.d("Sophie", " add record success!");
                    }
                }
            }
        });

        btn_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            if(dataSource.deleteAgent(Integer.parseInt(et_id.getText().toString()))==-1){
                 Log.d("Sophie"," record delete failed!");
            }
            else {
                Log.d("Sophie"," record delete success!");
            }
            }
        });
    }
}


