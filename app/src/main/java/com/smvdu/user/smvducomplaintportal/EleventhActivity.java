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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
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
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class EleventhActivity extends AppCompatActivity {
    String ServerURL = "http://learningphp1234.000webhostapp.com/android/otp.php";
    private Button Submit, Submit2;
    EditText temail, totp;
    TextView tx;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eleventh);


        temail = (EditText) findViewById(R.id.etEmail);
        totp = (EditText) findViewById(R.id.etOtp);
        tx= findViewById(R.id.txt);
        Submit = (Button) findViewById(R.id.btnSubmit);
        Submit2 = (Button) findViewById(R.id.btnSubmit2);



        tx.setVisibility(View.INVISIBLE);
        totp.setVisibility(View.INVISIBLE);
        Submit2.setVisibility(View.INVISIBLE);
        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tempemail = temail.getText().toString();
                InsertData(tempemail);
                Submit.setVisibility(View.INVISIBLE);
                tx.setVisibility(View.VISIBLE);
                totp.setVisibility(View.VISIBLE);
                Submit2.setVisibility(View.VISIBLE);
            }
        });



        Submit2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tempemail = temail.getText().toString();
                String tempotp = totp.getText().toString();
                String type = "login";

                BackgroundWorker3 backgroundWorker3 = new BackgroundWorker3(EleventhActivity.this);
                backgroundWorker3.execute(type, tempemail, tempotp);

            }
        });


    }

    public void InsertData(final String email) {

        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {
            @Override
            protected String doInBackground(String... params) {


                String EmailHolder = email;


                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();


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

                Toast.makeText(EleventhActivity.this, "OTP sent to your email", Toast.LENGTH_LONG).show();

            }
        }

        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();

        sendPostReqAsyncTask.execute(email);
    }

}