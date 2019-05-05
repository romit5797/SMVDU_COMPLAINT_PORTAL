package com.smvdu.user.smvducomplaintportal;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TableRow.LayoutParams;
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

public class ItemThreeFragment2 extends Fragment {

    String strtext;
    String ServerURL = "http://learningphp1234.000webhostapp.com/android/changestatus.php" ;

    //int length;
    TableLayout stk;
    ScrollView scrollView;


    //String heroes5[] = new String[100];
    //String heroes4[] = new String[100];
    //String heroes3[] = new String[100];
  //  String heroes2[] = new String[100];
  //  String heroes[]= new String[100];
   // String[] count= {"1","2","3","4","5"};


    public static ItemThreeFragment2 newInstance() {
        ItemThreeFragment2 fragment = new ItemThreeFragment2();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_item_three2, container, false);

        stk = (TableLayout) view.findViewById(R.id.table);
        scrollView = (ScrollView) view.findViewById(R.id.scrollView1);
        Bundle b3 = getArguments();
        strtext = b3.getString("SearchValue");

        getJSON("http://learningphp1234.000webhostapp.com/android/retrieve2.php?Category="+strtext);

                return view;
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
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            heroes[i] = obj.getString("id");

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
            heroes6[i] = obj.getString("name");

        }

        init(length,heroes,heroes2,heroes3,heroes4,heroes5,heroes6);
    }

    public void init(int length,String[] heroes,final String[] heroes2,String[] heroes3,String[] heroes4,String[] heroes5,String[] heroes6) {


        TableRow tbrow0 = new TableRow(getActivity());

        TableLayout.LayoutParams tableRowParams= new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.MATCH_PARENT);
        int leftMargin=30;
        int topMargin=2;
        int rightMargin=2;
        int bottomMargin=2;

        tableRowParams.setMargins(leftMargin, topMargin, rightMargin, bottomMargin);
        tbrow0.setLayoutParams(tableRowParams);

        TextView tv0 = new TextView(getActivity());
        tv0.setText(" S.No ");
        tv0.setTextColor(Color.WHITE);
        tbrow0.addView(tv0);
        TextView tv1 = new TextView(getActivity());
        tv1.setText(" Category ");
        tv1.setTextColor(Color.WHITE);
        tbrow0.addView(tv1);
        TextView tv2 = new TextView(getActivity());
        tv2.setText(" Complaint No. ");
        tv2.setTextColor(Color.WHITE);
        tv2.setGravity(Gravity.CENTER);
        tbrow0.addView(tv2);
        TextView tv3 = new TextView(getActivity());
        tv3.setText(" Complaint ");
        tv3.setTextColor(Color.WHITE);
        tbrow0.addView(tv3);
        TextView tv4 = new TextView(getActivity());
        tv4.setText(" Current Status ");
        tv4.setTextColor(Color.WHITE);
        tv4.setGravity(Gravity.CENTER);
        tbrow0.addView(tv4);
        TextView tv5 = new TextView(getActivity());
        tv5.setText(" Time ");
        tv5.setTextColor(Color.WHITE);
        tv5.setGravity(Gravity.CENTER);
        tbrow0.addView(tv5);
        TextView tv6 = new TextView(getActivity());
        tv6.setText(" Remarks ");
        tv6.setTextColor(Color.WHITE);
        tv6.setGravity(Gravity.CENTER);
        tbrow0.addView(tv6);
        TextView tv7 = new TextView(getActivity());
        tv7.setText(" Options ");
        tv7.setTextColor(Color.WHITE);
        tv7.setGravity(Gravity.CENTER);
        tbrow0.addView(tv7);

        stk.addView(tbrow0);

        for (int i = 0; i <length; i++) {
            final String cno = heroes2[i];
            TableRow tbrow = new TableRow(getActivity());
            tbrow.setLayoutParams(tableRowParams);
            TextView t1v = new TextView(getActivity());
            t1v.setText(Integer.toString(i+1));
            t1v.setTextColor(Color.WHITE);
            t1v.setGravity(Gravity.CENTER);
            t1v.setMaxWidth(10);
            tbrow.addView(t1v);

            TextView t2v = new TextView(getActivity());
            t2v.setText(heroes[i]);
            t2v.setTextColor(Color.WHITE);
            t2v.setGravity(Gravity.CENTER);
            t2v.setMaxWidth(10);
            tbrow.addView(t2v);

            TextView t3v = new TextView(getActivity());
            t3v.setText(heroes2[i]);
            t3v.setTextColor(Color.WHITE);
            t3v.setGravity(Gravity.CENTER);
            t3v.setMaxWidth(10);
            tbrow.addView(t3v);

            TextView t4v = new TextView(getActivity());
            t4v.setText(heroes3[i]);
            t4v.setTextColor(Color.WHITE);
            t4v.setGravity(Gravity.CENTER);
            t4v.setMaxWidth(10);
            tbrow.addView(t4v);

            TextView t5v = new TextView(getActivity());
            t5v.setText(heroes4[i]);
            t5v.setTextColor(Color.WHITE);
            t5v.setGravity(Gravity.CENTER);
            t5v.setMaxWidth(10);
            tbrow.addView(t5v);


            TextView t6v = new TextView(getActivity());
            t6v.setText(heroes5[i]);
            t6v.setTextColor(Color.WHITE);
            t6v.setGravity(Gravity.CENTER);
            t6v.setMaxWidth(10);
            tbrow.addView(t6v);


            TextView t7v = new TextView(getActivity());
            t7v.setText(heroes6[i]);
            t7v.setTextColor(Color.WHITE);
            t7v.setGravity(Gravity.CENTER);
            t7v.setMaxWidth(10);
            tbrow.addView(t7v);
            Button btn = new Button(getActivity());
            btn.setText("UPDATE");
            btn.setId(i);
            btn.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    String TempCNO= cno;
                    Intent intent = new Intent(getActivity(), NinthActivity.class);
                    Bundle bundle = new Bundle();
//Add your data to bundle
                    bundle.putString("stuff",TempCNO);
                    intent.putExtras(bundle);
                    startActivity(intent);


                    //InsertData(TempCNO,TempStatus);
                }
            });
            tbrow.addView(btn);





            stk.addView(tbrow);


        }

        for (int i = 0; i <15; i++) {
            final String cno = heroes2[i];
            TableRow tbrow = new TableRow(getActivity());
            tbrow.setLayoutParams(tableRowParams);
            TextView t1v = new TextView(getActivity());
            t1v.setText(" ");
            t1v.setTextColor(Color.WHITE);
            t1v.setGravity(Gravity.CENTER);
            t1v.setMaxWidth(10);
            tbrow.addView(t1v);

            TextView t2v = new TextView(getActivity());
            t2v.setText(" ");
            t2v.setTextColor(Color.WHITE);
            t2v.setGravity(Gravity.CENTER);
            t2v.setMaxWidth(10);
            tbrow.addView(t2v);

            TextView t3v = new TextView(getActivity());
            t3v.setText(" ");
            t3v.setTextColor(Color.WHITE);
            t3v.setGravity(Gravity.CENTER);
            t3v.setMaxWidth(10);
            tbrow.addView(t3v);

            TextView t4v = new TextView(getActivity());
            t4v.setText(" ");
            t4v.setTextColor(Color.WHITE);
            t4v.setGravity(Gravity.CENTER);
            t4v.setMaxWidth(10);
            tbrow.addView(t4v);

            TextView t5v = new TextView(getActivity());
            t5v.setText(" ");
            t5v.setTextColor(Color.WHITE);
            t5v.setGravity(Gravity.CENTER);
            t5v.setMaxWidth(10);
            tbrow.addView(t5v);


            TextView t6v = new TextView(getActivity());
            t6v.setText(" ");
            t6v.setTextColor(Color.WHITE);
            t6v.setGravity(Gravity.CENTER);
            t6v.setMaxWidth(10);
            tbrow.addView(t6v);
            stk.addView(tbrow);
        }


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

                Toast.makeText(getActivity(), "Status updated!", Toast.LENGTH_LONG).show();

            }
        }

        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();

        sendPostReqAsyncTask.execute(c_no,status);
    }

    @Override
    public void onResume() {
        super.onResume();
        ((AppCompatActivity)getActivity()).getSupportActionBar().hide();
    }
    @Override
    public void onStop() {
        super.onStop();
        ((AppCompatActivity)getActivity()).getSupportActionBar().show();
    }


}
