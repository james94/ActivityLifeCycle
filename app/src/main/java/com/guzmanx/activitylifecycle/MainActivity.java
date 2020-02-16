package com.guzmanx.activitylifecycle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button showGuess;
    private EditText enterGuess;
    // final means it can never be changed
    private final int REQUEST_CODE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showGuess = findViewById(R.id.button_guess);
        enterGuess = findViewById(R.id.guess_field);

        // everytime you start a new activity, show where you come from and where you are going
        // another Activity (ex: Activity B) is able to get this Activity's intent message
        // this activity is also able to get back result message from another activity (ex: B)
        showGuess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Extract user input string where they enter their guess
                // Note: get any text, convert to string, trim to include spaces and other stuff
                String guess = enterGuess.getText().toString().trim();

                if(!guess.isEmpty()) {
                    Intent intent = new Intent(MainActivity.this, ShowGuess.class);
                    intent.putExtra("guess", guess);
                    intent.putExtra("name", "bond");
                    intent.putExtra("age", 34);
                    //startActivity(intent);

                    // Start Activity and Wait for Result
                    // Note: the REQUEST_CODE tells us this is not a normal activity, if something
                    // happens, we expect to receive something inside our own activity result
                    startActivityForResult(intent, REQUEST_CODE);
                }else {
                    Toast.makeText(MainActivity.this, "Enter guess",
                            Toast.LENGTH_SHORT)
                            .show();
                }


            }
        });

//        Log.d("Cycle", "onCreate: ");
//
//        Toast.makeText(MainActivity.this, "OnCreate() Called",
//                Toast.LENGTH_SHORT)
//                .show();
    }

    // fetch what might be coming from a different activity
    // fetch message from Activity B back to this Activity A
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // check what we are receiving is part of protocol and these activities we put together
        // Note: what we don't want to happen is to not have security where any activity of a
        // malicious app is able to get caught here or retrieve info from your application
        if(requestCode == REQUEST_CODE) {
            if(resultCode == RESULT_OK) {
                // check to verify data is not null
                assert data != null;
                String message = data.getStringExtra("message_back");

                // Make toast message
                Toast.makeText(MainActivity.this, message,
                        Toast.LENGTH_LONG)
                        .show();
            }
        }
    }

    //    @Override
//    protected void onStart() {
//        super.onStart();
//
//        Log.d("Cycle", "onStart: ");
//
//        Toast.makeText(MainActivity.this, "OnStart() Called",
//                Toast.LENGTH_SHORT)
//                .show();
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//
//        Log.d("Cycle", "onResume: ");
//
//        Toast.makeText(MainActivity.this, "OnResume() Called",
//                Toast.LENGTH_SHORT)
//                .show();
//    }
//
//    @Override
//    protected void onPause() {
//        super.onPause();
//
//        Log.d("Cycle", "onPause: ");
//
//        Toast.makeText(MainActivity.this, "OnPause() Called",
//                Toast.LENGTH_SHORT)
//                .show();
//    }
//
//    @Override
//    protected void onStop() {
//        super.onStop();
//
//        Log.d("Cycle", "onStop: ");
//
//        Toast.makeText(MainActivity.this, "OnStop() Called",
//                Toast.LENGTH_SHORT)
//                .show();
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//
//        Log.d("Cycle", "onDestroy: ");
//
//        Toast.makeText(MainActivity.this, "OnDestroy() Called",
//                Toast.LENGTH_SHORT)
//                .show();
//    }
}

