package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class GameDisplay extends AppCompatActivity {
    private TicTacToe_Board ticTacToe_board;
    private Button playAgainButton;
    private Button homeButton;
    private TextView playerTurn;
    private String[] playerNames;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_display);
        playAgainButton = findViewById(R.id.playAgainBTN);
        homeButton = findViewById(R.id.homeBTN);
        ticTacToe_board = findViewById(R.id.ticTacToe_Board);
        playerTurn = findViewById(R.id.playerDisplay);
        playerNames = getIntent().getStringArrayExtra("playerNames");
        if (playerNames != null){
            playerTurn.setText((playerNames[0] + "'s Turn"));
        }
        ticTacToe_board.setUpGame(playAgainButton,homeButton,playerTurn,playerNames);
        playAgainButton.setVisibility(View.GONE);
        homeButton.setVisibility(View.GONE);
    }

    public void buttonHome(View view) {
        Intent intent = new Intent(this,  MainActivity.class);
        startActivity(intent);
    }

    public void playAgain(View view) {
        ticTacToe_board. resetGame();
        ticTacToe_board.invalidate();
    }
}