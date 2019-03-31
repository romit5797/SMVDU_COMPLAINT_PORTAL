package com.smvdu.user.smvducomplaintportal;


        import android.content.Intent;
        import android.graphics.Bitmap;
        import android.graphics.BitmapFactory;
        import android.graphics.Color;
        import android.support.annotation.NonNull;
        import android.support.design.widget.BottomNavigationView;
        import android.support.v4.app.Fragment;
        import android.support.v4.app.FragmentManager;
        import android.support.v4.app.FragmentTransaction;
        import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
        import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
        import android.support.v7.app.ActionBar;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.support.v7.widget.Toolbar;
        import android.view.Menu;
        import android.view.MenuInflater;
        import android.view.MenuItem;
        import android.view.View;
        import android.widget.Button;
        import android.widget.ImageView;
        import android.widget.Toast;

        import com.google.firebase.auth.FirebaseAuth;

public class SecondActivity extends AppCompatActivity {
    String text;
    @Override
    public void onBackPressed(){

    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        //setting the title
        toolbar.setTitle("SMVDU COMPLAINT PORTAL");
        toolbar.setTitleTextColor(Color.WHITE);

        //placing toolbar in place of actionbar
        setSupportActionBar(toolbar);

        Bundle bundle = getIntent().getExtras();
        text= bundle.getString("stuff");


        BottomNavigationView bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener
                (new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                        Fragment selectedFragment = null;
                        switch (item.getItemId()) {
                            case R.id.action_recents:
                                selectedFragment = ItemOneFragment.newInstance();
                                break;
                            case R.id.action_favorites:
                                selectedFragment = ItemTwoFragment.newInstance();
                                break;
                            case R.id.action_nearby:
                                selectedFragment = ItemThreeFragment.newInstance();
                                break;
                        }
                        Bundle connBundle = new Bundle();
                        connBundle.putString("SearchValue", text);
                        selectedFragment.setArguments(connBundle);
                        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.frame_layout, selectedFragment);
                        transaction.commit();
                        return true;
                    }
                });

        //Manually displaying the first fragment - one time only
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout, ItemOneFragment.newInstance());
        transaction.commit();

        //Used to select an item programmatically
        //bottomNavigationView.getMenu().getItem(2).setChecked(true);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.nav_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()){
            case R.id.menuAbout:
                Toast.makeText(this, "Requesting..", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(SecondActivity.this, ThirdActivity.class);
                Bundle bundle = new Bundle();
//Add your data to bundle
                bundle.putString("stuff",text);

                intent.putExtras(bundle);
                startActivity(intent);
                break;

            case R.id.menuContact:
                Toast.makeText(this, "Requesting..", Toast.LENGTH_SHORT).show();
                intent = new Intent(SecondActivity.this, FifthActivity.class);
                startActivity(intent);
                break;

            case R.id.menuSettings:
                Toast.makeText(this, "Admin login loading..", Toast.LENGTH_SHORT).show();
                 intent = new Intent(SecondActivity.this, SixthActivity.class);
                startActivity(intent);
                break;

            case R.id.menuLogout:
                Toast.makeText(this, "Logging out..", Toast.LENGTH_SHORT).show();
                intent = new Intent(SecondActivity.this, MainActivity.class);
                startActivity(intent);
                break;

        }
        return true;
    }
}