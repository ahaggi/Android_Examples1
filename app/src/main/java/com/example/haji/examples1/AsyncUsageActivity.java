package com.example.haji.examples1;

import android.content.Context;
import android.os.AsyncTask;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;

public class AsyncUsageActivity extends AppCompatActivity {

    protected TextView txtVw_percentField_AsyncUsage;

    protected Button btn_start_AsyncUsage;
    protected Button btn_cancel_AsyncUsage;

    protected ProgressBar progBar_percentField_AsyncUsage;
    protected InitTask _initTask;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_usage);
        txtVw_percentField_AsyncUsage = (TextView) findViewById(R.id.txtVw_percentField_AsyncUsage);
        progBar_percentField_AsyncUsage = findViewById(R.id.progBar_percentField_AsyncUsage);
        btn_start_AsyncUsage = (Button) findViewById(R.id.btn_start_AsyncUsage);
        btn_start_AsyncUsage.setOnClickListener(new StartButtonListener());
        btn_cancel_AsyncUsage = (Button) findViewById(R.id.btn_cancel_AsyncUsage);
        btn_cancel_AsyncUsage.setOnClickListener(new CancelButtonListener());

        //Back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

        protected class StartButtonListener implements View.OnClickListener {

        public void onClick(View v) {
            _initTask = new InitTask();
            _initTask.execute();
        }
    }
    protected class CancelButtonListener implements View.OnClickListener {

        public void onClick(View v) {
            _initTask.cancel(true);
        }
    }

    /**
     * sub-class of AsyncTask
     */
    protected class InitTask extends AsyncTask<Void, Integer, String> {

        // -- run intensive processes here
        // -- notice that the datatype of the first param in the class definition matches the param passed to this
        // method
        // -- and that the datatype of the last param in the class definition matches the return type of this method
        @Override
        protected String doInBackground(Void... params) {
            // -- on every iteration
            // -- runs a while loop that causes the thread to sleep for 50 milliseconds
            // -- publishes the progress - calls the onProgressUpdate handler defined below
            // -- and increments the counter variable i by one
            int i = 0;
            while (i <= 50) {
                try {
                    Thread.sleep(50);
                    publishProgress(i);
                    i++;
                }
                catch (Exception e) {
                    Log.i("makemachine", e.getMessage());
                }
            }
            return "COMPLETE!";
        }

        // -- gets called just before thread begins
        @Override
        protected void onPreExecute() {
            Log.i("makemachine", "onPreExecute()");
            txtVw_percentField_AsyncUsage.setTextColor(R.attr.editTextColor);
            btn_start_AsyncUsage.setVisibility(View.INVISIBLE);
            btn_cancel_AsyncUsage.setVisibility(View.VISIBLE);
            super.onPreExecute();
        }

        // -- called from the publish progress
        // -- notice that the datatype of the second param gets passed to this method
        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            Log.i("makemachine", "onProgressUpdate(): " + String.valueOf(values[0]));
            txtVw_percentField_AsyncUsage.setText((values[0] * 2) + "%");
            txtVw_percentField_AsyncUsage.setTextSize(values[0]);
            progBar_percentField_AsyncUsage.setProgress(values[0]*2);
        }

        // -- called if the cancel button is pressed
        @Override
        protected void onCancelled() {
            super.onCancelled();
            Log.i("makemachine", "onCancelled()");
            txtVw_percentField_AsyncUsage.setText("Cancelled!");
            txtVw_percentField_AsyncUsage.setTextColor(0xFFFF0000);
            btn_start_AsyncUsage.setVisibility(View.VISIBLE);
            btn_cancel_AsyncUsage.setVisibility(View.INVISIBLE);
        }

        // -- called as soon as doInBackground method completes
        // -- notice that the third param gets passed to this method
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            Log.i("makemachine", "onPostExecute(): " + result);
            txtVw_percentField_AsyncUsage.setText(result);
            txtVw_percentField_AsyncUsage.setTextColor(0xFF69adea);

            btn_start_AsyncUsage.setVisibility(View.VISIBLE);
            btn_cancel_AsyncUsage.setVisibility(View.INVISIBLE);
        }
    }
}
