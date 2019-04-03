package com.smvdu.user.smvducomplaintportal;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ItemOneFragment2 extends Fragment {
    TextView textView,textView2,textView3,textView4,textView5,textView6,textView7,textView8,textView9,textView10,textView11,textView12,textView13,textView14,textView15,textView16;

    public static ItemOneFragment2 newInstance() {
        ItemOneFragment2 fragment = new ItemOneFragment2();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_one2, container, false);
        textView = (TextView) view.findViewById(R.id.textView15);
        textView2 = (TextView) view.findViewById(R.id.textView16);
        textView3 = (TextView) view.findViewById(R.id.textView17);
        textView4 = (TextView) view.findViewById(R.id.textView18);

        textView5 = (TextView) view.findViewById(R.id.textView19);
        textView6 = (TextView) view.findViewById(R.id.textView20);
        textView7 = (TextView) view.findViewById(R.id.textView21);

        textView8 = (TextView) view.findViewById(R.id.textView22);
        textView9 = (TextView) view.findViewById(R.id.textView23);
        textView10 = (TextView) view.findViewById(R.id.textView24);

        textView11 = (TextView) view.findViewById(R.id.textView26);
        textView12 = (TextView) view.findViewById(R.id.textView27);
        textView13 = (TextView) view.findViewById(R.id.textView28);

        textView14 = (TextView) view.findViewById(R.id.textView29);
        textView15 = (TextView) view.findViewById(R.id.textView30);
        textView16 = (TextView) view.findViewById(R.id.textView31);

        getJSON("http://learningphp1234.000webhostapp.com/android/count.php");

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

        String[] heroes = new String[jsonArray.length()];
        String[] heroes2 = new String[jsonArray.length()];
        String[] heroes3 = new String[jsonArray.length()];
        String[] heroes4 = new String[jsonArray.length()];
        String[] heroes5 = new String[jsonArray.length()];

        String[] heroes6 = new String[jsonArray.length()];
        String[] heroes7 = new String[jsonArray.length()];
        String[] heroes8 = new String[jsonArray.length()];
        String[] heroes9 = new String[jsonArray.length()];
        String[] heroes10 = new String[jsonArray.length()];

        String[] heroes11 = new String[jsonArray.length()];
        String[] heroes12 = new String[jsonArray.length()];
        String[] heroes13 = new String[jsonArray.length()];
        String[] heroes14 = new String[jsonArray.length()];
        String[] heroes15 = new String[jsonArray.length()];
        String[] heroes16 = new String[jsonArray.length()];

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
            heroes16[i] = obj.getString("name1");

        }



        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            heroes4[i] = obj.getString("name2");

        }

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            heroes5[i] = obj.getString("name3");

        }
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            heroes6[i] = obj.getString("name4");

        }
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            heroes7[i] = obj.getString("name5");

        }
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            heroes8[i] = obj.getString("name6");

        }

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            heroes9[i] = obj.getString("name7");

        }
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            heroes10[i] = obj.getString("name8");

        }
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            heroes11[i] = obj.getString("name9");

        }
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            heroes12[i] = obj.getString("name10");

        }

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            heroes13[i] = obj.getString("name11");

        }
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            heroes14[i] = obj.getString("name12");

        }
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            heroes15[i] = obj.getString("name");

        }

        textView.setText(heroes[0]);
        textView2.setText(heroes2[0]);
        textView3.setText(heroes3[0]);
        textView4.setText(heroes16[0]);

        textView5.setText(heroes4[0]);
        textView6.setText(heroes5[0]);
        textView7.setText(heroes6[0]);

        textView8.setText(heroes7[0]);
        textView9.setText(heroes8[0]);
        textView10.setText(heroes9[0]);

        textView11.setText(heroes10[0]);
        textView12.setText(heroes11[0]);
        textView13.setText(heroes12[0]);

        textView14.setText(heroes13[0]);
        textView15.setText(heroes14[0]);
        textView16.setText(heroes15[0]);


    }
}