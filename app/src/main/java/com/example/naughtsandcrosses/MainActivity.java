package com.example.naughtsandcrosses;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static android.content.pm.ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;

public class MainActivity extends AppCompatActivity {
    private Button btntc;
    private Button btntl;
    private Button btntr;

    private Button btnmc;
    private Button btnml;
    private Button btnmr;

    private Button btnbc;
    private Button btnbl;
    private Button btnbr;

    private TextView Playertext;
    private Button btnNG;

    private int[] gamestate;
    private ArrayList<Button> buttons;
    private int round;
    private boolean turnX;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(SCREEN_ORIENTATION_PORTRAIT);

        btntl = findViewById(R.id.btnTL);
        btntc = findViewById(R.id.btnTC);
        btntr = findViewById(R.id.btnTR);
        btnml = findViewById(R.id.btnML);
        btnmc = findViewById(R.id.btnMC);
        btnmr = findViewById(R.id.btnMR);
        btnbl = findViewById(R.id.btnBL);
        btnbc = findViewById(R.id.btnBC);
        btnbr = findViewById(R.id.btnBR);

        Playertext = findViewById(R.id.txtPlayerTurn);
        btnNG = findViewById(R.id.btnNewGame);

        gamestate = new int[9];
        buttons = new ArrayList<Button>();
        buttons.add(btntl); buttons.add(btntc); buttons.add(btntr);
        buttons.add(btnml); buttons.add(btnmc); buttons.add(btnmr);
        buttons.add(btnbl); buttons.add(btnbc); buttons.add(btnbr);
        round = 1;
        turnX = true;

        Playertext.setText("Player X's Turn");
    }

    public void btnClick(View v){
       Button V = (Button)v;
       int pos = buttons.indexOf(V);

       gamestate[pos] = turnX? 1 : 2;
       v.setEnabled(false);
       buttons.get(pos).setText(turnX?"X":"O");

       if(checkGame()){

           round++;
           turnX = !turnX;
           Playertext.setText("Player "+ (turnX?"X":"O") +"'s Turn");

       }
    }

    public void reset(View v){
        round = 1;
        turnX = true;
        gamestate = new int[9];
        Playertext.setText("Player X's Turn");
        for(Button b: buttons){
            b.setEnabled(true);
            b.setText(" ");
        }
    }

    private boolean checkGame(){
        if(round == 9 && !checkForWin()){
            Playertext.setText("Draw!");
            toggleAll(false);
            return false;
        }
        else if (round >= 3 && checkForWin()){
            toggleAll(false);
            Playertext.setText("Player " + (turnX? "X":"O") + " wins!");
            return false;
        }
        else {
            return true;
        }
    }

    private void toggleAll(boolean able) {
        for (Button b: buttons) {
            b.setEnabled(able);
        }
    }

    private boolean checkForWin(){
        return (gamestate[0] == gamestate[1] && gamestate[0] == gamestate[2] && gamestate[0]!=0)|| //vertical
                (gamestate[3] == gamestate[4] && gamestate[3] == gamestate[5] && gamestate[3]!=0)||
                (gamestate[6] == gamestate[7] && gamestate[6] == gamestate[8] && gamestate[6]!=0)||
                (gamestate[0] == gamestate[3] && gamestate[0] == gamestate[6] && gamestate[0]!=0)|| //horizontal
                (gamestate[1] == gamestate[4] && gamestate[1] == gamestate[7] && gamestate[1]!=0)||
                (gamestate[2] == gamestate[5] && gamestate[2] == gamestate[8] && gamestate[2]!=0)||
                (gamestate[0] == gamestate[4] && gamestate[0] == gamestate[8] && gamestate[0]!=0)|| //diagonal
                (gamestate[2] == gamestate[4] && gamestate[2] == gamestate[6] && gamestate[2]!=0);
    }

}
