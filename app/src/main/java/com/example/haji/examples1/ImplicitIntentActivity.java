package com.example.haji.examples1;

import android.content.Intent;
import android.net.Uri;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;

public class ImplicitIntentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_implicit_intent);
        Button btn_load = (Button) findViewById(R.id.btn_load_ImplicitIntentActivity);
        btn_load.setOnClickListener((v)->{
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_OPEN_DOCUMENT);
            startActivityForResult(intent, 1); // 1=SELECT_PICTURE ,,, Den kan være et tilfeldlig tall, men må stemme med requestCode i onActivityResult callback
                                                                                            // action specifies the thing you want to do, such as view, edit, send, or get something
        });

        Button btn_chooser_shareWith = (Button) findViewById(R.id.btn_chooser_shareWith_ImplicitIntentActivity);
        btn_chooser_shareWith.setOnClickListener((v)->{
            Intent intent = new Intent();
            String path = ( (ImageView) findViewById(R.id.imgVw_ImplicitIntentActivity)).getTag()    + ""   ; // getImageView ==> getTAg returner en obj ==> toString
            intent.setData(Uri.parse(path));

            intent.setAction(Intent.ACTION_SEND);
            // Always use string resources for UI text.
            // This says something like "Share this photo with"
            String title = getResources().getString(R.string.chooser_title);
            // Create intent to show chooser
            Intent chooser = Intent.createChooser(intent, title);

            // Verify the intent will resolve to at least one activity
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(chooser);
            }

        });






        //Back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }


    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if (resultCode == RESULT_OK && requestCode == 1) { //1=SELECT_PICTURE
            ImageView imgVw = (ImageView) findViewById(R.id.imgVw_ImplicitIntentActivity);
            Uri uri = data.getData();
            imgVw.setImageURI(uri);
            imgVw.setTag("" + uri);
        }
    }
}
