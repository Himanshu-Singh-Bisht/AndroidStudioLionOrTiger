package com.himanshu.lionortiger;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{
    enum Player
    {
        ONE , TWO , NO
    }

    Player currentPlayer = Player.ONE;
    Player[] playerChoices = new Player[9];
    int[][] winner = {{0 , 1 , 2} , {3 , 4 , 5} , {6 , 7 , 8},              // for rows
                      {0 , 3 , 6} , {1 , 4 , 7} , {2 , 5 , 8},              // for columns
                      {0 , 4 , 8} , {2 , 4 , 6}};                           // for diagonals



    private boolean gameOver = false;
    private Button btnReset;

    private GridLayout gridLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnReset = findViewById(R.id.btnReset);
        gridLayout = findViewById(R.id.gridLayout);

        for(int i = 0 ; i < playerChoices.length ; i++)
        {
            playerChoices[i] = Player.NO;
        }

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // calling the reset the game.
                resetTheGame();
            }
        });
    }


    // ADDING ON ONCLICK FUNCTION FOR IMAGE VIEWS
    public void imageViewIsTapped(View imageView) {
        ImageView tappedImageView = (ImageView) imageView;

        int tiTag = Integer.parseInt(imageView.getTag().toString());        // making tiTag variable such that it can be used in the fillng of array playerChoices.

        if (playerChoices[tiTag] == Player.NO && gameOver == false)              // as if the value of the image View is already filled then we can't fill it again.
        {
            playerChoices[tiTag] = currentPlayer;

            if (currentPlayer == Player.ONE)
            {
                tappedImageView.setTranslationX(-2000);                     // setting image view of the tapped to be at -2000dp
                tappedImageView.setImageResource(R.drawable.lion);         // to set the image of  tiger.

                tappedImageView.animate().translationXBy(2000f).alpha(1).rotation(3600).setDuration(1000);          // using 3 animation (for using alpha firstly set it to 0).

                currentPlayer = Player.TWO;         // changing player after setting the image.
            }
            else if (currentPlayer == Player.TWO)
            {
                tappedImageView.setTranslationX(2000);                     // setting image view of the tapped to be at 2000dp
                tappedImageView.setImageResource(R.drawable.tiger);         // to set the image of  tiger.

                tappedImageView.animate().translationXBy(-2000f).alpha(1).rotation(3600).setDuration(1000);          // using 3 animation (for using alpha firstly set it to 0).

                currentPlayer = Player.ONE;                 // changing player after setting the image.
            }


            for (int[] winnerColumns : winner)
            {
                if (playerChoices[winnerColumns[0]] == playerChoices[winnerColumns[1]] &&
                        playerChoices[winnerColumns[1]] == playerChoices[winnerColumns[2]] &&
                        playerChoices[winnerColumns[0]] != Player.NO)
                {
                    gameOver = true;        // as game is Over (So it should not be played again).
                    btnReset.setVisibility(View.VISIBLE);       // as when the game is over the button should be visible.

                    String winnerPlayer = "";
                    if (currentPlayer == Player.ONE)
                    {
                        winnerPlayer = "Player 2 ";      // as the player is changed when the imageView is tapped.
                    }
                    else if (currentPlayer == Player.TWO)
                    {
                        winnerPlayer = "Player 1 ";
                    }
                    Toast.makeText(this, winnerPlayer + "is the Winner.", Toast.LENGTH_LONG).show();
                }
            }
        }
    }



    // Reset the Game
    private void resetTheGame()
    {
        for(int index = 0 ; index < gridLayout.getChildCount() ; index++)
        {
            ImageView imageView = (ImageView)gridLayout.getChildAt(index);          // typecasting the child of grid layout at index index

            imageView.setImageDrawable(null);           // set the image back to null at the imageView
            imageView.setAlpha(0.2f);                   // setting the image view to back to the shade as it were.
        }

        // change the things as they were at starting.

        currentPlayer = Player.ONE;

        for(int i = 0 ; i < playerChoices.length ; i++)
        {
            playerChoices[i] = Player.NO;
        }

        gameOver = false;

        btnReset.setVisibility(View.GONE);      // As the game is reset so the button should be gone.
    }
}
