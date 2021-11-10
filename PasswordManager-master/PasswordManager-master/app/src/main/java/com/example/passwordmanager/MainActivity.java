package com.example.mypasswordsafe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Context;
import android.content.SharedPreferences;



public class MainActivity extends AppCompatActivity {

    private EditText username, password;
    private Button login;
    SharedPreferences sharedPreferences;

    public static final String mypreference ="mypref";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);

        login = (Button)findViewById(R.id.login);
        //instantiating shared preferences
        sharedPreferences = getSharedPreferences(mypreference, Context.MODE_PRIVATE);
        if(sharedPreferences.contains(String.valueOf(username)))
        {
            username.setText(sharedPreferences.getString(String.valueOf(username), ""));
        }
        if(sharedPreferences.contains(String.valueOf(password)))
        {
            password.setText(sharedPreferences.getString(String.valueOf(password),""));
        }
    }

    public void validate(View view) {

        String name = username.getText().toString();
        String passwd= password.getText().toString();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(String.valueOf(username), name);
        editor.putString(String.valueOf(password),passwd);
        editor.commit();

        if(name.equals("Zaina") || passwd.equals("gohome")){
            Intent intent = new Intent(this, MainActivity.class);
        }else{
            Toast.makeText(this, "Password/Username incorrect", Toast.LENGTH_SHORT).show();
        }
    }
    public void Get(View view)
    {
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        sharedPreferences = getSharedPreferences(mypreference, Context.MODE_PRIVATE);
        if(sharedPreferences.contains(String.valueOf(username)))
        {
            username.setText(sharedPreferences.getString(String.valueOf(username), ""));
        }
        if(sharedPreferences.contains(String.valueOf(password)))
        {
            password.setText(sharedPreferences.getString(String.valueOf(password),""));
        }
    }



    public Button getLogin() {
        return login;
    }

    public void setLogin(Button login) {
        this.login = login;
    }
}