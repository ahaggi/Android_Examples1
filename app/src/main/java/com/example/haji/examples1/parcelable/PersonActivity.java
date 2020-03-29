package com.example.haji.examples1.parcelable;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.haji.examples1.R;

public class PersonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle noe = getIntent().getExtras();
        Person person = (Person) noe.getParcelable("person");

        TextView txt = findViewById(R.id.person_textView);
        txt.setText(person.getNavn());

        ImageView img = findViewById(R.id.person_imageView);
        img.setImageURI(person.getUri());

        //Back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);




    }
}
