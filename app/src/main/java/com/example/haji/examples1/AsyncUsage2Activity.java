package com.example.haji.examples1;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class AsyncUsage2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_usage2);

        //Back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
}
