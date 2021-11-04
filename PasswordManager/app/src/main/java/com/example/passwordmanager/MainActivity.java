package com.example.passwordmanager;

import androidx.biometric.BiometricPrompt;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.util.concurrent.Executor;

import utdallas.edu.passwordmanager.R;

public class MainActivity extends AppCompatActivity {

    //UI Views
    private TextView authStatusTv;
    private Button authBtn;

    private Executor exec;
    private BiometricPrompt biometricPrompt;
    private BiometricPrompt.PromptInfo promptInfo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //init UI view
        authStatusTv = findViewById(R.id.authStatusTv);
        authBtn = findViewById(R.id.authBtn);

        //init biometric
        exec = ContextCompat.getMainExecutor(this);
        biometricPrompt = new BiometricPrompt(MainActivity.this, exec, new BiometricPrompt.AuthenticationCallback(){
            @Override
            public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
                authStatusTv.setText("Authentication Error: " +errString);
                Toast.makeText(MainActivity.this, "Authentication Error: " +errString, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                authStatusTv.setText("Authentication Successful");
                Toast.makeText(MainActivity.this, "Authentication Success!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
                authStatusTv.setText("Authentication Failed");
                Toast.makeText(MainActivity.this, "Authentication Failed" , Toast.LENGTH_SHORT).show();
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