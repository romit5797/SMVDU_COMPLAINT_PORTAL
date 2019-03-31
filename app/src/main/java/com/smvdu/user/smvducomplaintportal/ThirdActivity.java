
package com.smvdu.user.smvducomplaintportal;

        import android.graphics.Color;
        import android.os.AsyncTask;
        import android.support.design.widget.BottomNavigationView;
        import android.support.v4.app.Fragment;
        import android.support.v4.app.FragmentTransaction;
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
        import android.widget.Spinner;
        import android.widget.Toast;

        import java.io.IOException;
        import java.util.ArrayList;
        import java.util.List;
        import android.content.Intent;
        import android.support.annotation.NonNull;
        import android.widget.EditText;
        import android.content.Intent;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.text.TextUtils;
        import android.view.View;
        import android.widget.AdapterView;
        import android.widget.ArrayAdapter;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.ListView;
        import android.widget.Spinner;
        import android.widget.Toast;

        import com.google.firebase.database.DataSnapshot;
        import com.google.firebase.database.DatabaseError;
        import com.google.firebase.database.DatabaseReference;
        import com.google.firebase.database.FirebaseDatabase;
        import com.google.firebase.database.ValueEventListener;

        import org.apache.http.HttpEntity;
        import org.apache.http.HttpResponse;
        import org.apache.http.NameValuePair;
        import org.apache.http.client.ClientProtocolException;
        import org.apache.http.client.HttpClient;
        import org.apache.http.client.entity.UrlEncodedFormEntity;
        import org.apache.http.client.methods.HttpPost;
        import org.apache.http.impl.client.DefaultHttpClient;
        import org.apache.http.message.BasicNameValuePair;

        import java.util.ArrayList;
        import java.util.List;



public class ThirdActivity extends AppCompatActivity {
    String ServerURL = "http://learningphp1234.000webhostapp.com/android/changepass.php" ;
    private Button Submit;
    private EditText oldPassword, newPassword;
    String text;
    String TempOld,TempNew,TempEmail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third2);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        Bundle bundle = getIntent().getExtras();
        text= bundle.getString("stuff");
        toolbar.setTitle("SMVDU COMPLAINT PORTAL");
        toolbar.setTitleTextColor(Color.WHITE);

        //placing toolbar in place of actionbar
        setSupportActionBar(toolbar);

        oldPassword = (EditText) findViewById(R.id.etName);
        newPassword = (EditText) findViewById(R.id.etContact);

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
        TempEmail = text;
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

                Toast.makeText(ThirdActivity.this, "Password Changed Successfully", Toast.LENGTH_LONG).show();

            }
        }

        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();

        sendPostReqAsyncTask.execute(old,newp,email);
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
                Toast.makeText(this, "Requesting..", Toast.LENGTH_SHORT).show();
                 Intent intent = new Intent(ThirdActivity.this, FifthActivity.class);
                startActivity(intent);
                break;

            case R.id.menuSettings:
                Toast.makeText(this, "Admin login loading..", Toast.LENGTH_SHORT).show();
                intent = new Intent(ThirdActivity.this, SixthActivity.class);
                startActivity(intent);
                break;

            case R.id.menuLogout:
                Toast.makeText(this, "Logging out..", Toast.LENGTH_SHORT).show();
                intent = new Intent(ThirdActivity.this, MainActivity.class);
                startActivity(intent);
                break;

        }
        return true;
    }

}