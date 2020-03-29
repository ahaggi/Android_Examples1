package com.example.haji.examples1;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;

import androidx.core.app.ActivityCompat;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static android.text.TextUtils.isEmpty;

public class TelOgSmsActivity extends AppCompatActivity {
    //TODO sjekk for gyldig innput
    EditText editTxt_telNr_telOgSms;
    EditText editTxt_smsNr_telOgSms;
    EditText editTxt_smsBody_telOgSms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tel_og_sms);
        editTxt_telNr_telOgSms = (EditText) findViewById(R.id.editTxt_telNr_telOgSms);
        editTxt_smsNr_telOgSms = (EditText) findViewById(R.id.editTxt_smsNr_telOgSms);
        editTxt_smsBody_telOgSms = (EditText) findViewById(R.id.editTxt_smsBody_telOgSms);


        Button button_tel1_telOgSms = findViewById(R.id.button_tel1_telOgSms);
        button_tel1_telOgSms.setOnClickListener(new Tel1());

        Button button_tel2_telOgSms = findViewById(R.id.button_tel2_telOgSms);
        button_tel2_telOgSms.setOnClickListener(new Tel2());

        Button button_sms1_telOgSms = findViewById(R.id.button_sms1_telOgSms);
        button_sms1_telOgSms.setOnClickListener(new Sms1());

        Button button_sms2_telOgSms = findViewById(R.id.button_sms2_telOgSms);
        button_sms2_telOgSms.setOnClickListener(new Sms2());

        //Back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    protected class Tel1 implements View.OnClickListener {

        public void onClick(View v) {
            String mobNr = "tel:" + editTxt_telNr_telOgSms.getText().toString();

            if (!(editTxt_telNr_telOgSms.getText().toString()).isEmpty()) {
                startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse(mobNr)));
            } else {
                Toast.makeText(TelOgSmsActivity.this, "Tast inn tel Nr!", Toast.LENGTH_LONG).show();
                editTxt_telNr_telOgSms.setError("Tast inn tel Nr!");
            }
        }
    }


    protected class Tel2 implements View.OnClickListener {

        public void onClick(View v) {
            String mobNr = "tel:" + editTxt_telNr_telOgSms.getText().toString();
            String [] tab = {Manifest.permission.CALL_PHONE };

            if (!(editTxt_telNr_telOgSms.getText().toString()).isEmpty()) {
                if (ActivityCompat.checkSelfPermission(TelOgSmsActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(TelOgSmsActivity.this, tab ,2);
                    return;
                }
                startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(mobNr)));
            }else {
                Toast.makeText(TelOgSmsActivity.this, "Tast inn tel Nr!", Toast.LENGTH_LONG).show();
                editTxt_telNr_telOgSms.setError("Tast inn tel Nr!");
            }
        }
    }


    protected class Sms1 implements View.OnClickListener {

        public void onClick(View v) {
            String mobNr =  editTxt_smsNr_telOgSms.getText().toString();
            String smsBody =  editTxt_smsBody_telOgSms.getText().toString();

            if ( mobNr.isEmpty()) {
                editTxt_smsNr_telOgSms.setError("Tast inn tel Nr!");
            }else if ( isEmpty(smsBody) ) {
                editTxt_smsBody_telOgSms.setError("Tast inn Sms melding!");
            } else {
                Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:" +mobNr));
                intent.putExtra("sms_body" , smsBody);
                startActivity(intent);
            }
        }
    }

    protected class Sms2 implements View.OnClickListener {

        public void onClick(View v) {

            //TODO implement ::  would like to send a message to +  this may cause charges
            String mobNr =  editTxt_smsNr_telOgSms.getText().toString();
            String smsBody =  editTxt_smsBody_telOgSms.getText().toString();
            String [] tab = {Manifest.permission.SEND_SMS};

            if ( mobNr.isEmpty()) {
                editTxt_smsNr_telOgSms.setError("Tast inn tel Nr!");
            }else if ( isEmpty(smsBody) ) {
                editTxt_smsBody_telOgSms.setError("Tast inn Sms melding!");
            } else {



                if (ActivityCompat.checkSelfPermission(TelOgSmsActivity.this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(TelOgSmsActivity.this, tab ,2);
                    return;
                }

                //Vi bruker ikke noen intent/"startActivity", mobNr blir ikke gjort om til en URI
                final SmsManager sms = SmsManager.getDefault();
                editTxt_smsNr_telOgSms.setText("");
                editTxt_smsBody_telOgSms.setText("");
                sms.sendTextMessage(mobNr , null, smsBody, null, null);
                Toast.makeText(TelOgSmsActivity.this, "Melding er sendt!" , Toast.LENGTH_LONG).show();
            }
        }
    }



}
