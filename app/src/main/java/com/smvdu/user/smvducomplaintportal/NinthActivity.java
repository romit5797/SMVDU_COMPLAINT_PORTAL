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
import java.util.Arrays;
import java.util.List;

public class NinthActivity extends AppCompatActivity {
    String ServerURL = "http://learningphp1234.000webhostapp.com/android/changestatus.php" ;
    String ServerURL2 = "http://learningphp1234.000webhostapp.com/android/changeremarks.php" ;
    String ServerURL3 = "http://learningphp1234.000webhostapp.com/android/changeinventory.php" ;
    private Button Submit,Submit2,Submit3,Submit4;
    Spinner spinner,spinner2;
    String TempCNO,consumed,net;
    private EditText TRemarks,TQuantity;
    String[] resource = new String[200];
    int[] balance = new int[200];
    private TextView avail;
    int position=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ninth);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("SMVDU COMPLAINT PORTAL");
        toolbar.setTitleTextColor(Color.WHITE);

        //placing toolbar in place of actionbar
        setSupportActionBar(toolbar);

        Bundle bundle = getIntent().getExtras();
        TempCNO= bundle.getString("stuff");


        TRemarks = findViewById(R.id.etEmail);
        TQuantity = findViewById(R.id.etQuantity);
        avail= (TextView) findViewById(R.id.available);
        spinner = findViewById(R.id.spinner);
        spinner2 = findViewById(R.id.spinner2);
        resource[0]="..Select..";
        balance[0]=0;
        for(int i=1;i<200;i++)
        {resource[i]=" ";
         balance[i]=0;
        }


        List<String> categories = new ArrayList<>();
        categories.add(0, "..Select..");
        categories.add("Resolved");
        categories.add("Pending");

        //Style and populate the spinner
        ArrayAdapter<String> dataAdapter;
        dataAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, categories);

        //Dropdown layout style
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (parent.getItemAtPosition(position).equals("..Select..")) {
                    //do nothing
                } else {
                    //on selecting a spinner item
                    String item = parent.getItemAtPosition(position).toString();

                    //show selected spinner item
                    Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_SHORT).show();

                    //anything else you want to do on item selection do here
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

                // TODO Auto-generated method stub
            }
        });
