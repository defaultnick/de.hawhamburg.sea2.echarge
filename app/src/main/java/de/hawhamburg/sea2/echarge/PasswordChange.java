package de.hawhamburg.sea2.echarge;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import de.hawhamburg.sea2.echarge.library.DatabaseHandler;
import de.hawhamburg.sea2.echarge.library.UserFunctions;

public class PasswordChange extends ActionBarActivity {

    private static String KEY_SUCCESS = "success";
    private static String KEY_ERROR = "error";
    private static String netCheckUrl = "http://192.168.253.151/";

    EditText newpass;
    Button changepass;
    TextView reset_status_message;
    private ProgressDialog nDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_change);

        newpass = (EditText) findViewById(R.id.newpass);
        changepass = (Button) findViewById(R.id.change_pass_button);
        reset_status_message = (TextView) findViewById(R.id.reset_status_message);

        changepass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NetAsync();
            }
        });
    }

    private void ShowDialog(String Title, String Message, Boolean tr) {
        if(tr == true) {
            if(nDialog != null) nDialog.dismiss();

            nDialog = new ProgressDialog(PasswordChange.this);
            nDialog.setTitle(Title);
            nDialog.setMessage(Message);
            nDialog.setIndeterminate(false);
            nDialog.setCancelable(true);
            nDialog.show();
        } else {
            nDialog.setTitle(Title);
            nDialog.setMessage(Message);
        }

    }

    private void ShowDialog(Boolean show) {
        if(show == false) {
            nDialog.dismiss();
        }
    }

    private class NetCheck extends AsyncTask<String,String,Boolean> {
        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            ShowDialog("Internet","prüfe...",true);
        }

        @Override
        protected Boolean doInBackground(String... args){

            ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo netInfo = cm.getActiveNetworkInfo();
            if (netInfo != null && netInfo.isConnected()) {
                try {
                    URL url = new URL(netCheckUrl);
                    HttpURLConnection urlc = (HttpURLConnection) url.openConnection();
                    urlc.setConnectTimeout(3000);
                    urlc.connect();
                    if (urlc.getResponseCode() == 200) {
                        return true;
                    }
                } catch (MalformedURLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            return false;

        }
        @Override
        protected void onPostExecute(Boolean th){

            if(th == true){
                ShowDialog(false);
                new ProcessChange().execute();
            }
            else{
                ShowDialog(false);
                //   mLogin_status_message.setText(getString(R.string.error_network_access));
                reset_status_message.setText(getString(R.string.error_network_access));
                reset_status_message.setVisibility(1);
            }
        }
    }


    private class ProcessChange extends AsyncTask<String, String, JSONObject> {


        String newpas,email;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            DatabaseHandler db = new DatabaseHandler(getApplicationContext());
            HashMap<String,String> user = new HashMap<String, String>();
            user = db.getUserDetails();

            newpas = newpass.getText().toString();
            email = user.get("email");

            ShowDialog("kontaktiere Server", "hole Daten...", true);
        }

        @Override
        protected JSONObject doInBackground(String... args) {
            UserFunctions userFunction = new UserFunctions();
            JSONObject json = userFunction.chgPass(newpas,email);
            return json;
        }


        @Override
        protected void onPostExecute(JSONObject json) {
            /**
             * Checks if the Password Change Process is sucesss
             **/
            try {
                if (json.getString(KEY_SUCCESS) != null) {
                    reset_status_message.setText("");
                    String res = json.getString(KEY_SUCCESS);
                    String red = json.getString(KEY_ERROR);


                    if(Integer.parseInt(res) == 1){
                        ShowDialog(false);
                        reset_status_message.setText("Das Passwort wurde erfolgreich geändert");
                    }
                    else if (Integer.parseInt(red) == 2)
                    {    ShowDialog(false);
                        reset_status_message.setText("Das alte Passwort ist falsch!");
                    }
                    else {
                        ShowDialog(false);
                        reset_status_message.setText("Es ist ein Fehler aufgetreten!");
                    }
                    if( reset_status_message.getText().length()>0)
                        reset_status_message.setVisibility(1);
                }}
            catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public void NetAsync(){
        new NetCheck().execute();
    }

}
