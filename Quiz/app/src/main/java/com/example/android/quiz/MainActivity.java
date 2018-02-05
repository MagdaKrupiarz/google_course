package com.example.android.quiz;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final String HAVE_FUN = "have fun";
    public static final String LEGO_PROFESSIONALS = "14";
    public static final String MILLENIUM_FALCON = "75192: Millenium Falcon";
    public static final String MINIFIG_SERIES = "17";
    public static final String CORRECT_YEAR = "1974";
    private String message;
    private String playerName;
    int score= 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button result = (Button) findViewById(R.id.button_result);
        result.setClickable(true);
    }

    public void result(View view) {
        EditText text = (EditText) findViewById(R.id.player_name_field);
        String playerName = text.getText().toString();

        RadioGroup radioLegoMeaningGroup = (RadioGroup) findViewById(R.id.group_lego_meaning);
        score = checkRadioButton(HAVE_FUN, score, radioLegoMeaningGroup);

        RadioGroup radioLegoProfessionals = (RadioGroup) findViewById(R.id.group_lego_professionals);
        score = checkRadioButton(LEGO_PROFESSIONALS, score, radioLegoProfessionals);
        
        RadioGroup radioBiggestSet = (RadioGroup) findViewById(R.id.radio_biggest_set);
        score = checkRadioButton(MILLENIUM_FALCON, score, radioBiggestSet);
        
        RadioGroup radioSeriesMinifig =(RadioGroup)findViewById(R.id.radio_series_of_minifig);
        score = checkRadioButton(MINIFIG_SERIES, score, radioSeriesMinifig);
        
        RadioGroup radioFirstMinifig =(RadioGroup)findViewById(R.id.radio_first_minifig);
        score = checkRadioButton(CORRECT_YEAR, score, radioFirstMinifig);

        CheckBox legoTechnicDigger = (CheckBox) findViewById(R.id.checkBox2);
        score = checkCheckbox(score, legoTechnicDigger);
//        boolean TechnikCheckBox1 = legoTechnicDigger.isChecked();
//        if (TechnikCheckBox1){
//            score = score +1;
//        }
        CheckBox legoTechnicBrick = (CheckBox) findViewById(R.id.checkBox4);
        score = checkCheckbox(score, legoTechnicBrick);
        EditText TypeOfLego = (EditText) findViewById(R.id.type_of_lego);
        String TypeOfLegoEdited = TypeOfLego.getText().toString();
        if (TypeOfLegoEdited.matches("DUPLO")){
            score = score +1;
        }

        String resultMessage = createResultMessage(playerName, score);
        displayResultMessage( "You earned: " + String.valueOf(score) + " points" + "\n" + resultMessage);
        score = 0;
    }

    public void sendEmail(String[] addresses, String subject) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    public void displayResultMessage(String message ){
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, message, duration);
        toast.show();
    }

    private String createResultMessage(String playerName, int score){
        if (score >= 7){
            message = "Amazing, " + playerName + " you are realy lego freak :)";
        }
        else if (score >= 4 && score <7){
            message = "Good job " + playerName;
        }
        else if (score < 4){
            message = "Lego is not your strong side, " +playerName;
        }
        return message;
    }

//    public void sendEmail(View view) {
//        String resultMessage = createResultMessage(playerName, score);
//        displayResultMessage( "You earned: " + score + "\n" + resultMessage);
//        Intent intent = new Intent(Intent.ACTION_SENDTO);
//        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
//        intent.putExtra(Intent.EXTRA_SUBJECT, "Lego quiz results");
//        if (intent.resolveActivity(getPackageManager()) != null) {
//            startActivity(intent);
//        }
//
//    }

    private int checkRadioButton(String correctAnswear, int score, RadioGroup radioGroup){
        int selectedId = radioGroup.getCheckedRadioButtonId();
        if (selectedId != -1) {
            RadioButton radioButton = (RadioButton) findViewById(selectedId);
            if (radioButton.getText().equals(correctAnswear)) {
                score = score + 1;
            }
        }
        return score;
    }

    private int checkCheckbox(int score, CheckBox checkbox){
        boolean isChecked = checkbox.isChecked();
        if (isChecked){
            score = score +1;
        }
        return score;
    }
}
