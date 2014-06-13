package de.hawhamburg.sea2.echarge;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.HashMap;

import de.hawhamburg.sea2.echarge.library.DatabaseHandler;


public class InfoActivity extends Activity {

    private TextView tvFNAME;
    private TextView tvLASTNAME;
    private TextView tvBIRTHDAY;
    private TextView tvSTREET;
    private TextView tvNUMBER;
    private TextView tvPLZ;
    private TextView tvCITY;
    private TextView tvCOUNTRY;
    private TextView tvEMAIL;
    private TextView tvMOBILE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        tvFNAME = (TextView) findViewById(R.id.viewVorname);
        tvLASTNAME = (TextView) findViewById(R.id.viewNachname);
        tvBIRTHDAY = (TextView) findViewById(R.id.viewGebDatum);
        tvSTREET = (TextView) findViewById(R.id.viewStrasse);
        tvNUMBER = (TextView) findViewById(R.id.viewHausnummer);
        tvPLZ = (TextView) findViewById(R.id.viewPLZ);
        tvCITY = (TextView) findViewById(R.id.viewStadt);
        tvCOUNTRY = (TextView) findViewById(R.id.viewLand);
        tvEMAIL = (TextView) findViewById(R.id.viewEmail);
        tvMOBILE = (TextView) findViewById(R.id.viewHandynummer);

        DatabaseHandler db = new DatabaseHandler(getApplicationContext());
        HashMap hm = db.getUserDetails();

        tvFNAME.setText((String) hm.get("fname"));
        tvLASTNAME.setText((String) hm.get("lname"));
        tvBIRTHDAY.setText((String) hm.get("birthday"));
        tvSTREET.setText((String) hm.get("street"));
        tvNUMBER.setText((String) hm.get("number"));
        tvPLZ.setText((String) hm.get("plz"));
        tvCITY.setText((String) hm.get("city"));
        tvCOUNTRY.setText((String) hm.get("country"));
        tvEMAIL.setText((String) hm.get("email"));
        tvMOBILE.setText((String) hm.get("mobile"));
    }

/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.info, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
*/
}
