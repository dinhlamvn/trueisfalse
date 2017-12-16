package com.example.administrator.trueisfalse;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import database.Values;

/**
 * Created by Administrator on 16/12/2017.
 */

public class SelectQuestionPackActivity extends AppCompatActivity {

    /*--- Declare component ---*/
    private Button btn20in120s;
    private Button btn30in210s;
    private Button btn45in420s;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_question_packs);

        initComponents();

        /* Create a new game play activity */
        final GamePlayActivity gamePlayActivity = new GamePlayActivity();


        btn20in120s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /* When player click button */
                gamePlayActivity.numOfQuestion = 20;
                gamePlayActivity.timeToAnswer = Values.TIME_20;
                gamePlayActivity.typePlay = 1;
                SelectQuestionPackActivity.this.finish();
                Intent intent = new Intent(SelectQuestionPackActivity.this, gamePlayActivity.getClass());
                startActivity(intent);

            }
        });

        btn30in210s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /* Same with button above, however change number of question and time */
                gamePlayActivity.numOfQuestion = 30;
                gamePlayActivity.timeToAnswer = Values.TIME_30;
                gamePlayActivity.typePlay = 1;
                SelectQuestionPackActivity.this.finish();
                Intent intent = new Intent(SelectQuestionPackActivity.this, gamePlayActivity.getClass());
                startActivity(intent);
            }
        });

        btn45in420s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /* Same with button above, however change number of question and time */
                gamePlayActivity.numOfQuestion = 45;
                gamePlayActivity.timeToAnswer = Values.TIME_45;
                gamePlayActivity.typePlay = 1;
                SelectQuestionPackActivity.this.finish();
                Intent intent = new Intent(SelectQuestionPackActivity.this, gamePlayActivity.getClass());
                startActivity(intent);
            }
        });


    }



    private void initComponents(){

        btn20in120s = (Button)findViewById(R.id.btn_20in120s);
        btn30in210s = (Button)findViewById(R.id.btn_30in210s);
        btn45in420s = (Button)findViewById(R.id.btn_45in420s);
    }

}
