package com.smvdu.user.smvducomplaintportal;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EighthActivity extends AppCompatActivity {
    String ServerURL = "http://learningphp1234.000webhostapp.com/android/changepass.php" ;
    private Button Submit;
    private EditText email,oldPassword, newPassword;
    String TempOld,TempNew,TempEmail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eighth);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("SMVDU COMPLAINT PORTAL");
        toolbar.setTitleTextColor(Color.WHITE);

        //placing toolbar in place of actionbar
        setSupportActionBar(toolbar);

        oldPassword = (EditText) findViewById(R.id.etName);
        newPassword = (EditText) findViewById(R.id.etContact);
        email = (EditText) findViewById(R.id.etEmail);

        Submit = (Button) findViewById(R.id.btnSubmit);



        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GetData();

                InsertData(TempOld,TempNew,TempEmail);
            }
        });

    }
    public void GetData(){

        TempOld = oldPassword.getText().toString();
        TempNew = newPassword.getText().toString();
        TempEmail = email.getText().toString();
    }

    public void InsertData(final String old,final String newp,final String email){

        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {
            @Override
            protected String doInBackground(String... params) {

                String OldHolder = old ;
                String NewHolder = newp;
                String EmailHolder = email;


                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

                nameValuePairs.add(new BasicNameValuePair("old", OldHolder));
                nameValuePairs.add(new BasicNameValuePair("newp", NewHolder));
                nameValuePairs.add(new BasicNameValuePair("email", EmailHolder));

                try {
                    HttpClient httpClient = new DefaultHttpClient();

                    HttpPost httpPost = new HttpPost(ServerURL);

                    httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

                    HttpResponse httpResponse = httpClient.execute(httpPost);

                    HttpEntity httpEntity = httpResponse.getEntity();


                } catch (ClientProtocolException e) {

                } catch (IOException e) {

                }
                return "Data Inserted Successfully";
            }

            @Override
            protected void onPostExecute(String result) {

                super.onPostExecute(result);

                Toast.makeText(EighthActivity.this, "Password Changed Successfully", Toast.LENGTH_LONG).show();

            }
        }

        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();

        sendPostReqAsyncTask.execute(old,newp,email);
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.nav_menu3, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()){

            case R.id.menuInventory:
                Toast.makeText(this, "Navigating..", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(EighthActivity.this, TenthActivity.class);
                startActivity(intent);
                break;

            case R.id.menuAbout:
                Toast.makeText(this, "Requesting..", Toast.LENGTH_SHORT).show();
                intent = new Intent(EighthActivity.this, EighthActivity.class);
                startActivity(intent);
                break;

            case R.id.menuLogout:
                Toast.makeText(this, "Logging out..", Toast.LENGTH_SHORT).show();
                intent = new Intent(EighthActivity.this, MainActivity.class);
                startActivity(intent);
                break;

        }
        return true;
    }}

