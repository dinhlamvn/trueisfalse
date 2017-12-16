package com.example.administrator.trueisfalse;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import database.Values;


public class MainActivity extends AppCompatActivity {


    /*--- Declare components ---*/
    private Button buttonTimeRacing;
    private Button buttonFarmGold;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        initComponent();

        buttonFarmGold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SelectQuestionPackActivity.class);
                MainActivity.this.finish();
                startActivity(intent);
            }
        });

        buttonTimeRacing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GamePlayActivity gp = new GamePlayActivity();
                gp.gameScore = 0;
                gp.numOfQuestion = Values.NUMBER_OF_QUESTION;
                gp.timeToAnswer = Values.TIME_1;
                gp.typePlay = 0;
                MainActivity.this.finish();
                Intent intent = new Intent(MainActivity.this, gp.getClass());
                startActivity(intent);
            }
        });



    }

    private void initComponent(){
        buttonTimeRacing = (Button)findViewById(R.id.btn_time_racing);
        buttonFarmGold = (Button)findViewById(R.id.btn_farm_gold);
    }

}
