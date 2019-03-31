package com.smvdu.user.smvducomplaintportal;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class FifthActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fifth);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("SMVDU COMPLAINT PORTAL");
        toolbar.setTitleTextColor(Color.WHITE);

        //placing toolbar in place of actionbar
        setSupportActionBar(toolbar);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.nav2_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()){

            case R.id.menuContact:
                Toast.makeText(this, "Already in contact activity", Toast.LENGTH_SHORT).show();
                break;

            case R.id.menuSettings:
                Toast.makeText(this, "Admin login loading..", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(FifthActivity.this, SixthActivity.class);
                startActivity(intent);
                break;

            case R.id.menuLogout:
                Toast.makeText(this, "Logging out..", Toast.LENGTH_SHORT).show();
                intent = new Intent(FifthActivity.this, MainActivity.class);
                startActivity(intent);
                break;

        }
        return true;
    }

}
