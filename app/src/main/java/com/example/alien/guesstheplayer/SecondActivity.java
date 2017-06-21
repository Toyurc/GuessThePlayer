package com.example.alien.guesstheplayer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class SecondActivity extends AppCompatActivity {
    TextView textView;
    ImageView imageView;
    Button button1;
    Button button2;
    Button button3;
    Button button4;
    ArrayList<String> PlayersNames = new ArrayList<>();
    ArrayList<String> PlayersImgREs = new ArrayList<>();
    int chosenPlayer = 0;
    int points = 0;
    String score;
    int noOfQuestions = 0;
    int locationOfCorrectAnswer = 0;
    int incorrectAnswerLocation;
    ArrayList<String> answers = new ArrayList<>();
    String Name;
    String Gender;
    String Message;

        /*
        * method to generate questions
        * and also set answers to the butttons
        * */
    private void createNewQuestion() {
        Random random = new Random();
        String playerImage = PlayersImgREs.get(chosenPlayer);

        imageView.setImageResource(Integer.parseInt(playerImage));

        locationOfCorrectAnswer = random.nextInt(4);


        answers.clear();
        for (int i = 0; i < 4; i++) {

            if (i == locationOfCorrectAnswer) {

                answers.add(PlayersNames.get(chosenPlayer));

            } else {

                incorrectAnswerLocation = random.nextInt(PlayersImgREs.size());

                while (incorrectAnswerLocation == chosenPlayer) {

                    incorrectAnswerLocation = random.nextInt(PlayersImgREs.size());

                }

                answers.add(PlayersNames.get(incorrectAnswerLocation));
            }

        }

        button1.setText(answers.get(0));
        button2.setText(answers.get(1));
        button3.setText(answers.get(2));
        button4.setText(answers.get(3));

        chosenPlayer++;
    }


    /*
        * this is an onClick method for the buttons provided
        * each click of the right or wrong button generates a custom toast
        * showing the right answer if the user gets it wrong
        * */
    public void choosePlayer(View view) {

        if (view.getTag().toString().equals(Integer.toString(locationOfCorrectAnswer))) {

            LayoutInflater inflater = getLayoutInflater();
            View layout = inflater.inflate(R.layout.custom_toast,
                    (ViewGroup) findViewById(R.id.custom_toast_container));

            TextView text = (TextView) layout.findViewById(R.id.text);
            text.setText("Correct!!!");

            Toast toast = new Toast(getApplicationContext());
            toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.setView(layout);
            toast.show();

            points++;
            noOfQuestions++;

        } else {

            LayoutInflater inflater = getLayoutInflater();
            View layout = inflater.inflate(R.layout.custom_toast2,
                    (ViewGroup) findViewById(R.id.custom_toast_container));
            int rightPlayer = chosenPlayer - 1;
            TextView text = (TextView) layout.findViewById(R.id.text);
            text.setText("Wrong! It was " + PlayersNames.get(rightPlayer));

            Toast toast = new Toast(getApplicationContext());
            toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.setView(layout);
            toast.show();

            noOfQuestions++;
        }


        score = points + "/" + noOfQuestions;

        /*
        * condition to test if questions are upto 10 in order to terminate and show
        * the score of the game player*/
        if (noOfQuestions >= 10) {
            Intent intent = new Intent(this, LastActvitiy.class);
            intent.putExtra("score", score);
            intent.putExtra("name", Name);
            intent.putExtra("gender", Gender);
            intent.putExtra("message", Message);
            startActivity(intent);
            finish();
        } else {
            createNewQuestion();
        }


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        textView = (TextView) findViewById(R.id.textview);
        imageView = (ImageView) findViewById(R.id.image);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);

        /*
        * Stores resource id name for each player in an arraylist
        * */
        PlayersImgREs.add(String.valueOf(R.drawable.ibrahimovic));
        PlayersImgREs.add(String.valueOf(R.drawable.messi));
        PlayersImgREs.add(String.valueOf(R.drawable.ronaldo));
        PlayersImgREs.add(String.valueOf(R.drawable.beckham));
        PlayersImgREs.add(String.valueOf(R.drawable.drogba));
        PlayersImgREs.add(String.valueOf(R.drawable.henry));
        PlayersImgREs.add(String.valueOf(R.drawable.kaka));
        PlayersImgREs.add(String.valueOf(R.drawable.ozil));
        PlayersImgREs.add(String.valueOf(R.drawable.ronaldinho));
        PlayersImgREs.add(String.valueOf(R.drawable.sanchez));

        /*
        * Stores playerNames for each player in an arraylist
        * */
        PlayersNames.add("ibrahimovic");
        PlayersNames.add("messi");
        PlayersNames.add("ronaldo");
        PlayersNames.add("beckham");
        PlayersNames.add("drogba");
        PlayersNames.add("henry");
        PlayersNames.add("kaka");
        PlayersNames.add("ozil");
        PlayersNames.add("ronaldinho");
        PlayersNames.add("sanchez");

        /*
        * send intent message from mainActivity to lastActivity
        * also to recieve intent extras that weredisplayed in this activity
        * */
        Bundle recieveBundle = getIntent().getExtras();
        if (recieveBundle == null) {
            return;}
        else{

            String name = recieveBundle.getString("name");
            String gender = recieveBundle.getString("gender");
            String message = recieveBundle.getString("message");
            textView.setText(name);
            Gender = gender;
            Message = message;
            Name = name;
        }


        createNewQuestion();

    }

}
