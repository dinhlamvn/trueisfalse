package database;

/**
 * Created by Administrator on 13/12/2017.
 */

public class Question {
    private int id;
    private String question;
    private String answer1;
    private String answer2;
    private String correctAnswer;
    private String urlImage;

    public Question(){
        this.id = -1;
        this.question = null;
        this.answer1 = null;
        this.answer2 = null;
        this.correctAnswer = null;
        this.urlImage = null;

    }

    public Question(int id, String question, String answer1, String answer2, String correctAnswer, String urlImage){
        this.id = id;
        this.question = question;
        this.answer1 = answer1;
        this.answer2 = answer2;
        this.correctAnswer = correctAnswer;
        this.urlImage = urlImage;
    }


    /* GETTING FUNCTION */
    public int getId(){
        return this.id;
    }

    public String getQuestion(){
        return this.question;
    }

    public String getAnswer1(){
        return this.answer1;
    }

    public String getAnswer2(){
        return this.answer2;
    }

    public String getCorrectAnswer(){
        return this.correctAnswer;
    }

    public String getUrlImage(){
        return this.urlImage;
    }

    /* SETTING FUNCTION */
    public void setId(int id){
        this.id = id;
    }

    public void setQuestion(String question){
        this.question = question;
    }

    public void setAnswer1(String answer1){
        this.answer1 = answer1;
    }

    public void setAnswer2(String answer2){
        this.answer2 = answer2;
    }

    public void setCorrectAnswer(String correctAnswer){
        this.correctAnswer = correctAnswer;
    }

    public void setUrlImage(String urlImage){
        this.urlImage = urlImage;
    }

}
