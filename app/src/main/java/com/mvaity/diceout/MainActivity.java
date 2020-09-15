package com.mvaity.diceout;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView rollResult;
    Button rollButton;
    //Field to hold the score
    int score;
    Random rand;
    int die1;
    int die2;
    int die3;

    //Field to hold the score text
    TextView scoreText;

    //Arraylist to hold all three dice
    ArrayList<Integer> dice;

    ArrayList<ImageView> diceImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        //set initial score
        score = 0;
        rollResult = findViewById(R.id.rollResult);
        rollButton = findViewById(R.id.rollButton);
        scoreText = findViewById(R.id.scoreText);
        rand = new Random();

        //Create arraylist container for dice values
        dice = new ArrayList<>();

        ImageView die1Image = findViewById(R.id.die1Image);
        ImageView die2Image = findViewById(R.id.die2Image);
        ImageView die3Image = findViewById(R.id.die3Image);

        diceImageView = new ArrayList<>();
        diceImageView.add(die1Image);
        diceImageView.add(die2Image);
        diceImageView.add(die3Image);
        //Create greeting
        Toast.makeText(getApplicationContext(),"Welcome to DiceOut!",Toast.LENGTH_LONG).show();
    }

    @SuppressLint("SetTextI18n")
    public void rolldice(View v){
        rollResult.setText("Clicked!");
        die1 = rand.nextInt(6)+1;
        die2 = rand.nextInt(6)+1;
        die3 = rand.nextInt(6)+1;

        //set dice values into arraylist
        dice.clear();
        dice.add(die1);
        dice.add(die2);
        dice.add(die3);

        for(int dieOfSet = 0; dieOfSet < 3; dieOfSet++){
            String imageName = "die" + dice.get(dieOfSet) + ".png";
            try {
                InputStream stream = getAssets().open(imageName);
                Drawable d = Drawable.createFromStream(stream,null);
                diceImageView.get(dieOfSet).setImageDrawable(d);
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }


        String msg;

        if(die1 == die2 && die1 == die3 ){
            int scoreDelta = die1 * 100;
            msg = "You rolled a tripple " + die1 + "! You score " + scoreDelta + " points!";
            score += scoreDelta;
        }
        else if(die1 == die2 || die1 == die3 || die2 == die3){
            msg = "You rolled doubles for 50 points!";
            score += 50;
        }
        else{
            msg = "You didn't score this roll. Try again!";
        }

        rollResult.setText(msg);
        scoreText.setText("Score: "+score);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
