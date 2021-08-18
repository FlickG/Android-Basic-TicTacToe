package com.example.tictactoe;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TicTacToe_Board extends View {
    private final int bColor;
    private final int xColor;
    private final int oColor;
    private final int diagonalColor;
    private boolean winningLine = false;
    private int cellSize = getWidth()/3;
    private final Paint paint = new Paint();
    private final GameLogic game;
    public TicTacToe_Board(Context context, AttributeSet attrs) {
        super(context, attrs);

        game = new GameLogic();
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.TicTacToe_Board, 0, 0 );

        try {
                bColor = a.getInteger(R.styleable.TicTacToe_Board_boardColor, 0 );
                xColor = a.getInteger(R.styleable.TicTacToe_Board_XColor, 0);
                oColor = a.getInteger(R.styleable.TicTacToe_Board_OColor, 0);
                diagonalColor = a.getInteger(R.styleable.TicTacToe_Board_winningLineColor, 0);
        }finally {
            a.recycle();
        }

    }

    @Override
    protected void onMeasure(int width, int height){
        super.onMeasure(width,height);

        int dimension = Math.min(getMeasuredWidth(), getMeasuredHeight());
        cellSize = dimension/3;

        setMeasuredDimension(dimension, dimension);
    }

    @Override
    protected void onDraw(Canvas canvas){
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);

        drawGameBoard(canvas);
        drawMarkers(canvas);
        if (winningLine){
            paint.setColor(diagonalColor);
            drawWinningLine(canvas);
        }
    }
    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event){
        float x = event.getX();
        float y = event.getY();

        int action = event.getAction();

        if(action == MotionEvent.ACTION_DOWN){
            int row =  (int)Math.ceil(y/cellSize);
            int col = (int)Math.ceil(x/cellSize);
            if(!winningLine){
                if(game.updateGameBoard(row,col)){
                    invalidate();

                    if(game.winnerCheck()){
                        winningLine = true;
                        invalidate();
                    }
                    if(game.getPlayer() % 2 == 0){
                        game.setPlayer(game.getPlayer()-1);
                    }
                    else{
                        game.setPlayer(game.getPlayer()+1);
                    }
                }
            }
            invalidate();
            return true;
        }
        return false;
    }
    private void drawGameBoard(Canvas canvas){
        paint.setColor(bColor);
        paint.setStrokeWidth(16);
        for (int c = 1; c<3; c++){
            canvas.drawLine(cellSize*c, 0, cellSize*c, canvas.getWidth(), paint);
        }
        for (int r = 1; r<3; r++){
            canvas.drawLine(0,cellSize*r , canvas.getWidth(),cellSize*r, paint);
        }

    }
    private  void drawMarkers(Canvas canvas){
        for (int r = 0; r < 3; r++ ){
            for (int c = 0; c < 3; c++ ){
                if(game.getGameBoard() [r][c] != 0){
                    if (game.getGameBoard() [r][c] == 1){
                        drawX(canvas, r, c);
                    }
                    else {
                        drawO(canvas, r, c);
                    }
                }
            }
        }
    }
    private void drawX(Canvas canvas, int row, int col){
        paint.setColor(xColor);

        canvas.drawLine((float)((col+1) * cellSize - cellSize*0.2),
                (float)(row*cellSize + cellSize*0.2),
                (float)(col*cellSize + cellSize*0.2),
                (float)((row+1)*cellSize - cellSize*0.2),
                paint) ;
        canvas.drawLine((float)(col * cellSize + cellSize*0.2),
                (float)(row*cellSize + cellSize*0.2),
                (float)((col+1)*cellSize - cellSize*0.2),
                (float)((row+1)*cellSize -  cellSize*0.2), paint);
    }
    private void drawO(Canvas canvas, int row, int col){
        paint.setColor(oColor);
        canvas.drawOval((float)(col*cellSize + cellSize*0.2), (float)(row*cellSize + cellSize*0.2),(float)((col*cellSize + cellSize) - cellSize*0.2), (float)((row*cellSize + cellSize) - cellSize*0.2), paint );
    }
    private void drawWinningLine(Canvas canvas){
        int row = game.getWinType()[0];
        int col = game.getWinType()[1];

        switch (game.getWinType()[2]){
            case 1:
                //draw Horizontal Line
                drawHorizontalLine(canvas, row, col);
                break;
            case 2:
                //draw Vertical Line
                drawVerticalLine(canvas, row, col);
                break;
            case 3:
                //draw Diagonal Line Neg
                drawDiagonalLineNeg(canvas);
                break;
            case 4:
                //draw Diagonal Line Pos
                drawDiagonalLinePos(canvas);
                break;
        }
    }

    private void drawHorizontalLine(Canvas canvas, int row, int col){
        canvas.drawLine(col, row*cellSize + (float)cellSize/2,
                cellSize*3, row*cellSize + (float)cellSize/2,
                paint);
    }



    private void drawVerticalLine(Canvas canvas, int row, int col){
        canvas.drawLine(col*cellSize + (float)cellSize/2, row,
                col*cellSize + (float)cellSize/2, cellSize*3,
                paint);
    }

    private void drawDiagonalLinePos(Canvas canvas){
        canvas.drawLine(0, cellSize*3, cellSize*3, 0, paint);
    }

    private void drawDiagonalLineNeg(Canvas canvas){
        canvas.drawLine(0, 0, cellSize*3, cellSize*3, paint);
    }
    public void setUpGame(Button playAgain, Button home, TextView playerDisplay, String[] names){
        game.setPlayAgainBTN(playAgain);
        game.setHomeButton(home);
        game.setPlayerTurn(playerDisplay);
        game.setPlayerNames(names);
    }
    public void resetGame(){
        game.resetGame();
        winningLine = false;
    }
}
