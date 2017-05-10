package com.example.maryam.androidapp;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;






public class SignupActivity extends AppCompatActivity {
    private static final String TAG = "SignupActivity";
    EditText inputEmail,inputPassword,namee;
    Button signup;
    TextView loginlink;
    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.signup);
        inputEmail= (EditText) findViewById(R.id.input_email);
        inputPassword= (EditText) findViewById(R.id.input_password);
        namee= (EditText) findViewById(R.id.input_name);
        signup= (Button) findViewById(R.id.btn_signup);
        loginlink= (TextView) findViewById(R.id.link_login);
        signup.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                signupp();
            }
        });
        loginlink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });
    }
    public void signupp() {
        Log.d(TAG, "Signup");

        if (!validate()) {
            onSignupFailed();
            return;
        }

        signup.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(SignupActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Creating Account...");
        progressDialog.show();

        String name =namee.getText().toString();
        String email = inputEmail.getText().toString();
        String password = inputPassword.getText().toString();
        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {

                        onSignupSuccess();

                        progressDialog.dismiss();
                    }
                }, 3000);
    }
    public void onSignupSuccess() {
        signup.setEnabled(true);
        setResult(RESULT_OK, null);
        finish();
    }

    public void onSignupFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

        signup.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String name = namee.getText().toString();
        String email = inputEmail.getText().toString();
        String password = inputPassword.getText().toString();

        if (name.isEmpty() || name.length() < 3) {
            namee.setError("at least 3 characters");
            valid = false;
        } else {
            namee.setError(null);
        }

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            inputEmail.setError("enter a valid email address");
            valid = false;
        } else {
            inputEmail.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            inputPassword.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            inputPassword.setError(null);
        }

        return valid;
    }
}


