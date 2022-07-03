package com.example.capstoneprojectkassem.Other;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.renderscript.Sampler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.capstoneprojectkassem.R;
import com.squareup.picasso.Picasso;

public class Play extends AppCompatActivity implements View.OnClickListener{
    private static final String STATE_COUNTER = "counter";
    final static long INTERVAL = 3000; //1 sec
    final static long TIMEOUT = 12000; //7 sec
    int progressValue = 0;
    CountDownTimer mCountDown;
    int index=0,score=0,thisQuestion=0,totalQuestion,correctAnswer;
    ProgressBar progressBar;
    ImageView question_image;
    Button btnA,btnB,btnC,btnD;
    TextView txtScore,txtQuestionNum,question_text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playing);

        txtScore = findViewById(R.id.txtScore);
        txtQuestionNum = findViewById(R.id.txtTotalQuestion);
        question_text = findViewById(R.id.question_text);
        question_image = findViewById(R.id.question_image);

        progressBar = findViewById(R.id.progressBar);


        btnA = findViewById(R.id.btnAnswerA);
        btnB = findViewById(R.id.btnAnswerB);
        btnC = findViewById(R.id.btnAnswerC);
        btnD = findViewById(R.id.btnAnswerD);

        btnA.setOnClickListener(this);
        btnB.setOnClickListener(this);
        btnC.setOnClickListener(this);
        btnD.setOnClickListener(this);
      /*  if (savedInstanceState!=null) {
            savedInstanceState.getInt("score");
            savedInstanceState.getInt("correctAnswer");
            savedInstanceState.getInt("index");

        }*/
        if (savedInstanceState != null) {
            index = savedInstanceState.getInt(STATE_COUNTER, 0);
            score = savedInstanceState.getInt("score", 0);
            txtScore.setText(String.valueOf(score));
            thisQuestion = savedInstanceState.getInt("sss", 0);

            correctAnswer = savedInstanceState.getInt("allquestionss", 0);
            txtQuestionNum.setText(String.valueOf(totalQuestion));
        }
    }
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        thisQuestion-=1;
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
       //outState.putInt("score",score);
      // outState.putInt("correctAnswer",correctAnswer);
     //  outState.putInt("index",index);
       super.onSaveInstanceState(outState);
       // Save our own state now
       outState.putInt(STATE_COUNTER, index);
       outState.putInt("score", score);
       outState.putInt("sss", thisQuestion);
       outState.putInt("allquestionss", correctAnswer);
    }

    @Override
    public void onClick(View v) {
        mCountDown.cancel();
        if (index<totalQuestion)
        {
            Button clickedButton = (Button) v;
            if (clickedButton.getText().equals(Common.questionList.get(index).getCorrectAnswer()))
            {
                score+=10;
                correctAnswer++;
                showQuestion(++index);

            }
            else
            {
                Intent intent = new Intent(this, Done.class);
                Bundle dataSend = new Bundle();
                dataSend.putInt("SCORE",score);
                dataSend.putInt("TOTAL",totalQuestion);
                dataSend.putInt("CORRECT",correctAnswer);
                intent.putExtras(dataSend);
                startActivity(intent);
                finish();
            }
            txtScore.setText(String.format("%d",score));

        }
    }
    private void showQuestion(int index) {
        if (index<totalQuestion){
            thisQuestion++;
            txtQuestionNum.setText(String.format("%d / %d",thisQuestion,totalQuestion));
            progressBar.setProgress(0);
            progressValue=0;
            if (Common.questionList.get(index).getIsImageQuestion().equals("true")){
                Picasso.with(getBaseContext()).load(Common.questionList.get(index).getQuestion())
                        .into(question_image);
                question_image.setVisibility(View.VISIBLE);
                question_text.setVisibility(View.INVISIBLE);
            }
            else
            {
                question_text.setText(Common.questionList.get(index).getQuestion());
                question_text.setVisibility(View.VISIBLE);
                question_image.setVisibility(View.INVISIBLE);
            }
            btnA.setText(Common.questionList.get(index).getAnswerA());
            btnB.setText(Common.questionList.get(index).getAnswerB());
            btnC.setText(Common.questionList.get(index).getAnswerC());
            btnD.setText(Common.questionList.get(index).getAnswerD());

            mCountDown.start(); //start timer :D

        }
        else
        {
            Intent intent = new Intent(this,Done.class);
            Bundle dataSend = new Bundle();
            dataSend.putInt("SCORE",score);
            dataSend.putInt("TOTAL",totalQuestion);
            dataSend.putInt("CORRECT",correctAnswer);
            intent.putExtras(dataSend);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        totalQuestion = Common.questionList.size();
        mCountDown = new CountDownTimer(TIMEOUT,INTERVAL) {
            @Override
            public void onTick(long minisec) {
                progressBar.setProgress(progressValue);
                progressValue++;
            }

            @Override
            public void onFinish() {
                mCountDown.cancel();
                showQuestion(++index);
            }
        };
        showQuestion(index);
    }
}
