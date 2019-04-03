package com.smvdu.user.smvducomplaintportal;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
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

public class ItemTwoFragment extends Fragment {
    String ServerURL = "http://learningphp1234.000webhostapp.com/android/get_data.php" ;

    private Spinner spinner;
    private Spinner spinner1;
    String strtext="zxc";
    private EditText userName, userContact, userRoom,userBlock,userDetails;
    private Button Submit;

    String TempName, TempContact,TempRoom,TempBlock,TempCategory,TempHostel,TempEmail,TempDetails ;
    public static ItemTwoFragment newInstance() {
        ItemTwoFragment fragment = new ItemTwoFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_item_two, container, false);


        userName = (EditText) view.findViewById(R.id.etName);
        userContact = (EditText) view.findViewById(R.id.etContact);
        userRoom = (EditText) view.findViewById(R.id.etRoom);
        userBlock = (EditText) view.findViewById(R.id.etBlock);
        userDetails = (EditText) view.findViewById(R.id.etDetails);

        spinner = view.findViewById(R.id.spinner);

        List<String> categories = new ArrayList<>();
        categories.add(0, "Choose Category");
        categories.add("Electricity");
        categories.add("Internet");
        categories.add("Other");
        categories.add("Eraser");
        categories.add("Bag");
        categories.add("Pen");
        categories.add("Pencil");
        categories.add("Eraser");

        //Style and populate the spinner
        ArrayAdapter<String> dataAdapter;
        dataAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, categories);

        //Dropdown layout style
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (parent.getItemAtPosition(position).equals("Choose Category")) {
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


        spinner1 = view.findViewById(R.id.spinner1);

        List<String> Hostel = new ArrayList<>();
        Hostel.add(0, "Choose Hostel");
        Hostel.add("Niligiri");
        Hostel.add("Internet");
        Hostel.add("Pencil");
        Hostel.add("Eraser");


        //Style and populate the spinner
        ArrayAdapter<String> dataAdapter1;
        dataAdapter1 = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, Hostel);

        //Dropdown layout style
        dataAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //attaching data adapter to spinner
        spinner1.setAdapter(dataAdapter1);

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (parent.getItemAtPosition(position).equals("Choose Hostel")) {
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


        Submit = (Button) view.findViewById(R.id.btnSubmit);

        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //make your toast here

                GetData();

                InsertData(TempName, TempContact, TempRoom, TempBlock, TempHostel, TempCategory,TempEmail,TempDetails);
            }


        });

        return view;
    }

    public void GetData(){

        TempName = userName.getText().toString();
        TempBlock = userBlock.getText().toString();
        TempContact = userContact.getText().toString();
        TempRoom = userRoom.getText().toString();

       TempCategory = spinner.getSelectedItem().toString();
        TempHostel = spinner1.getSelectedItem().toString();
        TempEmail= strtext;
        TempDetails = userDetails.getText().toString();


    }

    public void InsertData(final String name,final String contact,final String room,final String block,final String hostel,final String category,final String email,final String details){

        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {
            @Override
            protected String doInBackground(String... params) {

                String NameHolder = name ;
                String ContactHolder = contact ;
                String RoomHolder = room ;
                String BlockHolder = block ;
                String HostelHolder = hostel ;
                String CategoryHolder = category ;
                String EmailHolder = email;
                String DetailsHolder = details;

                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

                nameValuePairs.add(new BasicNameValuePair("name", NameHolder));
                nameValuePairs.add(new BasicNameValuePair("contact", ContactHolder));
                nameValuePairs.add(new BasicNameValuePair("room", RoomHolder));
                nameValuePairs.add(new BasicNameValuePair("block", BlockHolder));
                nameValuePairs.add(new BasicNameValuePair("hostel", HostelHolder));
                nameValuePairs.add(new BasicNameValuePair("category", CategoryHolder));
                nameValuePairs.add(new BasicNameValuePair("email", EmailHolder));
                nameValuePairs.add(new BasicNameValuePair("details", DetailsHolder));

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

                Toast.makeText(getActivity(), "Data Submit Successfully", Toast.LENGTH_LONG).show();

            }
        }

        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();

        sendPostReqAsyncTask.execute(name,contact,room,block,hostel,category,email,details);
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