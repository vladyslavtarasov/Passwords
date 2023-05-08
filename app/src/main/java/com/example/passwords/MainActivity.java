package com.example.passwords;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void showPassword(View view) {
        EditText password = findViewById(R.id.password_text);
        password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
    }

    public void hidePassword(View view) {
        EditText password = findViewById(R.id.password_text);
        password.setTransformationMethod(PasswordTransformationMethod.getInstance());
    }

    public void viewPassword(View view) {
        EditText password = findViewById(R.id.password_text);
        TextView passwordView = findViewById(R.id.password);
        passwordView.setText("Your password: " + password.getText());
        passwordView.setVisibility(View.VISIBLE);
        password.setText("");
    }
}