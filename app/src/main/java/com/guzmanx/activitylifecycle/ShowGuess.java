package com.guzmanx.activitylifecycle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class ShowGuess extends AppCompatActivity {
    private TextView showGuessTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_guess);

        // Get all extras we put into intent using bundle
        Bundle extra = getIntent().getExtras();

        showGuessTextView = findViewById(R.id.received_textview);

        // make sure intent extras contains data
        if(extra != null) {
            // display user's guess on screen
            showGuessTextView.setText(extra.getString("guess"));
            Log.d("Name extra", "onCreate: " + extra.getString("name"));
            Log.d("Name extra 2", "onCreate: " + extra.getInt("age"));
        }

        // Listen for click on result text, put result message into intent, return to prev activity
        showGuessTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                intent.putExtra("message_back", "From Second Activity");
                setResult(RESULT_OK, intent);
                // pop current activity off stack, so we can see what's behind it, the 1st activity
                finish();
            }
        });

//        if(getIntent().getStringExtra("guess") != null) {
//            Log.d("Stuff", " " + getIntent().getStringExtra("name"));
//            String value = getIntent().getStringExtra("guess");
//            showGuessTextView.setText(value);
//        }


    }
}
