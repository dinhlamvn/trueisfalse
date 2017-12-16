package com.example.administrator.trueisfalse;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Administrator on 13/12/2017.
 */

public class GameResultActivity extends AppCompatActivity {


    // Declare status result variable
    // Values: 0. Lose game 1. Win game
    public static int statusResult = 1;


    // DECLARE COMPONENT
    private TextView textViewResult;
    private Button buttonMenu;
    private Button buttonExit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_result);

        initComponent();

        if(this.statusResult == 0){
            textViewResult.setText("LOSE");
        }
        else textViewResult.setText("WIN");

        buttonExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GameResultActivity.this.finish();
                System.exit(0);
            }
        });

        buttonMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GameResultActivity.this.finish();
                Intent intent = new Intent(GameResultActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });


    }

    public void initComponent(){
        textViewResult = (TextView)findViewById(R.id.text_result);
        buttonMenu = (Button)findViewById(R.id.btn_go_menu);
        buttonExit = (Button)findViewById(R.id.btn_exit);
    }
}
