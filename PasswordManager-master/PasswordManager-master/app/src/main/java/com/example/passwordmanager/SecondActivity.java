package com.example.passwordmanager;

import androidx.biometric.BiometricPrompt;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Toast;
import java.util.concurrent.Executor;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;

public class SecondActivity extends AppCompatActivity {

    //UI Views
    private TextView authStatusTv;
    private Button authBtn;

    private Executor exec;
    private BiometricPrompt biometricPrompt;
    private BiometricPrompt.PromptInfo promptInfo;
    //for storing email and passwords
    private EditText email, password;
    
    SharedPreferences sharedPreferences;
    public static final String mypreference ="mypref";
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        //storage UI 
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
                                           
        //init UI view
        authStatusTv = findViewById(R.id.authStatusTv);
        authBtn = findViewById(R.id.authBtn);

        //setting shared preferences
        sharedPreferences = getSharedPreferences(mypreference, Context.MODE_PRIVATE);
        if(sharedPreferences.contains(String.valueOf(email)))
        {
            email.setText(sharedPreferences.getString(String.valueOf(email), ""));
        }
        if(sharedPreferences.contains(String.valueOf(password)))
        {
            password.setText(sharedPreferences.getString(String.valueOf(password),""));
        }
        
        //init biometric
        exec = ContextCompat.getMainExecutor(this);
        biometricPrompt = new BiometricPrompt(SecondActivity.this, exec, new BiometricPrompt.AuthenticationCallback(){
            @Override
            public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
                authStatusTv.setText("Authentication Error: " +errString);
                Toast.makeText(SecondActivity.this, "Authentication Error: " +errString, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                authStatusTv.setText("Authentication Successful");
                Toast.makeText(SecondActivity.this, "Authentication Success!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
                authStatusTv.setText("Authentication Failed");
                Toast.makeText(SecondActivity.this, "Authentication Failed" , Toast.LENGTH_SHORT).show();
            }
        });

        promptInfo = new BiometricPrompt.PromptInfo.Builder()
                .setTitle("Biometric Authentication")
                .setSubtitle("Login Using Fingerprint Authentication")
                .setNegativeButtonText("User App Password")
                .build();

        //Start Authentication
        authBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //Show Auth Dialog
                biometricPrompt.authenticate(promptInfo);
            }
        });
    }
}
