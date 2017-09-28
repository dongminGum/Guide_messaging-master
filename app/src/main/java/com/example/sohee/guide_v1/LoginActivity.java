package com.example.sohee.guide_v1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class LoginActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

    }

    public void OnLoginbtnClicked2(View v){
        Intent loginbtnIntent2 = new Intent(getApplicationContext(),MenuActivity.class);
        startActivity(loginbtnIntent2);
    }


}
