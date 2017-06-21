package com.example.alien.guesstheplayer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.jar.Attributes;

public class LastActvitiy extends AppCompatActivity {
    TextView nametextView;
    TextView genderTextView;
    TextView footyknowledgeTextView;
    TextView scoretextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_last_actvitiy);
        nametextView = (TextView) findViewById(R.id.name);
        genderTextView = (TextView) findViewById(R.id.gender);
        footyknowledgeTextView = (TextView) findViewById(R.id.hasfootballknowledge);
        scoretextView = (TextView)findViewById(R.id.score);

        Bundle recievedBundle = getIntent().getExtras();
        if (recievedBundle == null) {
            return;}
        else{

            String name = recievedBundle.getString("name");
            String gender = recievedBundle.getString("gender");
            String message = recievedBundle.getString("message");
            String score = recievedBundle.getString("score");
            nametextView.setText(name);
            genderTextView.setText(gender);
            footyknowledgeTextView.setText(message);
            scoretextView.setText(score);
        }

    }
    public void restart (View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }



}
