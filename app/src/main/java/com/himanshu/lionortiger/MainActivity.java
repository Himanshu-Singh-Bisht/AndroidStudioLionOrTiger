package com.himanshu.lionortiger;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    // ADDING ON ONCLICK FUNCTION FOR IMAGE VIEWS
    public void imageViewIsTapped(View imageView)
    {
        ImageView tappedImageView = (ImageView) imageView;

        tappedImageView.setTranslationX(-2000);
        tappedImageView.setImageResource(R.drawable.tiger);         // to set the image of  tiger.

        tappedImageView.animate().translationXBy(2000f).alpha(1).rotation(3600).setDuration(1000);          // using 3 animation (for using alpha firstly set it to 0).


    }
}
