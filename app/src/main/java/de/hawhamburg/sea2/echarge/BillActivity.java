package de.hawhamburg.sea2.echarge;

import android.app.Activity;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;
import java.util.HashMap;


import de.hawhamburg.sea2.echarge.library.DatabaseHandler;


public class BillActivity extends Activity {


    private TextView tvRechnungsBetrag;
    private TextView tvLink;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill);

        DatabaseHandler db = new DatabaseHandler(getApplicationContext());
        HashMap hm = db.getUserDetails();

        String summe = (String) hm.get("gesamtsumme");
        String new_summe = summe.replace(".", ",");


        tvRechnungsBetrag = (TextView) findViewById(R.id.viewBetrag);
        tvRechnungsBetrag.setText(new_summe + " €");

        tvLink = (TextView) findViewById(R.id.viewClickableLink);
        tvLink.setMovementMethod(LinkMovementMethod.getInstance());



    }




}
