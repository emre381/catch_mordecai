package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    TextView scoreText;
    TextView timeText;
    ImageView imageView;
    ImageView imageView2;
    ImageView imageView3;
    ImageView imageView4;
    ImageView imageView5;
    ImageView imageView6;
    ImageView imageView7;
    ImageView imageView8;
    ImageView imageView9;
    ImageView[]  imageArray;
    Handler handler;
    Runnable runnable;

    int score;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //initialize etmek
        timeText = (TextView)  findViewById(R.id.timeText);

        scoreText = (TextView)  findViewById(R.id.scoreText);
        imageView= findViewById((R.id.imageView));
        imageView2= findViewById((R.id.imageView2));
        imageView3= findViewById((R.id.imageView3));
        imageView4= findViewById((R.id.imageView4));
        imageView5= findViewById((R.id.imageView5));
        imageView6= findViewById((R.id.imageView6));
        imageView7= findViewById((R.id.imageView7));
        imageView8= findViewById((R.id.imageView8));
        imageView9= findViewById((R.id.imageView9));
        imageArray = new ImageView[] {imageView, imageView2,imageView3,imageView4,imageView5,imageView6,imageView7,imageView8,imageView9};

        hideImages();
        score = 0;

        new CountDownTimer(10000, 1000){

            @SuppressLint("SetTextI18n")
            @Override
            public void onTick(long millisUntilFinished) {
                timeText.setText("Time : " + millisUntilFinished/1000);
            }

            @SuppressLint("SetTextI18n")
            @Override
            public void onFinish() {
                timeText.setText("Time Off");
                handler.removeCallbacks(runnable);
                for (ImageView image: imageArray){
                    image.setVisibility(View.INVISIBLE);
                }
                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                alert.setTitle("Restart?");
                alert.setMessage("Are you sure to restart the game?");
                alert.setPositiveButton("YES", (dialog, which) -> {
                    // Restart
                    Intent intent = getIntent();
                    finish();
                    startActivity(intent);
                });
                alert.setNegativeButton("No", (dialog, which) -> Toast.makeText(MainActivity.this, "GAME OVER", Toast.LENGTH_SHORT).show());
                alert.show();


            }
        }.start();

    }

    @SuppressLint("SetTextI18n")
    public void increaseScore(View view){

        score++;

        scoreText.setText("Score:" + score);




    }
    public void hideImages(){
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                for (ImageView image: imageArray){
                    image.setVisibility(View.INVISIBLE);
                }

                Random random =new Random();
                int i = random.nextInt(9);
                imageArray[i].setVisibility(View.VISIBLE);
                handler.postDelayed(this,500);

            }
        };
        handler.post(runnable);

    }
}