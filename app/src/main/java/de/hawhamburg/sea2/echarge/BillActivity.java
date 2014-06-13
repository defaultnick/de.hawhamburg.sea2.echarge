package de.hawhamburg.sea2.echarge;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;


public class BillActivity extends Activity {


    private TextView tvTEST;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill);

        tvTEST = (TextView) findViewById(R.id.viewTEST);
        tvTEST.setText("Ich putze hier nur");






    }




}
