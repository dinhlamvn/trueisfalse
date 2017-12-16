package com.example.administrator.trueisfalse;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;

import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import database.Question;
import database.Values;


/**
 * Created by Administrator on 12/12/2017.
 */

public class GamePlayActivity extends AppCompatActivity {


    /*--- DECLARE COMPONENTS ---*/
    private TextView textViewQuestion; // Text view of question
    private TextView textViewTimer; // Text view to show time
    private TextView textViewGameScore; // Text view to show game score
    private TextView textViewTotalCorrect; // Text view to show how many question are correct

    private ImageView imageViewMain; // Image for each question

    private Button buttonAnswer1; // Answer A
    private Button buttonAnswer2; // Answer B
    private Button buttonChangeQuestion; // Change question

    /*--- END DECLARE COMPONENTS ---*/


    /*--- DECLARE VARIABLES ---*/
    private List<Question> questionList; // List of question get from server
    private int index; // Index of question in list
    private boolean[] questionState; // Array state of each question

    public static int timeToAnswer; // Time to answer finish list question
    public static int gameScore; // Game score
    public static int numOfQuestion; // Number of question to get from server
    public static int typePlay; // Type to play game
    /*--- END DECLARE VARIABLES ---*/


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_play);

        /* Call function to initialize component */
        initComponent();

        /* Create a list of question */
        questionList = new ArrayList<>();

        /* Create and execute  method to get data from server */
        new GetQuestion().execute(Values.SERVER_NAME + Values.DB_NAME + "/?e=" + numOfQuestion);

    }

    private void controlEvent1(){

        /* Create array to set status for once question in list */
        questionState = new boolean[questionList.size()];

        /* Initialize value, on start all question aren't answer */
        for(int i = 0; i < questionState.length; i++){
            questionState[i] = false;
        }

        /* Initialize index start in list */
        index = 0;

        /* Update interface */
        updateView(index);


        /* Create timer to count time */
        final CountDownTimer countDownTimer = countTime();
        /* Start count time */
        countDownTimer.start();

        buttonChangeQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /* When user click button 'Change Question ' in app */

                do{
                    /* Increase index by 1 */
                    index++;
                    /* If index equals size of question list, set index = 0, because can't increase index */
                    if(index == questionList.size()) index = 0;
                }while(questionState[index]); /*Loop when the question in [index] isn't answer by user */

                /* Update view in app */
                updateView(index);
            }
        });


        buttonAnswer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /* When user click button 'A' */

                /* Get value in button */
                String c = buttonAnswer1.getText().toString();

                /*Compare value get from button with correct */
                if(c.equals(questionList.get(index).getCorrectAnswer())){
                    /* If the answer is correct */

                    /* Set question state in index i is true */
                    questionState[index] = true;

                    /* Increase score */
                    gameScore += Values.GAME_SCORE_INCREASE;


                    /* Check status game */
                    if(checkWinGame()){
                        /* If all question in list have been answer */
                        /* Call function result game and set it win game state */
                        callGameResult(1);
                    }else{
                        /* If have any question haven't been answer */
                        /* Find this question */
                        do{
                            index++;
                            if(index == questionList.size()) index = 0;
                        }while(questionState[index]);

                        /* Update interface */
                        updateView(index);
                    }
                }
                else{
                    /* If the answer is incorrect */
                    /* Reset game play*/
                    resetWhenIncorrect();
                }
            }
        });


        buttonAnswer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Event button 2 as with button 1 */

                String c = buttonAnswer2.getText().toString();
                if(c.equals(questionList.get(index).getCorrectAnswer())){
                    questionState[index] = true;
                    gameScore += Values.GAME_SCORE_INCREASE;
                    if(checkWinGame()){
                        callGameResult(1);
                    }else{
                        do{
                            index++;
                            if(index == questionList.size()) index = 0;
                        }while(questionState[index]);
                        updateView(index);
                    }
                }
                else {
                    resetWhenIncorrect();
                }
            }
        });
    }


    private void controlEvent2(){
        /* Create array to set status for once question in list */
        questionState = new boolean[questionList.size()];

        /* Initialize value, on start all question aren't answer */
        for(int i = 0; i < questionState.length; i++){
            questionState[i] = false;
        }

        /* Initialize index start in list */
        index = 0;

        /* Update interface */
        updateView(index);


        /* Create timer to count time */
        final CountDownTimer countDownTimer = countTime();
        /* Start count time */
        countDownTimer.start();

        buttonChangeQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /* When user click button 'Change Question ' in app */

                do{
                    /* Increase index by 1 */
                    index++;
                    /* If index equals size of question list, set index = 0, because can't increase index */
                    if(index == questionList.size()) index = 0;
                }while(questionState[index]); /*Loop when the question in [index] isn't answer by user */

                /* Update view in app */
                updateView(index);
            }
        });


        buttonAnswer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /* When user click button 'A' */

                /* Get value in button */
                String c = buttonAnswer1.getText().toString();

                /*Compare value get from button with correct */
                if(c.equals(questionList.get(index).getCorrectAnswer())){
                    /* If the answer is correct */

                    /* Set question state in index i is true */
                    questionState[index] = true;

                    /* Increase score */
                    gameScore += Values.GAME_SCORE_INCREASE;

                    /* Check status game */
                    if(checkWinGame()){
                        /* If all question in list have been answer */
                        /* Call function result game and set it win game state */
                        callGameResult(1);
                    }else{
                        /* If have any question haven't been answer */
                        /* Find this question */
                        do{
                            index++;
                            if(index == questionList.size()) index = 0;
                        }while(questionState[index]);

                        /* Reset time */
                        countDownTimer.cancel();
                        countDownTimer.start();

                        /* Update interface */
                        updateView(index);
                    }
                }
                else{
                    /* If the answer is incorrect */
                    /* Call game over*/
                    callGameResult(0);
                }
            }
        });


        buttonAnswer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Event button 2 as with button 1 */

                String c = buttonAnswer2.getText().toString();
                if(c.equals(questionList.get(index).getCorrectAnswer())){
                    questionState[index] = true;
                    gameScore += Values.GAME_SCORE_INCREASE;
                    if(checkWinGame()){
                        callGameResult(1);
                    }else{
                        do{
                            index++;
                            if(index == questionList.size()) index = 0;
                        }while(questionState[index]);

                        countDownTimer.cancel();
                        countDownTimer.start();

                        updateView(index);
                    }
                }
                else {
                    callGameResult(0);
                }
            }
        });
    }


    private int getTotalCorrectAnswer(){
        /* The function will return total correct answer */
        /* Initialize variable to count correct answer */
        int result = 0;
        /* Loop in check answer array to count */
        for(boolean b : questionState){
            if(b) result++;
        }
        return result;
    }

    private void resetWhenIncorrect(){
        /* The function will reset game play when player answer the question incorrect */

        /* Reset array question were answer */
        for(int i = 0; i < questionState.length; i++){
            questionState[i] = false;
        }

        /* Reset index of question list */
        index = 0;

        /* Update interface */
        updateView(index);
    }

    private void callGameResult(int status){
        /* The function will call result activity */

        /* Create a new activity class */
        GameResultActivity gameResultActivity = new GameResultActivity();

        /* Set game status */
        gameResultActivity.statusResult = status;

        /* Create a intent with the class is define above */
        Intent intent = new Intent(GamePlayActivity.this, gameResultActivity.getClass());

        /* Call intent */
        this.finish();
        startActivity(intent);
    }

    private boolean checkWinGame(){
        for(int i = 0; i < questionState.length; i++){
            if(!questionState[i]) return false;
        }
        return true;
    }

    private CountDownTimer countTime(){
        CountDownTimer countDownTimer = new CountDownTimer(timeToAnswer, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                textViewTimer.setText(""+ millisUntilFinished / 1000);
            }

            @Override
            public void onFinish() {
                callGameResult(0);
            }
        };
        return countDownTimer;
    }

    private void updateView(int currentIndex){
        Question q = questionList.get(currentIndex);
        textViewQuestion.setText(q.getQuestion());
        buttonAnswer1.setText(q.getAnswer1());
        buttonAnswer2.setText(q.getAnswer2());
        textViewGameScore.setText(""+gameScore);
        if(typePlay == 1){
            textViewTotalCorrect.setText(getTotalCorrectAnswer() + "/" + numOfQuestion);
        }
        else if(typePlay == 0){
            textViewTotalCorrect.setText(""+getTotalCorrectAnswer());
        }
        new ImageDownLoadTask(q.getUrlImage(), imageViewMain).execute();
    }

    // Initialize component
    private void initComponent(){
        /* The function will initialize components in layout with our id
        *  After initialize, you could define event to component
        */

        // Initialize text view component
        textViewQuestion = (TextView)findViewById(R.id.text_question);
        textViewTimer = (TextView)findViewById(R.id.text_timer);
        textViewGameScore = (TextView)findViewById(R.id.text_gpoint);
        textViewTotalCorrect = (TextView)findViewById(R.id.text_total_correct);

        // Initialize image view component
        imageViewMain = (ImageView)findViewById(R.id.image_view);

        // Initialize button component
        buttonAnswer1 = (Button)findViewById(R.id.btn_answer1);
        buttonAnswer2 = (Button)findViewById(R.id.btn_answer2);
        buttonChangeQuestion = (Button)findViewById(R.id.btn_change_question);

    }

    /* Build class to get data from server */
    private class GetQuestion extends AsyncTask<String,Void,JSONArray> {

        // Create a process dialog
        private ProgressDialog progressDialog;


        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = new ProgressDialog(GamePlayActivity.this);

            // Set message to process dialog, user will see that
            progressDialog.setMessage("Please wait...");

            // Set cancel status for process, in case it can't be cancel
            progressDialog.setCancelable(false);

            // Show dialog to app
            progressDialog.show();

        }

        @Override
        protected JSONArray doInBackground(String... params) {

            /* The function will get data from server and return to a json array */

            // Create the connection
            HttpURLConnection connection = null;

            // Create the reader to read data
            BufferedReader reader = null;

            try {
                // Url of server
                URL url = new URL(params[0]);

                // Initialize connection to server
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();

                // Initialize reader
                InputStream inputStream = connection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(inputStream));

                // Create variables to save data from reader
                StringBuffer buffer = new StringBuffer();
                String line = null;

                // Reading data...
                while ((line = reader.readLine()) != null) {
                    buffer.append(line);
                }

                // Convert data to a json array and return it
                return new JSONArray(buffer.toString());

            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            } finally {
                /* Finish connect and read data */
                if (connection != null) {
                    // If app is connecting to server, destroy it
                    connection.disconnect();
                }

                try {
                    if (reader != null) {
                        // Close reader
                        reader.close();
                    }
                } catch (IOException e) {
                    // Print exception
                    e.printStackTrace();
                }

            }

            return null;
        }

        @Override
        protected void onPostExecute(JSONArray jsonArray) {
            /* After read data from server */
            super.onPostExecute(jsonArray);

            /* If the dialog is showing, close it */
            if(progressDialog.isShowing()){
                progressDialog.dismiss();
            }

            /* Create variable with values is length of json array read from server */
            int length = jsonArray.length();

            /* Set value of json array to list in class */
            for(int i = 0; i < length; i++){
                try{
                    /* Create json object with value of json array [i] */
                    JSONObject object = jsonArray.getJSONObject(i);
                    /* Create variables to get value from json object */
                    int id;
                    String a, b, c, d, e;

                    /* Set value to variables */
                    id = object.getInt("id");
                    a = object.getString("question");
                    b = object.getString("answer_1");
                    c = object.getString("answer_2");
                    d = object.getString("correct");
                    e = object.getString("image_url");

                    /* Create a new question object with value of list variable */
                    Question q = new Question(id, a, b, c, d, e);

                    /* Add new question to list question */
                    questionList.add(q);
                }catch(JSONException e){
                    e.printStackTrace();
                }
            }

            /* After list of question has initialized, call function control event in layout */
            if(typePlay == 1) {
                controlEvent1();
            }
            else if(typePlay == 0){
                controlEvent2();
            }
        }
    }

    private class ImageDownLoadTask extends AsyncTask<Void, Void, Bitmap>{

        // Url of image
        private String url;

        // ImageView to set image
        private ImageView imageView;

        public ImageDownLoadTask(String url, ImageView imageView){
            this.url = Values.SERVER_NAME + Values.DB_NAME + "/assets/" + url;
            this.imageView = imageView;
        }

        @Override
        protected Bitmap doInBackground(Void... params) {

            try{
                // Create url object
                URL url = new URL(this.url);

                // Open connection
                HttpURLConnection connection = (HttpURLConnection)url.openConnection();
                connection.setDoInput(true);
                connection.connect();

                // Read data from server
                InputStream inputStream = connection.getInputStream();

                // Convert data to bitmap
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);

                return bitmap;

            }catch(IOException e){
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            this.imageView.setImageBitmap(bitmap);
        }
    }
}
