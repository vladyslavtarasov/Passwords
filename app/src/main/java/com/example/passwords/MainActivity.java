package com.example.passwords;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void showPassword(View view) {
        EditText password = findViewById(R.id.password_text);
        password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        password.setSelection(password.length());
    }

    public void hidePassword(View view) {
        EditText password = findViewById(R.id.password_text);
        password.setTransformationMethod(PasswordTransformationMethod.getInstance());
        password.setSelection(password.length());
    }

    public void viewPassword(View view) {
        EditText password = findViewById(R.id.password_text);
        if (password.getText().toString().equals("")) {
            Toast toast = Toast.makeText(this, "Please enter your password first", Toast.LENGTH_LONG);
            toast.show();
            return;
        }
        TextView passwordView = findViewById(R.id.password);
        passwordView.setText("Your password: " + password.getText());
        passwordView.setVisibility(View.VISIBLE);
        password.setText("");
    }
}