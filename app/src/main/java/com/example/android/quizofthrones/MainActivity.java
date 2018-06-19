package com.example.android.quizofthrones;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the "Submit" button is pressed
     */
    public void submitAnswers(View v) {
        // Question1
        checkRadioGroupAnswer(R.id.answer_group_1, getString(R.string.answer1D));

        // Question 2
        int[] checkBoxIds2 = {R.id.checkbox2A, R.id.checkbox2B, R.id.checkbox2C, R.id.checkbox2D};
        boolean[] answerKey2 = {true, false, true, true};
        checkCheckBoxAnswers(checkBoxIds2, answerKey2);

        // Question 3
        checkEditTextAnswer(R.id.answer3, R.string.answer3);

        // Question 4
        checkRadioGroupAnswer(R.id.answer_group_2, getString(R.string.answer4B));

        // Question 5
        checkEditTextAnswer(R.id.answer5, R.string.answer5);

        // Question 6
        int[] checkBoxIds6 = {R.id.checkbox6A, R.id.checkbox6B, R.id.checkbox6C, R.id.checkbox6D};
        boolean[] answerKey6 = {true, false, false, true};
        checkCheckBoxAnswers(checkBoxIds6, answerKey6);

        // Process results
        String result = Integer.toString(score);
        if(score == 6) {
            result += getString(R.string.message1);
        } else if(score == 4 || score == 5) {
            result += getString(R.string.message2);
        } else {
            result += getString(R.string.message3);
        }
        Toast toast = Toast.makeText(this, result, Toast.LENGTH_LONG);
        toast.show();

        // Reset for next attempt
        score = 0;
    }

    /**
     * This method checks for the correct RadioButton in a RadioGroup
     */
    public void checkRadioGroupAnswer(int answerGroupId, String answer) {
        RadioGroup answerGroup = findViewById(answerGroupId);
        int checkedId = answerGroup.getCheckedRadioButtonId();
        if(checkedId == -1) {
            return;
        }
        RadioButton chosenRadioButton = findViewById(checkedId);
        String userAnswer = chosenRadioButton.getText().toString();
        if(userAnswer.equals(answer)) {
            score++;
        }
    }

    /**
     * This method ensures the proper CheckBoxes are checked based on an answer key
     */
    public void checkCheckBoxAnswers(int[] checkBoxIds, boolean[] answerKey) {
        CheckBox checkBoxA = findViewById(checkBoxIds[0]);
        boolean answerAChecked = checkBoxA.isChecked();
        CheckBox checkBoxB = findViewById(checkBoxIds[1]);
        boolean answerBChecked = checkBoxB.isChecked();
        CheckBox checkBoxC = findViewById(checkBoxIds[2]);
        boolean answerCChecked = checkBoxC.isChecked();
        CheckBox checkBoxD = findViewById(checkBoxIds[3]);
        boolean answerDChecked = checkBoxD.isChecked();
        if(answerAChecked == answerKey[0] && answerBChecked == answerKey[1] && answerCChecked == answerKey[2] && answerDChecked == answerKey[3]) {
            score++;
        }
    }

    /**
     * This method verifies the user's input text
     */
    public void checkEditTextAnswer(int editTextId, int answerId) {
        EditText editText = findViewById(editTextId);
        String answer = editText.getText().toString().toLowerCase().trim();
        if(answer.equals(getString(answerId))) {
            score++;
        }
    }
}