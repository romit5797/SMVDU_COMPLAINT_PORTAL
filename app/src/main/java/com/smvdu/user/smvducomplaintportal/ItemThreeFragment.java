package com.smvdu.user.smvducomplaintportal;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
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
import java.util.Arrays;
import java.util.List;

public class ItemThreeFragment extends Fragment {

    String strtext;
    //int length;
    TableLayout stk;
    ScrollView scrollView;

    //String heroes5[] = new String[100];
    //String heroes4[] = new String[100];
    //String heroes3[] = new String[100];
    //  String heroes2[] = new String[100];
    //  String heroes[]= new String[100];
    // String[] count= {"1","2","3","4","5"};


    public static ItemThreeFragment newInstance() {
        ItemThreeFragment fragment = new ItemThreeFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_item_three, container, false);
        Bundle b3 = getArguments();
        strtext = b3.getString("SearchValue");

        stk = (TableLayout) view.findViewById(R.id.table);
        scrollView = (ScrollView) view.findViewById(R.id.scrollView1);


        getJSON("http://learningphp1234.000webhostapp.com/android/retrieve.php?email="+strtext);

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
            heroes5[i] = obj.getString("name");

        }

        init(length,heroes,heroes2,heroes3,heroes4,heroes5);
    }

    public void init(int length,String[] heroes,final String[] heroes2,String[] heroes3,String[] heroes4,String[] heroes5) {

        TableRow tbrow0 = new TableRow(getActivity());

        TableLayout.LayoutParams tableRowParams= new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.MATCH_PARENT);
        int leftMargin=10;
        int topMargin=2;
        int rightMargin=10;
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
            stk.addView(tbrow);


        }
        for (int i = 0; i <15; i++) {
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