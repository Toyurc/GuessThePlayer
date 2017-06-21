package com.example.alien.guesstheplayer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {
    EditText theName;
    Boolean FootyKnowledge;
    String Gender;
    CheckBox football;
    String Message;
    String Name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        theName = (EditText) findViewById(R.id.nametf);
        football = (CheckBox) findViewById(R.id.footballKnowledge);
    }

    /*
    * onClick metthod, to start the game, after accepting
    * the name
    * gender
    * and know if user has football knowledge*/
    public void start(View view) {
        Name = theName.getText().toString();
        if (TextUtils.isEmpty(Name)) {
            theName.setError("Please Enter Your Name:");
        } else {
            RadioGroup types = (RadioGroup) findViewById(R.id.radiogroup);
            switch (types.getCheckedRadioButtonId()) {
                case R.id.male:
                    Gender = "Male";
                    break;
                case R.id.female:
                    Gender = "Female";
                    break;
            }
            FootyKnowledge = football.isChecked();
            if (FootyKnowledge) {
                Message = "with football knowledge";
            } else {
                Message = "without football knowledge";
            }
            startIntent();
        }
        // Log.i("here","is me the button");
    }

/*
* method to start intent*/
    public void startIntent() {
        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra("name", Name);
        intent.putExtra("gender", Gender);
        intent.putExtra("message", Message);
        startActivity(intent);
        finish();
    }


}