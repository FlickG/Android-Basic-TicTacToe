package com.example.tictactoe;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GameLogic {
    private final int [][] gameBoard;
    private int[] winType = {-1, -1, -1};
    private  int player = 1;
    private  Button playAgainBTN;
    private  Button homeButton;
    private TextView playerTurn;
    private String[] playerNames = {};

    GameLogic(){
        gameBoard = new int[3][3];
        for (int r = 0; r < 3; r++ ){
            for (int c = 0; c < 3; c++ ){
                gameBoard[r][c] = 0;

            }
        }
    }
    // CHECK WHO'S PLAYER TURN IS
    public boolean updateGameBoard(int row, int col){
        //An X will be a 1
        //An O will be a 2
        //Player will be a 1 or 2. if 1 --> X. if 2 --> O
        if (gameBoard[row-1][col-1] == 0){
            gameBoard[row-1][col-1] = player;

            if(player == 1){

                playerTurn.setText((playerNames[1] + "'s Turn"));
            }
            else{
                playerTurn.setText((playerNames[0] + "'s Turn"));
            }
            return true;
        }
        else {
            return  false;
        }
    }
    // CHECK THE RESULT AFTER EACH PLAYER CLICKS A TILE
    public  boolean winnerCheck(){
        boolean isWinner = false;
        //Horizontal check
        for (int r = 0; r<3; r++){
            //player 1 win check
            if (gameBoard[r][0] == gameBoard[r][1] && gameBoard[r][0] == gameBoard[r][2] &&
                    gameBoard[r][0] != 0){

                winType = new int[]{r, 0, 1};
                isWinner = true;
                break;
            }
        }
        //Vertical check
        for (int c=0; c<3; c++){
            //player 1 win check
            if (gameBoard[0][c] == gameBoard[1][c] && gameBoard[2][c] == gameBoard[0][c] &&
                    gameBoard[0][c] != 0){

                winType = new int[]{0, c, 2};
                isWinner = true;
                break;
            }
        }
        //negative diagonal check
        if (gameBoard[0][0] == gameBoard[1][1] && gameBoard[2][2] == gameBoard[0][0] &&
                gameBoard[0][0] != 0){

            winType = new int[]{0, 2, 3};
            isWinner = true;
        }
        //positive diagonal check
        if (gameBoard[2][0] == gameBoard[1][1] && gameBoard[0][2] == gameBoard[2][0] &&
                gameBoard[2][0] != 0){

            winType = new int[]{2, 2, 4};
            isWinner = true;
        }

        int boardFiilled = 0;

         for (int r = 0; r < 3; r++){
             for(int c = 0; c < 3; c++){
                 if(gameBoard[r][c] != 0){
                     boardFiilled += 1;
                 }
             }
         }

        if(isWinner){
            playAgainBTN.setVisibility(View.VISIBLE);
            homeButton.setVisibility(View.VISIBLE);
            playerTurn.setText((playerNames[player-1] + " Won!!!"));
            return true;
        }
        else if(boardFiilled == 9){
            playAgainBTN.setVisibility(View.VISIBLE);
            homeButton.setVisibility(View.VISIBLE);
            playerTurn.setText(("Tie Game!!!"));
            return  true;
        }
        else {
            return  false;
        }
    }
    public void setPlayAgainBTN(Button playAgainBTN){
        this.playAgainBTN = playAgainBTN;
    }
    public void setHomeButton(Button homeButton){
        this.homeButton = homeButton;
    }

    public void setPlayerTurn(TextView playerTurn) {
        this.playerTurn = playerTurn;
    }
    public int[] getWinType() {
        return winType;
    }
    public void setPlayerNames(String[] playerNames){
        this.playerNames = playerNames;
    }

    public int[][] getGameBoard() {
        return gameBoard;
    }
    public void setPlayer(int player){
        this.player =player;
    }
    public  int getPlayer(){
         return player;
    }
    // WHEN THE USER CLICK PLAY AGAIN BUTTON
    public void resetGame(){
        for (int r = 0; r < 3; r++){
            for (int c = 0; c < 3; c++){
                gameBoard[r][c] = 0;
            }
        }

        winType[0] = -1;
        winType[1] = -1;
        winType[2] = -1;
        player = 1;
        playAgainBTN.setVisibility(View.GONE);
        homeButton.setVisibility(View.GONE);
        playerTurn.setText((playerNames[0] + "'s turn"));
    }
}
