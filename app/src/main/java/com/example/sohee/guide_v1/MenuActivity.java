package com.example.sohee.guide_v1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);


    }

    public void OnRegistmenubtnClicked(View v){
        Intent registmenubtnIntent = new Intent(getApplicationContext(),RegisterActivity.class);
        startActivity(registmenubtnIntent);
    }

    public void OnCheckbtnClicked(View v){
        Intent checkbtnIntent = new Intent(getApplicationContext(),CheckActivity.class);
        startActivity(checkbtnIntent);
    }


    public void   OnMsgmenubtnClicked(View v){
        Intent msgbtnIntent = new Intent(getApplicationContext(),MessageActivity.class);
        startActivity(msgbtnIntent);
    }


}
