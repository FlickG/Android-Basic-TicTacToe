package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class PlayerSetup extends AppCompatActivity {
    private EditText player1, player2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.player_setup);
        player1 = findViewById(R.id.editTextPlayerOne);
        player2 = findViewById(R.id.editTextPlayerTwo);
    }

    public void clickSubmit(View view) {

        if (TextUtils.isEmpty(player1.getText().toString())){
            Toast.makeText(this, "PLEASE ENTER PLAYER ONE'S NAME", Toast.LENGTH_SHORT).show();
        }else if(TextUtils.isEmpty((player2.getText().toString()))){
            Toast.makeText(this, "PLEASE ENTER PLAYER TWO'S NAMES", Toast.LENGTH_SHORT).show();
        }
        else{
            String player1Name = player1.getText().toString();
            String player2Name = player2.getText().toString();
            Intent intent = new Intent(this, GameDisplay.class);
            intent.putExtra("playerNames", new String[] {player1Name,player2Name});
            startActivity(intent);
        }
    }
}