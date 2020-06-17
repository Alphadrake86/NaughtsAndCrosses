package com.example.naughtsandcrosses;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TextView Playertext;


    private int[] gamestate;
    private ArrayList<Button> buttons;
    private int round;
    private boolean turnX;
    private boolean xstart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);

        Button btntl = findViewById(R.id.btnTL);
        Button btntc = findViewById(R.id.btnTC);
        Button btntr = findViewById(R.id.btnTR);
        Button btnml = findViewById(R.id.btnML);
        Button btnmc = findViewById(R.id.btnMC);
        Button btnmr = findViewById(R.id.btnMR);
        Button btnbl = findViewById(R.id.btnBL);
        Button btnbc = findViewById(R.id.btnBC);
        Button btnbr = findViewById(R.id.btnBR);

        Playertext = findViewById(R.id.txtPlayerTurn);

        gamestate = new int[9];
        buttons = new ArrayList<Button>();
        buttons.add(btntl); buttons.add(btntc); buttons.add(btntr);
        buttons.add(btnml); buttons.add(btnmc); buttons.add(btnmr);
        buttons.add(btnbl); buttons.add(btnbc); buttons.add(btnbr);
        round = 1;
        turnX = true;
        xstart =true;

        Playertext.setText("Player X's Turn");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.xfirst:
                xstart = item.isChecked();
                item.setChecked(xstart);
                return true;
            case R.id.about:
                Intent i = new Intent(this,MainActivity2.class);
                startActivity(i);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
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
        turnX = xstart;
        gamestate = new int[9];
        Playertext.setText("Player "+(turnX?"X":"O")+"'s Turn");
        for(Button b: buttons){
            b.setEnabled(true);
            b.setText(" ");
        }
    }

    private boolean checkGame(){
        if(round == 9 && !checkForWin()){
            Playertext.setText("Draw!");
            toggleAll();
            return false;
        }
        else if (round >= 3 && checkForWin()){
            toggleAll();
            Playertext.setText("Player " + (turnX? "X":"O") + " wins!");
            return false;
        }
        else {
            return true;
        }
    }

    private void toggleAll() {
        for (Button b: buttons) {
            b.setEnabled(false);
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
