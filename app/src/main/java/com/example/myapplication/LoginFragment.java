package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Button;
import androidx.navigation.fragment.NavHostFragment;
import com.example.myapplication.databinding.FragmentLoginBinding;

public class LoginFragment extends Activity {
        protected void onCreate(Bundle icicle) {
                super.onCreate(icicle);

                setContentView(R.layout.fragment_login);

                final Button button = (Button) findViewById(R.id.login);
                button.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                                // Perform action on click

                                Intent activityChangeIntent = new Intent(LoginFragment.this, MainActivity.class);

                                // currentContext.startActivity(activityChangeIntent);

                                LoginFragment.this.startActivity(activityChangeIntent);
                        }
                });
        }
}
