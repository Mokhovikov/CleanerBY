package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class LoginFragment extends Activity {
        protected void onCreate(Bundle icicle) {
                super.onCreate(icicle);

                setContentView(R.layout.fragment_login);

                final Button button = (Button) findViewById(R.id.login);
                button.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                                // Perform action on click


                                Intent activityChangeIntent = new Intent(LoginFragment.this, SecondActivity.class);

                                // currentContext.startActivity(activityChangeIntent);

                                LoginFragment.this.startActivity(activityChangeIntent);
                        }
                });
        }
}