//Style and populate the spinner
        ArrayAdapter<String> dataAdapter2;
        dataAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, resource);

        //Dropdown layout style
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //attaching data adapter to spinner

        spinner2.setAdapter(dataAdapter2);

        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (parent.getItemAtPosition(position).equals("..Select..")) {
                    //do nothing
                } else {
                    //on selecting a spinner item
                    String item = parent.getItemAtPosition(position).toString();

                    //show selected spinner item
                    Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_SHORT).show();

                    //anything else you want to do on item selection do here
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

                // TODO Auto-generated method stub
            }
        });


        getJSON("http://learningphp1234.000webhostapp.com/android/retrieve4.php");
        //attaching data adapter to spinner
        Submit = (Button) findViewById(R.id.btnSubmit);



        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String TempStatus = spinner.getSelectedItem().toString();
                InsertData(TempCNO,TempStatus);
            }
        });

        Submit2 = (Button) findViewById(R.id.btnSubmit2);



        Submit2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String TempStatus = spinner.getSelectedItem().toString();
                   String TempRemarks = TRemarks.getText().toString();
                InsertData2(TempCNO,TempRemarks);
            }
        });

        Submit3 = (Button) findViewById(R.id.btnSubmit3);
        Submit4 = (Button) findViewById(R.id.btnSubmit4);
        Submit3.setVisibility(View.VISIBLE);
        TQuantity.setVisibility(View.INVISIBLE);
        Submit4.setVisibility(View.INVISIBLE);
        avail.setVisibility(View.INVISIBLE);




        Submit3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Submit3.setVisibility(View.GONE);
                String selecteditem = spinner2.getSelectedItem().toString();
                for(int j=0;j<resource.length;j++)
                {
                    if(selecteditem==resource[j])
                    {
                        position=j;
                        break;
                    }
                };
                getJSON("http://learningphp1234.000webhostapp.com/android/retrieve4.php");
                TQuantity.setVisibility(View.VISIBLE);
                Submit4.setVisibility(View.VISIBLE);
                avail.setVisibility(View.VISIBLE);

            }
        });

        Submit4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                consumed = TQuantity.getText().toString();
              int net2=balance[position]-Integer.parseInt(consumed);
              net=Integer.toString(net2);
                String name = spinner2.getSelectedItem().toString();
               if(balance[position]>Integer.parseInt(consumed))
               {  Submit4.setVisibility(View.INVISIBLE);
                   InsertData3(net,consumed,name);}
               else{
                   Toast.makeText(NinthActivity.this, "Not enough resources available", Toast.LENGTH_LONG).show();
               }





            }
        });

    }

    private void getJSON(final String urlWebService) {

        class GetJSON extends AsyncTask<Void, Void, String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }


            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);

                try {
                    loadIntoListView(s);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            protected String doInBackground(Void... voids) {
                try {
                    URL url = new URL(urlWebService);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    StringBuilder sb = new StringBuilder();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    String json;
                    while ((json = bufferedReader.readLine()) != null) {
                        sb.append(json + "\n");
                    }
                    return sb.toString().trim();
                } catch (Exception e) {
                    return null;
                }
            }
        }
        GetJSON getJSON = new GetJSON();
        getJSON.execute();
    }

    private void loadIntoListView(String json) throws JSONException {
        //,,Complaint,Status,Time
        JSONArray jsonArray = new JSONArray(json);
        int length = jsonArray.length();
        String[] heroes = new String[jsonArray.length()];
        String[] heroes2 = new String[jsonArray.length()];
        String[] heroes3 = new String[jsonArray.length()];
        String[] heroes4 = new String[jsonArray.length()];
        String[] heroes5 = new String[jsonArray.length()];
        String[] heroes6 = new String[jsonArray.length()];
        String[] heroes7 = new String[jsonArray.length()];
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            heroes[i] = obj.getString("id");
            resource[i]=heroes[i];

        }

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            heroes2[i] = obj.getString("tid");

        }

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            heroes3[i] = obj.getString("sid");

        }

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            heroes4[i] = obj.getString("pid");

        }

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            heroes5[i] = obj.getString("aid");

        }

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            heroes6[i] = obj.getString("bid");
            balance[i]=Integer.parseInt(heroes6[i]);

        }


        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            heroes7[i] = obj.getString("name");

        }
        avail.setText("Available Resources:"+balance[position]);



    }





    public void InsertData(final String c_no,final String status){

        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {
            @Override
            protected String doInBackground(String... params) {

                String CNOHolder = c_no;
                String StatusHolder = status;


                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

                nameValuePairs.add(new BasicNameValuePair("c_no", CNOHolder));
                nameValuePairs.add(new BasicNameValuePair("status", StatusHolder));
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

                Toast.makeText(NinthActivity.this, "Status updated!", Toast.LENGTH_LONG).show();

            }
        }

        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();

        sendPostReqAsyncTask.execute(c_no,status);
    }

    public void InsertData2(final String c_no,final String remarks){

        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {
            @Override
            protected String doInBackground(String... params) {

                String CNOHolder = c_no;
                String RemarksHolder = remarks;


                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

                nameValuePairs.add(new BasicNameValuePair("c_no", CNOHolder));
                nameValuePairs.add(new BasicNameValuePair("remarks", RemarksHolder));
                try {
                    HttpClient httpClient = new DefaultHttpClient();

                    HttpPost httpPost = new HttpPost(ServerURL2);

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

                Toast.makeText(NinthActivity.this, "Remarks Added!", Toast.LENGTH_LONG).show();

            }
        }

        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();

        sendPostReqAsyncTask.execute(c_no,remarks);
    }

    public void InsertData3(final String net,final String consumed,final String name){

        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {
            @Override
            protected String doInBackground(String... params) {

                String BalanceHolder = net;
                String IssuedHolder = consumed;
                String NameHolder = name;


                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

                nameValuePairs.add(new BasicNameValuePair("net", BalanceHolder));
                nameValuePairs.add(new BasicNameValuePair("consumed", IssuedHolder));
                nameValuePairs.add(new BasicNameValuePair("name", NameHolder));
                try {
                    HttpClient httpClient = new DefaultHttpClient();

                    HttpPost httpPost = new HttpPost(ServerURL3);

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

                Toast.makeText(NinthActivity.this, "Inventory updated!", Toast.LENGTH_LONG).show();

            }
        }

        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();

        sendPostReqAsyncTask.execute(net,consumed,name);
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
                Intent intent = new Intent(NinthActivity.this, TenthActivity.class);
                startActivity(intent);
                break;

            case R.id.menuAbout:
                Toast.makeText(this, "Requesting..", Toast.LENGTH_SHORT).show();
                intent = new Intent(NinthActivity.this, EighthActivity.class);
                startActivity(intent);
                break;

            case R.id.menuLogout:
                Toast.makeText(this, "Logging out..", Toast.LENGTH_SHORT).show();
                intent = new Intent(NinthActivity.this, MainActivity.class);
                startActivity(intent);
                break;

        }
        return true;
    }}

