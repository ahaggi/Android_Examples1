package com.example.haji.examples1;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class TextValidationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_validation);
        EditText editText_valid_name = findViewById(R.id.editText_valid_name);
        EditText editText_valid_email = findViewById(R.id.editText_valid_email);
        EditText editText_valid_phone = findViewById(R.id.editText_valid_phone);
        EditText editText_valid_date = findViewById(R.id.editText_valid_date);


        Button btn_valid = findViewById(R.id.button_valid);
        btn_valid.setOnClickListener((view)->{
         });



/* OBS onFocusChange  ,,, hasFocus
* new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
            */
        editText_valid_name.setOnFocusChangeListener((View v, boolean hasFocus) ->{
                if(!hasFocus){
                    String text = editText_valid_name.getText().toString();
                    String feilmelding = "";
                    if (! text.matches(".{2,20}")) {
                        feilmelding =  "Fornavn må være 2-20 tegn.\n";
                    }
                    if (! text.matches("^[A-ZÆØÅ]+.*$")) {
                        feilmelding =  feilmelding+"Første tegn må være en stor bokstav.\n";
                    }
                    if (! text.matches("[a-zA-zæøåÆØÅ_ ]+")) {
                        feilmelding =  feilmelding+"Fornavn kan kun inneholde bokstaver, bindestrek og mellomrom.";
                    }
                    if (!feilmelding.equals("")){
                        editText_valid_name.setError(feilmelding);
                    }

                }

            }
        );


// on textChanged ,,, uses TextValidator classe se ned!
        editText_valid_email.addTextChangedListener(new TextValidator(editText_valid_email) {

            @Override
            public void validate(EditText editText, String text) {
                String feilmelding = "";
                if (! text.matches("^[\\w._%+-]+@[\\w.-]+\\.[a-zA-Z_]{2,63}$")) {
                    feilmelding =  "Tast inn gyldig E-post!";
                   // valid=false;
                }
                if (!feilmelding.equals("")){
                    editText.setError(feilmelding);
                }

            }
        });



        //Back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }








    public abstract class TextValidator implements TextWatcher {
        private final EditText editText;

        public TextValidator(EditText editText) {
            this.editText = editText;
        }

        public abstract void validate(EditText editText, String text);

        @Override
        final public void afterTextChanged(Editable s) {
            String text = editText.getText().toString();
            validate(editText, text);
        }

        @Override
        final public void beforeTextChanged(CharSequence s, int start, int count, int after) { /* Don't care */ }

        @Override
        final public void onTextChanged(CharSequence s, int start, int before, int count) { /* Don't care */ }
    }
}
