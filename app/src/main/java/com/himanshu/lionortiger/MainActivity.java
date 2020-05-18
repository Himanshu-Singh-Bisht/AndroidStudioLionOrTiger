package com.himanshu.lionortiger;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        for(int i = 0 ; i < playerChoices.length ; i++)
        {
            playerChoices[i] = Player.NO;
        }
    }


    // ADDING ON ONCLICK FUNCTION FOR IMAGE VIEWS
    public void imageViewIsTapped(View imageView)
    {
        ImageView tappedImageView = (ImageView) imageView;

        int tiTag = Integer.parseInt(imageView.getTag().toString());        // making tiTag variable such that it can be used in the fillng of array playerChoices.
        playerChoices[tiTag] = currentPlayer;

        if (currentPlayer ==Player.ONE)
        {
            tappedImageView.setTranslationX(-2000);                     // setting image view of the tapped to be at -2000dp
            tappedImageView.setImageResource(R.drawable.lion);         // to set the image of  tiger.

            tappedImageView.animate().translationXBy(2000f).alpha(1).rotation(3600).setDuration(1000);          // using 3 animation (for using alpha firstly set it to 0).

            currentPlayer = Player.TWO;         // changing player after setting the image.
        }
        else if(currentPlayer == Player.TWO)
        {
            tappedImageView.setTranslationX(2000);                     // setting image view of the tapped to be at 2000dp
            tappedImageView.setImageResource(R.drawable.tiger);         // to set the image of  tiger.

            tappedImageView.animate().translationXBy(-2000f).alpha(1).rotation(3600).setDuration(1000);          // using 3 animation (for using alpha firstly set it to 0).

            currentPlayer = Player.ONE;                 // changing player after setting the image.
        }


        for(int[] winnerColumns : winner)
        {
            if(playerChoices[winnerColumns[0]] == playerChoices[winnerColumns[1]] &&
                playerChoices[winnerColumns[1]] == playerChoices[winnerColumns[2]] &&
                    playerChoices[winnerColumns[0]] != Player.NO)
            {
                if(currentPlayer == Player.ONE)
                {
                    Toast.makeText(this , "Player 2 is winner" , Toast.LENGTH_SHORT).show();        // as the player is changed when the imageView is tapped.
                }
                else if(currentPlayer == Player.TWO)
                {
                    Toast.makeText(this , "Player 1 is winner" , Toast.LENGTH_SHORT).show();
                }
                //Toast.makeText(this , "Winner" , Toast.LENGTH_SHORT).show();
            }
        }
    }
}
