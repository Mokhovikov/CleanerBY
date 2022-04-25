package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SecondActivity extends Activity {




    protected void onCreate(Bundle icicle) {
        super.onCreate(icicle);

        setContentView(R.layout.fragment_second);

        final Button button = (Button) findViewById(R.id.button_second);
        final Button gps = (Button) findViewById(R.id.buttonGps);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click

                Intent activityChangeIntent = new Intent(SecondActivity.this, MainActivity.class);

                // currentContext.startActivity(activityChangeIntent);

                SecondActivity.this.startActivity(activityChangeIntent);

            }
        });

        gps.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click

                Intent activityChangeIntent = new Intent(SecondActivity.this, main.class);

                // currentContext.startActivity(activityChangeIntent);

                SecondActivity.this.startActivity(activityChangeIntent);

            }
        });
    }
}
