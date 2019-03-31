package com.smvdu.user.smvducomplaintportal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class SixthActivity extends AppCompatActivity {
    EditText UsernameEt, PasswordEt;
    @Override
    public void onBackPressed(){

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sixth);
        UsernameEt = (EditText)findViewById(R.id.etUserName);
        PasswordEt = (EditText)findViewById(R.id.etPassword);

    }

    public void OnHome(View view) {

        Intent intent = new Intent(SixthActivity.this, MainActivity.class);
        startActivity(intent);
    }


    public void OnLogin(View view) {

        String username = UsernameEt.getText().toString();
        String password = PasswordEt.getText().toString();
        String type = "login";
        BackgroundWorker2 backgroundWorker2 = new BackgroundWorker2(this);
        backgroundWorker2.execute(type, username, password);


    }

}
