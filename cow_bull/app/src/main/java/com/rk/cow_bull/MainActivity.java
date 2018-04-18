package com.rk.cow_bull;

import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;

public class MainActivity extends AppCompatActivity {

    private TextView[][] arr = new TextView[15][3];
    private Button challenge;
    private Button try_b;
    private TextView turn;
    private EditText input;
    private HashSet<String> wordSet;
    private String word;
    private String mainWord;
    private int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        count=-1;

        arr[0][0] = (TextView) findViewById(R.id.TH1_1);
        arr[0][1] = (TextView) findViewById(R.id.TH1_2);
        arr[0][2] = (TextView) findViewById(R.id.TH1_3);
        arr[1][0] = (TextView) findViewById(R.id.TH2_1);
        arr[1][1] = (TextView) findViewById(R.id.TH2_2);
        arr[1][2] = (TextView) findViewById(R.id.TH2_3);
        arr[2][0] = (TextView) findViewById(R.id.TH3_1);
        arr[2][1] = (TextView) findViewById(R.id.TH3_2);
        arr[2][2] = (TextView) findViewById(R.id.TH3_3);
        arr[3][0] = (TextView) findViewById(R.id.TH4_1);
        arr[3][1] = (TextView) findViewById(R.id.TH4_2);
        arr[3][2] = (TextView) findViewById(R.id.TH4_3);
        arr[4][0] = (TextView) findViewById(R.id.TH5_1);
        arr[4][1] = (TextView) findViewById(R.id.TH5_2);
        arr[4][2] = (TextView) findViewById(R.id.TH5_3);
        arr[5][0] = (TextView) findViewById(R.id.TH6_1);
        arr[5][1] = (TextView) findViewById(R.id.TH6_2);
        arr[5][2] = (TextView) findViewById(R.id.TH6_3);
        arr[6][0] = (TextView) findViewById(R.id.TH7_1);
        arr[6][1] = (TextView) findViewById(R.id.TH7_2);
        arr[6][2] = (TextView) findViewById(R.id.TH7_3);
        arr[7][0] = (TextView) findViewById(R.id.TH8_1);
        arr[7][1] = (TextView) findViewById(R.id.TH8_2);
        arr[7][2] = (TextView) findViewById(R.id.TH8_3);
        arr[8][0] = (TextView) findViewById(R.id.TH9_1);
        arr[8][1] = (TextView) findViewById(R.id.TH9_2);
        arr[8][2] = (TextView) findViewById(R.id.TH9_3);
        arr[9][0] = (TextView) findViewById(R.id.TH10_1);
        arr[9][1] = (TextView) findViewById(R.id.TH10_2);
        arr[9][2] = (TextView) findViewById(R.id.TH10_3);
        arr[10][0] = (TextView) findViewById(R.id.TH11_1);
        arr[10][1] = (TextView) findViewById(R.id.TH11_2);
        arr[10][2] = (TextView) findViewById(R.id.TH11_3);
        arr[11][0] = (TextView) findViewById(R.id.TH12_1);
        arr[11][1] = (TextView) findViewById(R.id.TH12_2);
        arr[11][2] = (TextView) findViewById(R.id.TH12_3);
        arr[12][0] = (TextView) findViewById(R.id.TH13_1);
        arr[12][1] = (TextView) findViewById(R.id.TH13_2);
        arr[12][2] = (TextView) findViewById(R.id.TH13_3);
        arr[13][0] = (TextView) findViewById(R.id.TH14_1);
        arr[13][1] = (TextView) findViewById(R.id.TH14_2);
        arr[13][2] = (TextView) findViewById(R.id.TH14_3);
        arr[14][0] = (TextView) findViewById(R.id.TH15_1);
        arr[14][1] = (TextView) findViewById(R.id.TH15_2);
        arr[14][2] = (TextView) findViewById(R.id.TH15_3);

        input = (EditText) findViewById(R.id.input);
        turn = (TextView) findViewById(R.id.turn);

        challenge = (Button) findViewById(R.id.challenge);
        try_b = (Button) findViewById(R.id.try_b);

        turn.setText("Type a word");

        wordSet = new HashSet<>();

        AssetManager assetManager = getAssets();
        InputStream inputStream = null;
        try {
            inputStream = assetManager.open("words.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }

        BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        try {
            while((line = in.readLine()) != null) {
                String word = line.trim();
                wordSet.add(word);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onChallenge(View view){
        word = input.getText().toString();

        if(word.length()==4){
            if(isValid(word)){
                if(wordSet.contains(word)){
                    mainWord = word;
                    input.setText("");
                    turn.setText("Guess the word");
                    challenge.setVisibility(View.INVISIBLE);
                    try_b.setVisibility(View.VISIBLE);
                }
                else{
                    turn.setText("Please Enter a Valid Word");
                }
            }
            else{
                turn.setText("No Repeation Of Letters Allowed");
            }
        }
        else{
            turn.setText("Enter a 4 letter word");
        }
    }

    public boolean isValid(String str){

        for(int i=0;i<word.length();i++){
            for(int j=i+1;j<word.length();j++){
                if(String.valueOf(str.charAt(i)).equals(String.valueOf(str.charAt(j)))){
                    return false;
                }
            }
        }
        return true;
    }

    public void onTry(View view){

        String temp = input.getText().toString();
        int c=0,b=0;

        if(temp.length()==4){
            if(isValid(temp)){
                if(wordSet.contains(temp)){
                    if(count>14){
                        turn.setText("Out of trials and above was the original word");
                        try_b.setVisibility(View.INVISIBLE);
                        challenge.setVisibility(View.VISIBLE);
                        input.setText(mainWord);
                    }
                    else {
                        for (int i = 0; i < temp.length(); i++) {
                            for (int j = 0; j < temp.length(); j++) {
                                if (String.valueOf(temp.charAt(i)).equals(String.valueOf(mainWord.charAt(j)))) {
                                    if (i == j) {
                                        b++;
                                    } else {
                                        c++;
                                    }
                                }
                            }
                        }
                        count++;
                        if(count<=14) {
                            arr[count][0].setText(temp);
                            arr[count][1].setText(c + "");
                            arr[count][2].setText(b + "");

                            if (b == 4) {
                                turn.setText("You Guessed It Right!!!");
                            }
                        }
                        else{
                            turn.setText("Out of trials and above was the original word");
                            try_b.setVisibility(View.INVISIBLE);
                            challenge.setVisibility(View.VISIBLE);
                            input.setText(mainWord);
                        }
                    }
                }
                else{
                    turn.setText("Please Enter a Valid Word");
                }
            }
            else{
                turn.setText("No Repeation Of Letters Allowed");
            }
        }
        else{
            turn.setText("Enter a 4 letter word");
        }

    }

    public void onRestart(View view){
        for(int i=0; i<arr.length; i++){
            arr[i][0].setText("");
            arr[i][1].setText("");
            arr[i][2].setText("");
        }
        count=-1;
        turn.setText("Enter a word to challenge");
        input.setText("");
        try_b.setVisibility(View.INVISIBLE);
        challenge.setVisibility(View.VISIBLE);
    }
}